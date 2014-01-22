package cs355.solution.model;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Circle extends Ellipse
{
	public Circle(Color color, Vector2D center, float radius)
	{
		super(color, center, radius, radius);
	}

	public float getRadius()
	{
		return super.getxRadius();
	}

	public void setRadius(float radius)
	{
		super.setxRadius(radius);
		super.setyRadius(radius);
	}

	@Override
	public void setxRadius(float radius)
	{
		super.setxRadius(radius);
		super.setyRadius(radius);
	}

	@Override
	public void setyRadius(float radius)
	{
		super.setxRadius(radius);
		super.setyRadius(radius);
	}
}
