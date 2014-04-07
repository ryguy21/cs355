package cs355.solution.view;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import cs355.ViewRefresher;
import cs355.solution.controller.ViewTransformController;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.IModelManager;
import cs355.solution.model.Image;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.ImageUtils;
import cs355.solution.util.Log;

public class Refresher implements ViewRefresher
{
	private final IModelManager		model;
	private final Refresher3D		r3d;
	private final ShapeDrawer		drawer;
	private IController				controller;
	private ViewTransformController	viewController;

	public Refresher(IModelManager model)
	{
		this.model = model;
		drawer = new ShapeDrawer();
		r3d = new Refresher3D();
	}

	@Override
	public void refreshView(Graphics2D g)
	{
		Log.v("refreshView()");
		Log.v("Drawing %d shapes\n", model.getShapeCount());

		if (controller.isBackgroundEnabled()) drawBackground(g);

		Object antialias_default = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Iterator<Shape> itr = model.getShapes();

		while (itr.hasNext())
		{
			Shape shape = itr.next();
			Log.v("Drawing %s", shape);
			drawer.drawShape(shape, g);
		}

		if (controller != null)
		{
			Control control = controller.getControl();
			if (control != null) control.draw(g);

			AffineTransform original = g.getTransform();
			g.setTransform(viewController.getTransform().toAffineTransform());

			r3d.draw(g, viewController.getZoom());

			g.setTransform(original);
		}

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias_default);
	}

	private void drawBackground(Graphics2D g)
	{
		Image image = model.getImage();
		int[][] data = image.getData();
		if (data != null)
		{
			BufferedImage buffer = ImageUtils.createFromData(data);

			g.drawImage(buffer, viewController.getTransform().toAffineTransform(), null);
		}
	}

	public void setController(IController controller)
	{
		this.controller = controller;
		r3d.setController(controller.get3DController());
		viewController = controller.getViewTransformController();
		drawer.setViewTransformController(viewController);
	}
}
