package cs355.solution.controller;

import cs355.GUIFunctions;
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public class ViewTransformController
{
	private static final int	MIN_ZOOM		= 0x1;
	private static final int	MAX_ZOOM		= 0x10;
	private static final int	DEFAULT_ZOOM	= 0x4;

	private final Matrix		translation;
	private final Matrix		scale;
	private final Matrix		inverseTranslation;
	private final Matrix		inverseScale;

	private Matrix				transform;
	private Matrix				inverseTransform;

	private int					zoom;
	private final Vector2D		position;

	private boolean				dirty;
	private boolean				suspendTranslation;

	public ViewTransformController()
	{
		translation = new Matrix();
		inverseTranslation = new Matrix();

		scale = new Matrix();
		inverseScale = new Matrix();

		position = new Vector2D();
		zoom = DEFAULT_ZOOM;

		dirty = true;
		suspendTranslation = false;
	}

	public void zoomIn()
	{
		if (zoom < MAX_ZOOM)
		{
			Vector2D center = calcScreenCenter(position);

			zoom <<= 1;

			scale.scale(2f);
			inverseScale.scale(0.5f);

			suspendTranslation = true;

			setKnobSize();

			Vector2D pos = calcPositionFromCenter(center);
			setPosition(pos);

			dirty = true;
			suspendTranslation = false;
			GUIFunctions.refresh();
		}
	}

	public void zoomOut()
	{
		if (zoom > MIN_ZOOM)
		{
			Vector2D center = calcScreenCenter(position);

			zoom >>= 1;

			scale.scale(0.5f);
			inverseScale.scale(2f);

			suspendTranslation = true;

			Vector2D pos = calcPositionFromCenter(center);
			setPosition(pos);

			setKnobSize();

			dirty = true;
			suspendTranslation = false;
			GUIFunctions.refresh();
		}
	}

	public float getZoom()
	{
		return zoom * 0.25f;
	}

	private void setPosition(Vector2D pos)
	{
		position.copyValues(pos);
		int x = positionToScrollValue((int) position.x);
		int y = positionToScrollValue((int) position.y);

		int knobsize = getKnobSize();
		if (x < 0)
		{
			x = 0;
			position.x = scrollValueToPosition(x);
		}
		else if (x + knobsize > 2048)
		{
			x = 2048 - knobsize;
			position.x = scrollValueToPosition(x);
		}

		if (y < 0)
		{
			y = 0;
			position.y = scrollValueToPosition(y);
		}
		else if (y + knobsize > 2048)
		{
			y = 2048 - knobsize;
			position.x = scrollValueToPosition(y);
		}

		translation.setTranslation2D(position);
		inverseTranslation.setTranslation2D(position.getInvertedCopy());
		GUIFunctions.setHScrollBarPosit(x);
		GUIFunctions.setVScrollBarPosit(y);
	}

	public void setHorizontalPosition(int x)
	{
		if (!suspendTranslation)
		{
			x = scrollValueToPosition(x);
			float dx = x - position.x;
			position.x = x;
			translate(dx, 0);
		}
	}

	public void setVerticalPosition(int y)
	{
		if (!suspendTranslation)
		{
			y = scrollValueToPosition(y);
			float dy = y - position.y;
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
		transform = new Matrix(translation);
		transform.multiply(scale);

		inverseTransform = new Matrix(inverseScale);
		inverseTransform.multiply(inverseTranslation);

		dirty = false;
	}

	private int scrollValueToPosition(int scroll)
	{
		return scroll * zoom / -4;
	}

	private int positionToScrollValue(int position)
	{
		return position * -4 / zoom;
	}

	private int getKnobSize()
	{
		return 2048 / zoom;
	}

	private void setKnobSize()
	{
		int size = getKnobSize();

		GUIFunctions.setHScrollBarKnob(size);
		GUIFunctions.setVScrollBarKnob(size);
	}

	private Vector2D calcScreenOrigin(Vector2D position)
	{
		float iz = 1f / zoom;

		float x = position.x * -4 * iz;
		float y = position.y * -4 * iz;

		return new Vector2D(x, y);
	}

	private Vector2D calcScreenCenter(Vector2D position)
	{
		float diff = 1024f / zoom;

		return calcScreenOrigin(position).add(diff, diff);
	}

	private Vector2D calcPositionFromCenter(Vector2D center)
	{
		float diff = 1024f / zoom;

		float x = (center.x - diff) * zoom / -4;
		float y = (center.y - diff) * zoom / -4;

		return new Vector2D(x, y);
	}
}
