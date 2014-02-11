package cs355.solution.model.shapes;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.solution.controller.interfaces.Selectable;
import cs355.solution.util.math.Vector2D;

public abstract class Shape implements Selectable
{
	protected Color					color;

	protected final AffineTransform	translation;
	protected final AffineTransform	inverseTranslation;
	protected final AffineTransform	rotation;
	protected final AffineTransform	inverseRotation;

	private AffineTransform			transform;
	private AffineTransform			inverseTransform;

	private float					rotationAngle;

	private boolean					dirty;

	public Shape(Color color, Vector2D center)
	{
		setColor(color);
		translation = AffineTransform.getTranslateInstance(center.x, center.y);
		inverseTranslation = AffineTransform.getTranslateInstance(-center.x, -center.y);
		rotation = new AffineTransform();
		inverseRotation = new AffineTransform();
		rotationAngle = 0;
		dirty = true;
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
		Vector2D cc = getCenter();
		Vector2D trans = center.subtract(cc);
		translate(trans);
	}

	public float getRotation()
	{
		return rotationAngle;
	}

	public void setRotation(float rotation)
	{
		float dr = rotation - rotationAngle;
		rotationAngle = rotation;

		this.rotation.rotate(dr);
		inverseRotation.rotate(-dr);

		dirty = true;
	}

	public void rotate(float rotation)
	{
		rotationAngle += rotation;

		this.rotation.rotate(rotation);
		inverseRotation.rotate(-rotation);

		dirty = true;
	}

	public void translate(Vector2D trans)
	{
		translation.translate(trans.x, trans.y);
		inverseTranslation.translate(-trans.x, -trans.y);

		dirty = true;
	}

	public Vector2D objectToWorld(Vector2D o)
	{
		Point2D p = getTransform().transform(o.toPoint2D(), null);
		return new Vector2D(p);
	}

	public Vector2D worldToObject(Vector2D w)
	{
		Point2D p = getInverseTransform().transform(w.toPoint2D(), null);
		return new Vector2D(p);
	}

	public AffineTransform getTransform()
	{
		if (dirty)
			updateTransforms();

		return transform;
	}

	public AffineTransform getInverseTransform()
	{
		if (dirty)
			updateTransforms();

		return inverseTransform;
	}

	private void updateTransforms()
	{
		transform = new AffineTransform(translation);
		transform.concatenate(rotation);

		inverseTransform = new AffineTransform(inverseRotation);
		inverseTransform.concatenate(inverseTranslation);

		dirty = false;
	}
}
