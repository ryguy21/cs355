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
	protected Vector2D				oldPoint;

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
	public void mousePressed(Vector2D p)
	{
		Vector2D obj = shape.worldToObject(p);

		if (topLeft.contains(obj))
		{
			activeHandle = 1;
			oldPoint = obj;
		}
		else if (topRight.contains(obj))
		{
			activeHandle = 2;
			oldPoint = obj;
		}
		else if (bottomLeft.contains(obj))
		{
			activeHandle = 3;
			oldPoint = obj;
		}
		else if (bottomRight.contains(obj))
		{
			activeHandle = 4;
			oldPoint = obj;
		}
		else if (rotate.contains(obj))
		{
			activeHandle = 5;
			oldPoint = obj;
		}
		else if (shape.contains(p))
		{
			activeHandle = 0;
			oldPoint = obj;
		}
	}

	@Override
	public void mouseDragged(Vector2D p)
	{
		p = shape.worldToObject(p);

		Vector2D trans = p.getSubtractedCopy(oldPoint);
		Vector2D halfTrans = trans.getScaledCopy(0.5f);
		float width, height;

		switch (activeHandle)
		{
			case 0:
				shape.translate(trans);
				// topLeft.add(trans);
				// topRight.add(trans);
				// bottomLeft.add(trans);
				// bottomRight.add(trans);
				// rotate.add(trans);
				break;
			case 1:
				topLeft.add(trans);
				topRight.add(0f, trans.y);
				bottomLeft.add(trans.x, 0f);

				shape.translate(halfTrans);

				width = shape.getWidth() - trans.x;
				height = shape.getHeight() - trans.y;

				if (width < 0)
				{
					width *= -1;
					activeHandle = 2;
				}
				if (height < 0)
				{
					height *= -1;
					activeHandle += 2;
				}

				shape.setWidth(width);
				shape.setHeight(height);

				positionHandles();
				break;
			case 2:
				topRight.add(trans);
				topLeft.add(0f, trans.y);
				bottomRight.add(trans.x, 0f);

				shape.translate(halfTrans);

				width = shape.getWidth() + trans.x;
				height = shape.getHeight() - trans.y;

				if (width < 0)
				{
					width *= -1;
					activeHandle = 1;
				}
				if (height < 0)
				{
					height *= -1;
					activeHandle += 2;
				}

				shape.setWidth(width);
				shape.setHeight(height);

				positionHandles();
				break;
			case 3:
				bottomLeft.add(trans);
				topLeft.add(trans.x, 0f);
				bottomRight.add(0f, trans.y);

				shape.translate(halfTrans);

				width = shape.getWidth() - trans.x;
				height = shape.getHeight() + trans.y;

				if (width < 0)
				{
					width *= -1;
					activeHandle = 4;
				}
				if (height < 0)
				{
					height *= -1;
					activeHandle -= 2;
				}

				shape.setWidth(width);
				shape.setHeight(height);

				positionHandles();
				break;
			case 4:
				bottomRight.add(trans);
				topRight.add(trans.x, 0f);
				bottomLeft.add(0f, trans.y);

				shape.translate(halfTrans);

				width = shape.getWidth() + trans.x;
				height = shape.getHeight() + trans.y;

				if (width < 0)
				{
					width *= -1;
					activeHandle = 3;
				}
				if (height < 0)
				{
					height *= -1;
					activeHandle -= 2;
				}

				shape.setWidth(width);
				shape.setHeight(height);

				positionHandles();
				break;
			case 5:
				Vector2D center = shape.getCenter();
				Vector2D before = oldPoint.getSubtractedCopy(center);
				Vector2D after = p.getSubtractedCopy(center);
				float angle = Vector2D.angleBetween(before, after);
				shape.rotate(angle);
				break;
			default:
				return;
		}

		oldPoint = p;
		controller.refresh();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		activeHandle = -1;
	}
}
