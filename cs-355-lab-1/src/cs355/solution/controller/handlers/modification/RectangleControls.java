package cs355.solution.controller.handlers.modification;

import java.awt.Graphics2D;

import cs355.solution.controller.ViewTransformController;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Rectangle;
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public class RectangleControls extends SelectionControls<Rectangle>
{
	protected final HandleControl	topLeft, topRight, bottomLeft, bottomRight, rotate;

	protected int					activeHandle;
	protected Vector2D				oldPointO;
	protected Vector2D				oldPointW;

	public RectangleControls(IController controller, Rectangle s)
	{
		super(controller, s);

		ViewTransformController vtc = controller.getViewTransformController();

		topLeft = new HandleControl(vtc);
		topRight = new HandleControl(vtc);
		bottomLeft = new HandleControl(vtc);
		bottomRight = new HandleControl(vtc);
		rotate = new HandleControl(vtc);

		positionHandles();

		activeHandle = -1;
	}

	protected void positionHandles()
	{
		float halfWidth = shape.getWidth() * 0.5f;
		float halfHeight = shape.getHeight() * 0.5f;

		topLeft.copyValues(-halfWidth, -halfHeight);
		topRight.copyValues(halfWidth, -halfHeight);
		bottomLeft.copyValues(-halfWidth, halfHeight);
		bottomRight.copyValues(halfWidth, halfHeight);
		rotate.copyValues(getRotationHandlePosition(-halfHeight));
	}

	@Override
	public boolean contains(Vector2D w)
	{
		if (shape.contains(w))
			return true;

		Vector2D o = shape.worldToObject(w);

		if (topLeft.contains(o))
			return true;
		else if (topRight.contains(o))
			return true;
		else if (bottomLeft.contains(o))
			return true;
		else if (bottomRight.contains(o))
			return true;
		else if (rotate.contains(o))
			return true;
		else
			return false;
	}

	@Override
	protected void drawComponents(Graphics2D g, Matrix otov)
	{
		topLeft.draw(g, otov);
		topRight.draw(g, otov);
		bottomLeft.draw(g, otov);
		bottomRight.draw(g, otov);
		rotate.draw(g, otov);
		// drawRotateHandle(rotate, g, otov);

		Vector2D tlCorner = shape.getTopLeftCorner();
		// tlCorner.multiply(otov);
		//
		// AffineTransform rotate = shape.getRotationTransform().toAffineTransform();
		// AffineTransform original = g.getTransform();
		// g.setTransform(rotate);

		int x = (int) tlCorner.x;
		int y = (int) tlCorner.y;

		int width = (int) (shape.getWidth() * otov.getScaleX());
		int height = (int) (shape.getHeight() * otov.getScaleY());

		g.drawRect(x, y, width, height);
		// g.setTransform(original);
	}

	@Override
	public void mousePressed(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		// Vector2D offset = getRotationHandleOffset();
		// Vector2D r = w.getAddedCopy(offset);
		// r = shape.worldToObject(r);

		if (topLeft.contains(o))
		{
			activeHandle = 1;
		}
		else if (topRight.contains(o))
		{
			activeHandle = 2;
		}
		else if (bottomLeft.contains(o))
		{
			activeHandle = 3;
		}
		else if (bottomRight.contains(o))
		{
			activeHandle = 4;
		}
		else if (rotate.contains(o))
		{
			activeHandle = 5;
		}
		else if (shape.contains(w))
		{
			activeHandle = 0;
		}

		oldPointW = w;
		oldPointO = o;
	}

	@Override
	public void mouseDragged(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		Vector2D transW = w.getSubtractedCopy(oldPointW);
		Vector2D transO = o.getSubtractedCopy(oldPointO);

		Vector2D halfTransW = transW.getScaledCopy(0.5f);
		Vector2D halfTransO = transO.getScaledCopy(0.5f);

		switch (activeHandle)
		{
			case 0:
				shape.translate(transW);
				o.subtract(transW);
				break;
			case 1:
				update(transO, halfTransW);
				o.subtract(halfTransO);
				break;
			case 2:
				update(transO, halfTransW);
				o.subtract(halfTransO);
				break;
			case 3:
				update(transO, halfTransW);
				o.subtract(halfTransO);
				break;
			case 4:
				update(transO, halfTransW);
				o.subtract(halfTransO);
				break;
			case 5:
				o = w.getSubtractedCopy(shape.getCenter());
				float angle = Vector2D.angleBetween(Vector2D.Y_AXIS.getInvertedCopy(), o);
				shape.setRotation(angle);
				break;
			default:
				return;
		}

		oldPointW = w;
		oldPointO = o;
		controller.refresh();
	}

	private void update(Vector2D transO, Vector2D halfTransW)
	{
		float width = shape.getWidth() + (activeHandle % 2 == 1 ? -1 : 1) * transO.x;
		float height = shape.getHeight() + (activeHandle <= 2 ? -1 : 1) * transO.y;

		if (width < 0)
		{
			shape.setWidth(-width);

			if (activeHandle % 2 == 1)
			{
				activeHandle++;
			}
			else if (activeHandle % 2 == 0)
			{
				activeHandle--;
			}
		}
		else
			shape.setWidth(width);

		if (height < 0)
		{
			shape.setHeight(-height);

			if (activeHandle <= 2)
			{
				activeHandle += 2;
			}
			else if (activeHandle > 2)
			{
				activeHandle -= 2;
			}
		}
		else
			shape.setHeight(height);

		shape.translate(halfTransW);

		positionHandles();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		activeHandle = -1;
	}

	@Override
	public void update()
	{
		positionHandles();
	}
}
