package cs355.solution.model;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Rectangle extends Shape
{
	private final Vector2D	topLeft;
	private int				width;
	private int				height;

	public Rectangle(Color color, Vector2D topLeft, int width, int height)
	{
		super(color);
		this.topLeft = topLeft.getCopy();
		this.width = width;
		this.height = height;
	}

	public Vector2D getTopLeftCorner()
	{
		return topLeft.getCopy();
	}

	public void setTopLeftCorner(Vector2D topLeft)
	{
		this.topLeft.copyValues(topLeft);
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
}
