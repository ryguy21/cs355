package cs355.solution.controller;

import java.awt.Color;

import cs355.solution.model.shapes.ShapeType;
import cs355.solution.util.math.Vector2D;

public class DrawingState
{
	private Vector2D	startPoint;
	private Vector2D	endPoint;
	private Vector2D	intermediatePoint;

	private ShapeType	currentShape;
	private Color		color;

	public boolean isSetUp()
	{
		return currentShape != null && color != null;
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
		return startPoint;
	}

	public void setStartPoint(Vector2D startPoint)
	{
		this.startPoint = startPoint;
	}

	public Vector2D getEndPoint()
	{
		return endPoint;
	}

	public void setEndPoint(Vector2D endPoint)
	{
		this.endPoint = endPoint;
	}

	public Vector2D getIntermediatePoint()
	{
		return intermediatePoint;
	}

	public void setIntermediatePoint(Vector2D intermediatePoint)
	{
		this.intermediatePoint = intermediatePoint;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public void reset()
	{
		startPoint = null;
		intermediatePoint = null;
		endPoint = null;
	}
}
