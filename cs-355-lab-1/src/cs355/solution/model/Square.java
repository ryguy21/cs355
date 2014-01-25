package cs355.solution.model;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Square extends Rectangle
{
	public Square(Color color, Vector2D topLeft, int size)
	{
		super(color, topLeft, size, size);
	}

	public float getSize()
	{
		return width;
	}

	public void setSize(int size)
	{
		super.setWidth(size);
		super.setHeight(size);
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
}
