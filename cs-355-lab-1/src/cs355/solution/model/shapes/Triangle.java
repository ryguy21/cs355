package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.util.math.Vector2D;

public class Triangle extends Shape
{
	private final Vector2D	p1;
	private final Vector2D	p2;
	private final Vector2D	p3;

	public Triangle(Color color, Vector2D p1, Vector2D p2, Vector2D p3)
	{
		super(color, Vector2D.average(p1, p2, p3));

		this.p1 = p1.getSubtractedCopy(center);
		this.p2 = p2.getSubtractedCopy(center);
		this.p3 = p3.getSubtractedCopy(center);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		if (p1.equals(p2) || p2.equals(p3) || p3.equals(p1))
			return false;

		p = p.getSubtractedCopy(center);

		int l1 = (int) Math.signum(p.getSubtractedCopy(p1).dot(p2.getSubtractedCopy(p1).getPerpendicular()));
		int l2 = (int) Math.signum(p.getSubtractedCopy(p2).dot(p3.getSubtractedCopy(p2).getPerpendicular()));
		int l3 = (int) Math.signum(p.getSubtractedCopy(p3).dot(p1.getSubtractedCopy(p3).getPerpendicular()));

		return l1 == l2 && l2 == l3;
	}

	public Vector2D getPoint1()
	{
		return p1.getAddedCopy(center);
	}

	public void setPoint1(Vector2D pt1)
	{
		p1.x = pt1.x - center.x;
		p1.y = pt1.y - center.y;
		resetCenter();
	}

	public void translatePoint1(Vector2D trans)
	{
		p1.add(trans);
		resetCenter();
	}

	public Vector2D getPoint2()
	{
		return p2.getAddedCopy(center);
	}

	public void setPoint2(Vector2D pt2)
	{
		p2.x = pt2.x - center.x;
		p2.y = pt2.y - center.y;
		resetCenter();
	}

	public void translatePoint2(Vector2D trans)
	{
		p2.add(trans);
		resetCenter();
	}

	public Vector2D getPoint3()
	{
		return p3.getAddedCopy(center);
	}

	public void setPoint3(Vector2D pt3)
	{
		p3.x = pt3.x - center.x;
		p3.y = pt3.y - center.y;
		resetCenter();
	}

	public void translatePoint3(Vector2D trans)
	{
		p3.add(trans);
		resetCenter();
	}

	private void resetCenter()
	{
		Vector2D trans = Vector2D.average(p1, p2, p3);

		p1.subtract(trans);
		p2.subtract(trans);
		p3.subtract(trans);
		center.add(trans);
	}

	@Override
	public ShapeType getType()
	{
		return ShapeType.TRIANGLE;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Triangle [center=");
		builder.append(center.print());
		builder.append(", p1=");
		builder.append(p1.print());
		builder.append(", p2=");
		builder.append(p2.print());
		builder.append(", p3=");
		builder.append(p3.print());
		builder.append("]");
		return builder.toString();
	}
}
