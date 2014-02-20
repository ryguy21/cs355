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
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.Log;

public class Controller implements IController
{
	private final IModelManager				model;
	private final EventHandler				handler;

	private InputResponder					currentResponder;

	private final DrawingState				state;
	private final ViewTransformController	viewTransform;

	public Controller(IModelManager model, EventHandler handler)
	{
		this.model = model;
		this.handler = handler;

		state = new DrawingState();
		viewTransform = new ViewTransformController();
		handler.setViewTransformController(viewTransform);
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
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator)
	{
		String message = "keyPressed():";

		while (iterator.hasNext())
		{
			message += " " + iterator.next();
		}

		Log.v(message);
	}

	@Override
	public void doEdgeDetection()
	{
		Log.v("doEdgeDetection()");
	}

	@Override
	public void doSharpen()
	{
		Log.v("doSharpen()");
	}

	@Override
	public void doMedianBlur()
	{
		Log.v("doMedianBlur()");
	}

	@Override
	public void doUniformBlur()
	{
		Log.v("doUniformBlur()");
	}

	@Override
	public void doChangeContrast(int delta)
	{
		Log.v("doChangeContrast(" + delta + ")");
	}

	@Override
	public void doChangeBrightness(int delta)
	{
		Log.v("doChangeBrightness(" + delta + ")");
	}

	@Override
	public void doLoadImage(BufferedImage openImage)
	{
		Log.v("doLoadImage()");
	}

	@Override
	public void toggleBackgroundDisplay()
	{
		Log.v("toggleBackgroundDisplay()");
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
}
