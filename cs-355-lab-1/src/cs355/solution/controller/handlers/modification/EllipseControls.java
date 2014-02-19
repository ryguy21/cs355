package cs355.solution.controller.handlers.modification;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Ellipse;
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public class EllipseControls extends SelectionControls<Ellipse>
{
	protected final HandleControl	topLeft, topRight, bottomLeft, bottomRight, rotate;

	protected int					activeHandle;
	protected Vector2D				oldPointO;
	protected Vector2D				oldPointW;

	public EllipseControls(IController controller, Ellipse e)
	{
		super(controller, e);

		topLeft = new HandleControl();
		topRight = new HandleControl();
		bottomLeft = new HandleControl();
		bottomRight = new HandleControl();
		rotate = new HandleControl();

		positionHandles();

		activeHandle = -1;
	}

	protected void positionHandles()
	{
		Vector2D tlCorner = shape.getTopLeftCorner();

		float right = tlCorner.x + shape.getxDiameter();
		float bottom = tlCorner.y + shape.getyDiameter();

		topLeft.copyValues(tlCorner);
		topRight.copyValues(right, tlCorner.y);
		bottomLeft.copyValues(tlCorner.x, bottom);
		bottomRight.copyValues(right, bottom);
		rotate.copyValues(0, tlCorner.y);
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
	protected void drawComponents(Graphics2D g, Matrix otov)
	{
		topLeft.draw(g, otov);
		topRight.draw(g, otov);
		bottomLeft.draw(g, otov);
		bottomRight.draw(g, otov);
		drawRotateHandle(rotate, g, otov);

		float x = -shape.getxRadius();
		float y = -shape.getyRadius();

		Vector2D tlc = new Vector2D(x, y);
		tlc.multiply(otov);

		int width = (int) (shape.getxDiameter() * otov.getScaleX());
		int height = (int) (shape.getyDiameter() * otov.getScaleY());

		g.drawArc((int) tlc.x, (int) tlc.y, width, height, 0, 360);
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
		float width = shape.getxDiameter() + (activeHandle % 2 == 1 ? -1 : 1) * transO.x;
		float height = shape.getyDiameter() + (activeHandle <= 2 ? -1 : 1) * transO.y;

		if (width < 0)
		{
			shape.setxRadius(width * -0.5f);

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
			shape.setxRadius(width * 0.5f);

		if (height < 0)
		{
			shape.setyRadius(height * -0.5f);

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
			shape.setyRadius(height * 0.5f);

		shape.translate(halfTransW);

		positionHandles();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		activeHandle = -1;
	}
}
