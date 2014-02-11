package cs355.solution.controller.controls;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Rectangle;
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

		Vector2D tlCorner = shape.getTopLeftCorner();

		float right = tlCorner.x + shape.getWidth();
		float bottom = tlCorner.y + shape.getHeight();

		topLeft = new HandleControl(tlCorner);
		topRight = new HandleControl(right, tlCorner.y);
		bottomLeft = new HandleControl(tlCorner.x, bottom);
		bottomRight = new HandleControl(right, bottom);

		rotate = new HandleControl(0, tlCorner.y - 30f);

		activeHandle = -1;
	}

	protected void positionHandles()
	{
		Vector2D tlCorner = shape.getTopLeftCorner();

		float right = tlCorner.x + shape.getWidth();
		float bottom = tlCorner.y + shape.getHeight();

		topLeft.copyValues(tlCorner);
		topRight.copyValues(right, tlCorner.y);
		bottomLeft.copyValues(tlCorner.x, bottom);
		bottomRight.copyValues(right, bottom);

		rotate.copyValues(0, tlCorner.y - 30f);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		if (shape.contains(p))
			return true;

		p = shape.worldToObject(p);

		if (topLeft.contains(p))
			return true;
		else if (topRight.contains(p))
			return true;
		else if (bottomLeft.contains(p))
			return true;
		else if (bottomRight.contains(p))
			return true;
		else if (rotate.contains(p))
			return true;
		else
			return false;
	}

	@Override
	protected void drawComponents(Graphics2D g)
	{
		AffineTransform original = g.getTransform();
		AffineTransform otow = shape.getTransform();

		g.setTransform(otow);

		topLeft.draw(g);
		topRight.draw(g);
		bottomLeft.draw(g);
		bottomRight.draw(g);
		rotate.draw(g);

		Vector2D tlCorner = shape.getTopLeftCorner();
		int x = (int) tlCorner.x;
		int y = (int) tlCorner.y;
		int width = (int) shape.getWidth();
		int height = (int) shape.getHeight();

		g.drawRect(x, y, width, height);

		g.setTransform(original);
	}

	@Override
	public void mousePressed(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

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

		shape.setWidth(width);
		shape.setHeight(height);

		shape.translate(halfTransW);

		if (activeHandle % 2 == 1 && width < 0)
		{
			activeHandle++;
		}
		else if (activeHandle % 2 == 0 && width < 0)
		{
			activeHandle--;
		}
		if (activeHandle <= 2 && height < 0)
		{
			activeHandle += 2;
		}
		else if (activeHandle > 2 && height < 0)
		{
			activeHandle -= 2;
		}

		positionHandles();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		activeHandle = -1;
	}
}
