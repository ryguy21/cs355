package cs355.solution.model;

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

	public Vector2D getCenter()
	{
		return center.getCopy();
	}

	public void setCenter(Vector2D center)
	{
		this.center.copyValues(center);
	}

	public float getxRadius()
	{
		return xRadius;
	}

	public void setxRadius(float xRadius)
	{
		this.xRadius = xRadius;
	}

	public float getyRadius()
	{
		return yRadius;
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
}
