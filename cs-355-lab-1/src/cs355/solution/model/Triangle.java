package cs355.solution.model;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Triangle extends Shape
{
	private final Vector2D	p1;
	private final Vector2D	p2;
	private final Vector2D	p3;

	public Triangle(Color color, Vector2D p1, Vector2D p2, Vector2D p3)
	{
		super(color);

		this.p1 = p1.getCopy();
		this.p2 = p2.getCopy();
		this.p3 = p3.getCopy();
	}

	public Vector2D getPoint1()
	{
		return p1.getCopy();
	}

	public void setPoint1(Vector2D pt1)
	{
		p1.copyValues(pt1);
	}

	public Vector2D getPoint2()
	{
		return p2.getCopy();
	}

	public void setPoint2(Vector2D pt2)
	{
		p2.copyValues(pt2);
	}

	public Vector2D getPoint3()
	{
		return p3.getCopy();
	}

	public void setPoint3(Vector2D pt3)
	{
		p3.copyValues(pt3);
	}
}
