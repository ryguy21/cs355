package cs355.solution.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.solution.controller.handlers.creation.*;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.controller.interfaces.InputResponder;
import cs355.solution.model.IModelManager;
import cs355.solution.model.images.Image;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.Log;

public class Controller implements IController
{
	private final IModelManager				model;
	private final EventHandler				handler;

	private InputResponder					currentResponder;
	private boolean							backgroundEnabled;

	private final DrawingState				state;
	private final ViewTransformController	viewTransform;
	private final Controller3D				c3d;

	public Controller(IModelManager model, EventHandler handler)
	{
		this.model = model;
		this.handler = handler;

		state = new DrawingState();
		viewTransform = new ViewTransformController();
		handler.setViewTransformController(viewTransform);
		c3d = new Controller3D();
	}

	@Override
	public ViewTransformController getViewTransformController()
	{
		return viewTransform;
	}

	@Override
	public void initialize()
	{
		GUIFunctions.setHScrollBarMin(0);
		GUIFunctions.setHScrollBarMax(2048);
		GUIFunctions.setHScrollBarKnob(512);
		GUIFunctions.setHScrollBarPosit(768);
		GUIFunctions.setVScrollBarMin(0);
		GUIFunctions.setVScrollBarMax(2048);
		GUIFunctions.setVScrollBarKnob(512);
		GUIFunctions.setVScrollBarPosit(768);
	}

	@Override
	public void addShape(Shape shape)
	{
		model.addShape(shape);
	}

	@Override
	public void removeShape(Shape shape)
	{
		model.removeShape(shape);
	}

	@Override
	public void colorButtonHit(Color c)
	{
		Log.v("colorButtonHit(%s)", c);

		GUIFunctions.changeSelectedColor(c);
		state.setColor(c);

		if (currentResponder != null)
		{
			if (currentResponder instanceof ShapeCreationHandler)
			{
				((ShapeCreationHandler) currentResponder).setColor(c);
			}
			else if (currentResponder instanceof SelectionController)
			{
				((SelectionController) currentResponder).setColor(c);
			}
		}
	}

	@Override
	public void triangleButtonHit()
	{
		Log.v("triangleButtonHit()");
		unsetInputResponder();

		currentResponder = new TriangleCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
		refresh();
	}

	@Override
	public void squareButtonHit()
	{
		Log.v("squareButtonHit()");
		unsetInputResponder();

		currentResponder = new SquareCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
		refresh();
	}

	@Override
	public void rectangleButtonHit()
	{
		Log.v("rectangleButtonHit()");
		unsetInputResponder();

		currentResponder = new RectangleCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
		refresh();
	}

	@Override
	public void circleButtonHit()
	{
		Log.v("circleButtonHit()");
		unsetInputResponder();

		currentResponder = new CircleCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
		refresh();
	}

	@Override
	public void ellipseButtonHit()
	{
		Log.v("ellipseButtonHit()");
		unsetInputResponder();

		currentResponder = new EllipseCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
		refresh();
	}

	@Override
	public void lineButtonHit()
	{
		Log.v("lineButtonHit()");
		unsetInputResponder();

		currentResponder = new LineCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
		refresh();
	}

	@Override
	public void selectButtonHit()
	{
		Log.v("selectButtonHit()");
		unsetInputResponder();

		currentResponder = new SelectionController(model, this);
		handler.registerInputResponder(currentResponder);
		refresh();
	}

	@Override
	public Control getControl()
	{
		if (currentResponder != null && currentResponder instanceof SelectionController)
		{
			return ((SelectionController) currentResponder).getControl();
		}

		return null;
	}

	@Override
	public void inputSequenceComplete()
	{
		handler.unregisterInputResponder();
		currentResponder = null;
	}

	@Override
	public void zoomInButtonHit()
	{
		Log.v("zoomInButtonHit()");
		viewTransform.zoomIn();
		updateControl();
		refresh();
	}

	@Override
	public void zoomOutButtonHit()
	{
		Log.v("zoomOutButtonHit()");
		viewTransform.zoomOut();
		updateControl();
		refresh();
	}

	private void updateControl()
	{
		Control control = getControl();
		if (control != null)
		{
			control.update();
		}
	}

	@Override
	public void hScrollbarChanged(int value)
	{
		Log.v("hScrollbarChanged(" + value + ")");
		viewTransform.setHorizontalPosition(value);
		refresh();
	}

	@Override
	public void vScrollbarChanged(int value)
	{
		Log.v("vScrollbarChanged(" + value + ")");
		viewTransform.setVerticalPosition(value);
		refresh();
	}

	@Override
	public void toggle3DModelDisplay()
	{
		Log.v("toggle3DModelDisplay()");
		c3d.enable(!c3d.isEnabled());
		refresh();
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator)
	{
		if (c3d.isEnabled() && c3d.keyPressed(iterator)) refresh();
	}

	@Override
	public void doEdgeDetection()
	{
		if (backgroundEnabled)
		{
			Log.v("doEdgeDetection()");
			Image image = model.getImage();

			image.detectEdges();

			refresh();
		}
	}

	@Override
	public void doSharpen()
	{
		if (backgroundEnabled)
		{
			Log.v("doSharpen()");
			Image image = model.getImage();

			image.sharpen();

			refresh();
		}
	}

	@Override
	public void doMedianBlur()
	{
		if (backgroundEnabled)
		{
			Log.v("doMedianBlur()");
			Image image = model.getImage();

			image.blur(Image.MEDIAN_BLUR);

			refresh();
		}
	}

	@Override
	public void doUniformBlur()
	{
		if (backgroundEnabled)
		{
			Log.v("doUniformBlur()");
			Image image = model.getImage();

			image.blur(Image.UNIFORM_BLUR);

			refresh();
		}
	}

	@Override
	public void doChangeContrast(int delta)
	{
		if (backgroundEnabled)
		{
			Log.v("doChangeContrast(" + delta + ")");
			Image image = model.getImage();

			image.changeContrast(delta);

			refresh();
		}
	}

	@Override
	public void doChangeBrightness(int delta)
	{
		if (backgroundEnabled)
		{
			Log.v("doChangeBrightness(" + delta + ")");
			Image image = model.getImage();

			image.brighten(delta);

			refresh();
		}
	}

	@Override
	public void doLoadImage(BufferedImage image)
	{
		if (backgroundEnabled)
		{
			Log.v("doLoadImage()");
			model.setImage(image);
			refresh();
		}
	}

	@Override
	public void toggleBackgroundDisplay()
	{
		backgroundEnabled = !backgroundEnabled;
		Log.v("toggleBackgroundDisplay(%b)", backgroundEnabled);
		refresh();
	}

	@Override
	public void refresh()
	{
		GUIFunctions.refresh();
	}

	private void unsetInputResponder()
	{
		if (currentResponder != null)
		{
			if (currentResponder instanceof ShapeCreationHandler)
			{
				((ShapeCreationHandler) currentResponder).cancel();
			}

			currentResponder = null;
		}
	}

	@Override
	public Controller3D get3DController()
	{
		return c3d;
	}

	@Override
	public boolean isBackgroundEnabled()
	{
		return backgroundEnabled;
	}
}
