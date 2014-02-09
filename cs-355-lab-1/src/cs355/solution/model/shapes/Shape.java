package cs355.solution.model.shapes;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.solution.controller.interfaces.Selectable;
import cs355.solution.util.math.Vector2D;

public abstract class Shape implements Selectable
{
	protected Color					color;

	private final AffineTransform	transform;
	private final AffineTransform	inverseTransform;
	private float					rotation;

	public Shape(Color color, Vector2D center)
	{
		setColor(color);
		transform = AffineTransform.getTranslateInstance(center.x, center.y);
		inverseTransform = AffineTransform.getTranslateInstance(-center.x, -center.y);
		rotation = 0;
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
		return objectToWorld(new Vector2D());
	}

	public void setCenter(Vector2D center)
	{
		// center = worldToObject(center);
		Vector2D cc = getCenter();
		Vector2D trans = center.subtract(cc);
		translate(trans);
	}

	public float getRotation()
	{
		return rotation;
	}

	public void setRotation(float rotation)
	{
		float dr = rotation - this.rotation;
		this.rotation = rotation;

		transform.rotate(dr);
		inverseTransform.rotate(-dr);
	}

	public void rotate(float rotation)
	{
		this.rotation += rotation;

		transform.rotate(rotation);
		inverseTransform.rotate(-rotation);
	}

	public void translate(Vector2D trans)
	{
		transform.translate(trans.x, trans.y);
		inverseTransform.translate(-trans.x, -trans.y);
	}

	protected Vector2D objectToWorld(Vector2D o)
	{
		Point2D p = transform.transform(o.toPoint2D(), null);
		Vector2D result = new Vector2D(p);
		return result;
	}

	protected Vector2D worldToObject(Vector2D w)
	{
		Point2D p = inverseTransform.transform(w.toPoint2D(), null);
		Vector2D result = new Vector2D(p);
		return result;
	}
}
