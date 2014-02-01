package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Ellipse extends Shape
{
	private final Vector2D	center;
	private float			xRadius;
	private float			yRadius;

	public Ellipse(Color color, Vector2D center, float xRadius, float yRadius)
	{
		super(color);
		this.center = center.getCopy();
		this.xRadius = xRadius;
		this.yRadius = yRadius;
	}

	@Override
	public Vector2D getCenter()
	{
		return center.getCopy();
	}

	@Override
	public float getRotation()
	{
		return 0f;
	}

	public void setCenter(Vector2D center)
	{
		this.center.copyValues(center);
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
}
