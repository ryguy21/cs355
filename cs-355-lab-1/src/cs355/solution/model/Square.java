package cs355.solution.model;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Square extends Rectangle
{
	public Square(Color color, Vector2D topLeft, int size)
	{
		super(color, topLeft, size, size);
	}

	public void setSize(int size)
	{
		super.setWidth(size);
		super.setHeight(size);
	}

	@Override
	public void setWidth(int size)
	{
		super.setWidth(size);
		super.setHeight(size);
	}

	@Override
	public void setHeight(int size)
	{
		super.setWidth(size);
		super.setHeight(size);
	}
}
