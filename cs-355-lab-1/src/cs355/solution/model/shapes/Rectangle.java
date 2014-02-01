package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Rectangle extends Shape
{
	protected final Vector2D	topLeft;
	protected float				width;
	protected float				height;

	public Rectangle(Color color, Vector2D topLeft, float width, float height)
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

	public float getWidth()
	{
		return width;
	}

	public void setWidth(float width)
	{
		this.width = width;
	}

	public float getHeight()
	{
		return height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	@Override
	public ShapeType getType()
	{
		return ShapeType.RECTANGLE;
	}

	@Override
	public Vector2D getCenter()
	{
		return topLeft.getAddedCopy(width * 0.5f, height * 0.5f);
	}

	@Override
	public float getRotation()
	{
		return 0f;
	}

	@Override
	public boolean contains(Vector2D p)
	{
		return false;
	}
}
