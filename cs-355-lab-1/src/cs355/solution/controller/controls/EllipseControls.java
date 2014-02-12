package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Ellipse;
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

		Vector2D tlCorner = shape.getTopLeftCorner();

		float right = tlCorner.x + shape.getxDiameter();
		float bottom = tlCorner.y + shape.getyDiameter();

		topLeft = new HandleControl(tlCorner);
		topRight = new HandleControl(right, tlCorner.y);
		bottomLeft = new HandleControl(tlCorner.x, bottom);
		bottomRight = new HandleControl(right, bottom);

		rotate = new HandleControl(0, -e.getyRadius() - 30f);

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
		topLeft.draw(g);
		topRight.draw(g);
		bottomLeft.draw(g);
		bottomRight.draw(g);
		rotate.draw(g);

		int x = (int) (-shape.getxRadius());
		int y = (int) (-shape.getyRadius());
		int width = (int) shape.getxDiameter();
		int height = (int) shape.getyDiameter();

		g.drawArc(x, y, width, height, 0, 360);
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
