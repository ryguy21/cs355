package cs355.solution.controller;

import java.awt.Color;

public class DrawingState
{
	private Color	color	= Color.white;

	public DrawingState()
	{}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color c)
	{
		color = c;
	}
}
