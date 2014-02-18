package cs355.solution.controller;

import java.awt.geom.AffineTransform;

import cs355.GUIFunctions;
import cs355.solution.util.Log;
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public class ViewTransformController
{
	private static final int		MIN_ZOOM		= 0x1;
	private static final int		MAX_ZOOM		= 0x10;
	private static final int		DEFAULT_ZOOM	= 0x1;

	private final Matrix			translation;
	private final Matrix			scale;
	private final Matrix			inverseTranslation;
	private final Matrix			inverseScale;

	private final AffineTransform	translationJ;
	private final AffineTransform	scaleJ;
	private final AffineTransform	inverseTranslationJ;
	private final AffineTransform	inverseScaleJ;

	private Matrix					transform;
	private Matrix					inverseTransform;

	private AffineTransform			transformJ;
	private AffineTransform			inverseTransformJ;

	private int						zoom;
	private final Vector2D			position;

	private boolean					dirty;
	private boolean					suspendTranslation;

	public ViewTransformController()
	{
		translation = new Matrix();
		scale = new Matrix();
		inverseTranslation = new Matrix();
		inverseScale = new Matrix();

		translationJ = new AffineTransform();
		scaleJ = new AffineTransform();
		inverseTranslationJ = new AffineTransform();
		inverseScaleJ = new AffineTransform();

		zoom = DEFAULT_ZOOM;
		position = new Vector2D();

		dirty = true;
		suspendTranslation = false;
	}

	public void zoomIn()
	{
		if (zoom < MAX_ZOOM)
		{
			suspendTranslation = true;
			zoom <<= 1;

			scale.scale(2f);
			inverseScale.scale(0.5f);

			scaleJ.scale(2f, 2f);
			inverseScaleJ.scale(0.5f, 0.5f);

			float trans = 1024 / zoom;
			Vector2D copy = position.add(trans, trans).getCopy();
			translate(trans, trans);

			GUIFunctions.setHScrollBarKnob(2048 / zoom);
			GUIFunctions.setVScrollBarKnob(2048 / zoom);

			position.copyValues(copy);

			translation.setTranslation2D(-position.x, -position.y);
			inverseTranslation.setTranslation2D(position.x, position.y);

			GUIFunctions.setHScrollBarPosit((int) position.x);
			GUIFunctions.setVScrollBarPosit((int) position.y);
			GUIFunctions.refresh();

			dirty = true;
			suspendTranslation = false;
		}
	}

	public void zoomOut()
	{
		if (zoom > MIN_ZOOM)
		{
			suspendTranslation = true;
			float trans = -1024 / zoom;
			Vector2D copy = position.add(trans, trans).getCopy();
			translate(trans, trans);

			zoom >>= 1;

			scale.scale(0.5f);
			inverseScale.scale(2f);

			scaleJ.scale(0.5f, 0.5f);
			inverseScaleJ.scale(2f, 2f);

			translation.setTranslation2D(-position.x, -position.y);
			inverseTranslation.setTranslation2D(position.x, position.y);

			GUIFunctions.setHScrollBarPosit((int) position.x);
			GUIFunctions.setVScrollBarPosit((int) position.y);

			GUIFunctions.setHScrollBarKnob(2048 / zoom);
			GUIFunctions.setVScrollBarKnob(2048 / zoom);
			GUIFunctions.refresh();

			position.copyValues(copy);
			dirty = true;
			suspendTranslation = false;
		}
	}

	public void setHorizontalPosition(float x)
	{
		if (!suspendTranslation)
		{
			float dx = position.x - x;
			position.x = x;
			translate(dx, 0);
		}
	}

	public void setVerticalPosition(float y)
	{
		if (!suspendTranslation)
		{
			float dy = position.y - y;
			position.y = y;
			translate(0, dy);
		}
	}

	public void translate(float dx, float dy)
	{
		if (!suspendTranslation)
		{
			translation.translate2D(dx, dy);
			inverseTranslation.translate2D(-dx, -dy);

			translationJ.translate(dx, dy);
			inverseTranslationJ.translate(-dx, -dy);

			dirty = true;
		}
	}

	public void translate(Vector2D trans)
	{
		translate(trans.x, trans.y);
	}

	public Vector2D viewToWorld(Vector2D p)
	{
		return p.getMultipliedCopy(getInverseTransform());
	}

	public Vector2D worldToView(Vector2D p)
	{
		return p.getMultipliedCopy(getTransform());
	}

	public Matrix getTransform()
	{
		if (dirty)
			updateTransforms();

		Log.d("Compare %s (%s)\n%s to \n%s", transform, position, transform.toAffineTransform(), transformJ);

		return transform;
	}

	public Matrix getInverseTransform()
	{
		if (dirty)
			updateTransforms();

		return inverseTransform;
	}

	private void updateTransforms()
	{
		transform = new Matrix(scale);
		transform.multiply(translation);

		transformJ = new AffineTransform(scaleJ);
		transformJ.concatenate(translationJ);

		inverseTransform = new Matrix(inverseTranslation);
		inverseTransform.multiply(inverseScale);

		inverseTransformJ = new AffineTransform(inverseTranslationJ);
		inverseTransformJ.concatenate(inverseScaleJ);

		dirty = false;
	}
}
