package cs355.solution.model.shapes;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs355.solution.util.math.Vector2D;

public class Square extends Rectangle
{
	public Square(Color color, Vector2D topLeft, float size)
	{
		super(color, topLeft, size, size);
	}

	public float getSize()
	{
		return width;
	}

	public void setSize(float size)
	{
		super.setWidth(size);
		super.setHeight(size);
	}

	public Vector2D rotateObjectToWorld(Vector2D o)
	{
		Point2D w = rotation.transform(o.toPoint2D(), null);
		return new Vector2D(w);
	}

	public Vector2D rotateWorldToObject(Vector2D w)
	{
		Point2D o = inverseRotation.transform(w.toPoint2D(), null);
		return new Vector2D(o);
	}

	@Override
	public void setWidth(float size)
	{
		super.setWidth(size);
		super.setHeight(size);
	}

	@Override
	public void setHeight(float size)
	{
		super.setWidth(size);
		super.setHeight(size);
	}

	@Override
	public ShapeType getType()
	{
		return ShapeType.SQUARE;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Square [center=");
		builder.append(getCenter());
		builder.append(", size=");
		builder.append(width);
		builder.append("]");
		return builder.toString();
	}
}
