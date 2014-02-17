package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public class Line extends Shape
{
	private final Vector2D	start;
	private final Vector2D	end;

	public Line(Color color, Vector2D start, Vector2D end)
	{
		super(color, Vector2D.average(start, end));
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

	@Override
	public float getRotation()
	{
		return 0f;
	}

	@Override
	public Matrix getTransform()
	{
		return new Matrix();
	}

	@Override
	public Matrix getInverseTransform()
	{
		return new Matrix();
	}

	@Override
	public boolean contains(Vector2D p)
	{
		Vector2D line = new Vector2D(end, start);
		Vector2D linen = line.getNormalizedCopy();
		Vector2D linep = linen.getPerpendicular();

		p = new Vector2D(p, start);

		float along = linen.dot(p);
		float near = linep.dot(p);

		if (along < -4 || along > line.length() + 4)
			return false;
		if (near < -4 || 4 < near)
			return false;

		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Line [center=");
		builder.append(getCenter().print());
		builder.append(", start=");
		builder.append(start.print());
		builder.append(", end=");
		builder.append(end.print());
		builder.append("]");
		return builder.toString();
	}
}
