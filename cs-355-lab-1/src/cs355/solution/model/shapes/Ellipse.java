package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Ellipse extends Shape
{
	private float	xRadius;
	private float	yRadius;

	public Ellipse(Color color, Vector2D center, float xRadius, float yRadius)
	{
		super(color, center);
		this.xRadius = xRadius;
		this.yRadius = yRadius;
	}

	@Override
	public float getRotation()
	{
		return 0f;
	}

	public Vector2D getTopLeftCorner()
	{
		return center.getSubtractedCopy(xRadius, yRadius);
	}

	public float getxRadius()
	{
		return xRadius;
	}

	public float getxDiameter()
	{
		return xRadius * 2f;
	}

	public void setxRadius(float xRadius)
	{
		this.xRadius = xRadius;
	}

	public float getyRadius()
	{
		return yRadius;
	}

	public float getyDiameter()
	{
		return yRadius * 2f;
	}

	public void setyRadius(float yRadius)
	{
		this.yRadius = yRadius;
	}

	@Override
	public ShapeType getType()
	{
		return ShapeType.ELLIPSE;
	}

	@Override
	public boolean contains(Vector2D p)
	{
		return false;
	}

	@Override
	public String toString()
	{
		return "Ellipse [center=" + center.print() + ", a=" + xRadius * 2f + ", b=" + yRadius * 2f + "]";
	}
}
