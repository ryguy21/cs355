package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

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

	public abstract ShapeType getType();

	public abstract Vector2D getCenter();

	public abstract float getRotation();

	public abstract boolean contains(Vector2D p);
}
