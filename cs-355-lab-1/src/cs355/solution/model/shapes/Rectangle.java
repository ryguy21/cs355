package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Rectangle extends Shape
{
	protected float	width;
	protected float	height;

	public Rectangle(Color color, Vector2D topLeft, float width, float height)
	{
		super(color, topLeft.getAddedCopy(width * 0.5f, height * 0.5f));
		this.width = width;
		this.height = height;
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

	public Vector2D getTopLeftCorner()
	{
		return new Vector2D(width * -0.5f, height * -0.5f);
	}

	@Override
	public ShapeType getType()
	{
		return ShapeType.RECTANGLE;
	}

	@Override
	public boolean contains(Vector2D p)
	{
		p = worldToObject(p);

		if (p.x < -width * 0.5f || width * 0.5f < p.x)
			return false;
		if (p.y < -height * 0.5f || height * 0.5f < p.y)
			return false;

		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Rectangle [center=");
		builder.append(getCenter().print(3, 2));
		builder.append(", width=");
		builder.append(width);
		builder.append(", height=");
		builder.append(height);
		builder.append("]");
		return builder.toString();
	}

}
