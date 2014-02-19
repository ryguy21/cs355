package cs355.solution.model.shapes;

import java.awt.Color;

import cs355.solution.controller.interfaces.Selectable;
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public abstract class Shape implements Selectable
{
	protected Color			color;

	protected final Matrix	translation;
	protected final Matrix	inverseTranslation;
	protected final Matrix	rotation;
	protected final Matrix	inverseRotation;

	private Matrix			transform;
	private Matrix			inverseTransform;

	private float			rotationAngle;

	private boolean			dirty;

	public Shape(Color color, Vector2D center)
	{
		setColor(color);
		translation = Matrix.getTranslateInstance(center.x, center.y);
		inverseTranslation = Matrix.getTranslateInstance(-center.x, -center.y);
		rotation = new Matrix();
		inverseRotation = new Matrix();

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

		this.rotation.rotate2D(dr);
		inverseRotation.rotate2D(-dr);

		dirty = true;
	}

	public void rotate(float rotation)
	{
		rotationAngle += rotation;

		this.rotation.rotate2D(rotation);
		inverseRotation.rotate2D(-rotation);

		dirty = true;
	}

	public void translate(Vector2D trans)
	{
		translation.translate2D(trans.x, trans.y);
		inverseTranslation.translate2D(-trans.x, -trans.y);

		dirty = true;
	}

	public Vector2D objectToWorld(Vector2D o)
	{
		return o.getMultipliedCopy(getTransform());
	}

	public Vector2D worldToObject(Vector2D w)
	{
		return w.getMultipliedCopy(getInverseTransform());
	}

	public Vector2D rotateObjectToWorld(Vector2D o)
	{
		return o.getMultipliedCopy(rotation);
	}

	public Vector2D rotateWorldToObject(Vector2D w)
	{
		return w.getMultipliedCopy(inverseRotation);
	}

	public Matrix getTransform()
	{
		if (dirty)
			updateTransforms();

		return transform;
	}

	public Matrix getInverseTransform()
	{
		if (dirty)
			updateTransforms();

		return inverseTransform;
	}

	public Matrix getRotationTransform()
	{
		return rotation;
	}

	public Matrix getInverseRotationTransform()
	{
		return inverseRotation;
	}

	private void updateTransforms()
	{
		transform = new Matrix(translation);
		transform.multiply(rotation);

		inverseTransform = new Matrix(inverseRotation);
		inverseTransform.multiply(inverseTranslation);

		dirty = false;
	}
}
