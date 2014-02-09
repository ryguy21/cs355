package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.controller.interfaces.Selectable;
import cs355.solution.util.math.Vector2D;

public abstract class Shape implements Selectable
{
	protected final Vector2D	center;
	protected Color				color;
	protected float				rotation;

	public Shape(Color color, Vector2D center)
	{
		setColor(color);
		this.center = center.getCopy();
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public abstract ShapeType getType();

	@Override
	public abstract String toString();

	public Vector2D getCenter()
	{
		return center.getCopy();
	}

	public void setCenter(Vector2D center)
	{
		this.center.copyValues(center);
	}

	public float getRotation()
	{
		return rotation;
	}

	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}

	public void translate(Vector2D trans)
	{
		center.add(trans);
	}
}
