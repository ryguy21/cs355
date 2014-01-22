package cs355.solution.model;

import java.awt.Color;

public abstract class Shape
{
	public Shape(Color color)
	{
		setColor(color);
	}

	private Color	color;

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
}
