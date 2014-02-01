package cs355.solution.controller;

import java.awt.Color;

import cs355.solution.model.shapes.ShapeType;
import cs355.solution.util.math.Vector2D;

public class DrawingState
{
	private Vector2D	startPoint;
	private Vector2D	endPoint;
	private Vector2D	intermediatePoint;

	private boolean		hasStartPoint;
	private boolean		hasIntermediatePoint;
	private boolean		hasEndPoint;

	private ShapeType	currentShape;
	private Color		color	= Color.white;

	private boolean		enabled;

	public DrawingState()
	{
		reset();
	}

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
		if (currentShape != null)
			enabled = true;
	}

	public void setEnabled(boolean enable)
	{
		enabled = enable;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public Vector2D getStartPoint()
	{
		return startPoint;
	}

	public void setStartPoint(Vector2D startPoint)
	{
		this.startPoint = startPoint;
		hasStartPoint = true;
	}

	public boolean hasStartPoint()
	{
		return hasStartPoint;
	}

	public Vector2D getEndPoint()
	{
		return endPoint;
	}

	public void setEndPoint(Vector2D endPoint)
	{
		this.endPoint = endPoint;
		hasEndPoint = true;
	}

	public void setEndPoint(Vector2D endPoint, boolean finalPoint)
	{
		this.endPoint = endPoint;
		hasEndPoint |= finalPoint;
	}

	public boolean hasEndPoint()
	{
		return hasEndPoint;
	}

	public Vector2D getIntermediatePoint()
	{
		return intermediatePoint;
	}

	public void setIntermediatePoint(Vector2D intermediatePoint)
	{
		this.intermediatePoint = intermediatePoint;
		hasIntermediatePoint = true;
	}

	public void setIntermediatePoint(Vector2D intermediatePoint, boolean finalPoint)
	{
		this.intermediatePoint = intermediatePoint;
		hasIntermediatePoint |= finalPoint;
	}

	public boolean hasIntermediatePoint()
	{
		return hasIntermediatePoint;
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

		hasStartPoint = false;
		hasIntermediatePoint = false;
		hasEndPoint = false;
	}
}
