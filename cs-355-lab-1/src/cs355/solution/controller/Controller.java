package cs355.solution.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.solution.controller.handlers.*;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.controller.interfaces.InputResponder;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.Log;

public class Controller implements IController
{
	private final IModelManager			model;
	private final ViewRefresher			refresher;
	private final EventHandler			handler;

	private final SelectionController	selectionController;

	private Control						currentControl;
	private InputResponder				currentResponder;

	private final DrawingState			state;

	public Controller(IModelManager model, ViewRefresher refresher, EventHandler handler)
	{
		this.model = model;
		this.refresher = refresher;
		this.handler = handler;

		selectionController = new SelectionController(model, this);
		state = new DrawingState();
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
		}
	}

	@Override
	public void triangleButtonHit()
	{
		Log.v("triangleButtonHit()");

		unsetControl();
		unsetInputResponder();

		currentResponder = new TriangleCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
	}

	@Override
	public void squareButtonHit()
	{
		Log.v("squareButtonHit()");
		unsetControl();
		unsetInputResponder();

		currentResponder = new SquareCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
	}

	@Override
	public void rectangleButtonHit()
	{
		Log.v("rectangleButtonHit()");
		unsetControl();
		unsetInputResponder();

		currentResponder = new RectangleCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
	}

	@Override
	public void circleButtonHit()
	{
		Log.v("circleButtonHit()");
		unsetControl();
		unsetInputResponder();

		currentResponder = new CircleCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
	}

	@Override
	public void ellipseButtonHit()
	{
		Log.v("ellipseButtonHit()");
		unsetControl();
		unsetInputResponder();

		currentResponder = new EllipseCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
	}

	@Override
	public void lineButtonHit()
	{
		Log.v("lineButtonHit()");
		unsetControl();
		unsetInputResponder();

		currentResponder = new LineCreationHandler(this, state.getColor());
		handler.registerInputResponder(currentResponder);
	}

	@Override
	public void inputSequenceComplete()
	{
		handler.unregisterInputResponder();
		currentResponder = null;
	}

	@Override
	public void selectButtonHit()
	{
		Log.v("selectButtonHit()");
	}

	@Override
	public void zoomInButtonHit()
	{
		Log.v("zoomInButtonHit()");
	}

	@Override
	public void zoomOutButtonHit()
	{
		Log.v("zoomOutButtonHit()");
	}

	@Override
	public void hScrollbarChanged(int value)
	{
		Log.v("hScrollbarChanged(" + value + ")");
	}

	@Override
	public void vScrollbarChanged(int value)
	{
		Log.v("vScrollbarChanged(" + value + ")");
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

	@Override
	public void setCurrentControl(Control control)
	{
		currentControl = control;
		refresh();
	}

	@Override
	public void unsetControl()
	{
		currentControl = null;
		refresh();
	}

	@Override
	public Control getControl()
	{
		return currentControl;
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
