package cs355.solution.view;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import cs355.ViewRefresher;
import cs355.solution.controller.Controller3D;
import cs355.solution.controller.ViewTransformController;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.Log;

public class Refresher implements ViewRefresher
{
	private final IModelManager		model;
	private final ShapeDrawer		drawer;
	private IController				controller;
	private ViewTransformController	viewController;

	public Refresher(IModelManager model)
	{
		this.model = model;
		drawer = new ShapeDrawer();
	}

	@Override
	public void refreshView(Graphics2D g)
	{
		Log.v("refreshView()");
		Log.v("Drawing %d shapes\n", model.getShapeCount());

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
			if (control != null)
				control.draw(g);

			AffineTransform original = g.getTransform();
			g.setTransform(viewController.getTransform().toAffineTransform());

			Controller3D _3d = controller.get3DController();
			_3d.draw(g, viewController.getZoom());

			g.setTransform(original);
		}

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias_default);
	}

	public void setController(IController controller)
	{
		this.controller = controller;
		viewController = controller.getViewTransformController();
		drawer.setViewTransformController(viewController);
	}
}
