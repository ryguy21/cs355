package cs355.solution.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.solution.model.IModelManager;
import cs355.solution.util.Log;
import cs355.solution.util.math.Vector2D;

public class Controller implements IController
{
	private final IModelManager		model;
	private final ViewRefresher		refresher;

	private final DrawingController	drawingController;

	public Controller(IModelManager model, ViewRefresher refresher)
	{
		this.model = model;
		this.refresher = refresher;

		drawingController = new DrawingController(model);
	}

	@Override
	public void colorButtonHit(Color c)
	{
		Log.v("colorButtonHit(%s)", c);
		drawingController.colorButtonHit(c);
	}

	@Override
	public void triangleButtonHit()
	{
		Log.v("triangleButtonHit()");
		drawingController.triangleButtonHit();
	}

	@Override
	public void squareButtonHit()
	{
		Log.v("squareButtonHit()");
		drawingController.squareButtonHit();
	}

	@Override
	public void rectangleButtonHit()
	{
		Log.v("rectangleButtonHit()");
		drawingController.rectangleButtonHit();
	}

	@Override
	public void circleButtonHit()
	{
		Log.v("circleButtonHit()");
		drawingController.circleButtonHit();
	}

	@Override
	public void ellipseButtonHit()
	{
		Log.v("ellipseButtonHit()");
		drawingController.ellipseButtonHit();
	}

	@Override
	public void lineButtonHit()
	{
		Log.v("lineButtonHit()");
		drawingController.lineButtonHit();
	}

	@Override
	public void setDrawingStartPoint(Vector2D p)
	{
		drawingController.setDrawingStartPoint(p);
		refresh();
	}

	@Override
	public void updateDrawingEndPoint(Vector2D p)
	{
		drawingController.updateDrawingEndPoint(p);
		refresh();
	}

	@Override
	public void registerClick(Vector2D p)
	{
		boolean consumed = drawingController.addTrianglePoint(p);
		if (!consumed)
		{
			// do something here
		}

		refresh();
	}

	@Override
	public void registerMove(Vector2D p)
	{
		drawingController.updateTrianglePoint(p);
		refresh();
	}

	@Override
	public void endDrawing()
	{
		drawingController.endDrawing();
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

	private void refresh()
	{
		GUIFunctions.refresh();
	}
}
