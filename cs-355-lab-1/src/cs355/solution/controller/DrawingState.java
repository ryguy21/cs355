package cs355.solution.controller;

import java.awt.Color;

import cs355.solution.model.shapes.ShapeType;
import cs355.solution.util.math.Vector2D;

public class DrawingState
{
	private final Vector2D	startPoint;
	private final Vector2D	endPoint;

	private ShapeType		currentShape;
	private Color			color;

	public DrawingState()
	{
		startPoint = new Vector2D();
		endPoint = new Vector2D();
	}

	public ShapeType getCurrentShape()
	{
		return currentShape;
	}

	public void setCurrentShape(ShapeType currentShape)
	{
		this.currentShape = currentShape;
	}

	public Vector2D getStartPoint()
	{
		return startPoint.getCopy();
	}

	public void setStartPoint(Vector2D startPoint)
	{
		this.startPoint.copyValues(startPoint);
	}

	public Vector2D getEndPoint()
	{
		return endPoint.getCopy();
	}

	public void setEndPoint(Vector2D endPoint)
	{
		this.endPoint.copyValues(endPoint);
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
}
