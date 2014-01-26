package cs355.solution.controller;

import java.awt.Color;

import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.ShapeType;
import cs355.solution.util.math.Vector2D;

public class DrawingController
{
	private final IModelManager	model;

	private final DrawingState	drawingState;

	public DrawingController(IModelManager model)
	{
		this.model = model;

		drawingState = new DrawingState();
	}

	public void colorButtonHit(Color c)
	{
		drawingState.setColor(c);
	}

	public void triangleButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.TRIANGLE);
	}

	public void squareButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.SQUARE);
	}

	public void rectangleButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.RECTANGLE);
	}

	public void circleButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.CIRCLE);
	}

	public void ellipseButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.ELLIPSE);
	}

	public void lineButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.LINE);
	}

	public void setDrawingStartPoint(Vector2D p)
	{
		drawingState.setStartPoint(p);
		drawingState.setEndPoint(p);
	}

	public void updateDrawingEndPoint(Vector2D p)
	{
		drawingState.setEndPoint(p);
	}

	public void endDrawing()
	{

	}
}
