package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Line extends Shape
{
	private final Vector2D	start;
	private final Vector2D	end;

	public Line(Color color, Vector2D start, Vector2D end)
	{
		super(color);
		this.start = start.getCopy();
		this.end = end.getCopy();
	}

	public Vector2D getStartPoint()
	{
		return start.getCopy();
	}

	public void setStartPoint(Vector2D start)
	{
		this.start.copyValues(start);
	}

	public Vector2D getEndPoint()
	{
		return end.getCopy();
	}

	public void setEndPoint(Vector2D end)
	{
		this.end.copyValues(end);
	}

	public void translateStartPoint(Vector2D trans)
	{
		start.add(trans);
	}

	public void translateEndPoint(Vector2D trans)
	{
		end.add(trans);
	}

	@Override
	public ShapeType getType()
	{
		return ShapeType.LINE;
	}

	@Override
	public Vector2D getCenter()
	{
		return Vector2D.average(start, end);
	}
}