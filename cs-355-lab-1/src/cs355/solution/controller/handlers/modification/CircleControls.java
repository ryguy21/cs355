package cs355.solution.controller.handlers.modification;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Circle;
import cs355.solution.util.math.Vector2D;

public class CircleControls extends EllipseControls
{
	public CircleControls(IController controller, Circle circle)
	{
		super(controller, circle);
	}

	@Override
	public void mouseDragged(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		Vector2D transW = w.getSubtractedCopy(oldPointW);
		Vector2D transO = o.getSubtractedCopy(oldPointO);

		Vector2D halfTransO = transO.getScaledCopy(0.5f);

		switch (activeHandle)
		{
			case 0:
				shape.translate(transW);
				o.subtract(transW);
				break;
			case 1:
				update(bottomRight, o);
				o.subtract(halfTransO);
				break;
			case 2:
				update(bottomLeft, o);
				o.subtract(halfTransO);
				break;
			case 3:
				update(topRight, o);
				o.subtract(halfTransO);
				break;
			case 4:
				update(topLeft, o);
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
		controller.refresh();
	}

	private void update(Vector2D start, Vector2D end)
	{
		float oldSize = ((Circle) shape).getDiameter();
		float size = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));

		((Circle) shape).setRadius(size * 0.5f);

		float diff = (size - oldSize) * 0.5f;
		Vector2D trans = null;

		switch (activeHandle)
		{
			case 1:
				trans = new Vector2D(-diff, -diff);
				break;
			case 2:
				trans = new Vector2D(diff, -diff);
				break;
			case 3:
				trans = new Vector2D(-diff, diff);
				break;
			case 4:
				trans = new Vector2D(diff, diff);
				break;
			default:
				return;
		}

		if (activeHandle % 2 == 1 && end.x > start.x)
		{
			activeHandle++;
			trans.x += size;
		}
		else if (activeHandle % 2 == 0 && end.x < start.x)
		{
			activeHandle--;
			trans.x -= size;
		}
		if (activeHandle <= 2 && end.y > start.y)
		{
			activeHandle += 2;
			trans.y += size;
		}
		else if (activeHandle > 2 && end.y < start.y)
		{
			activeHandle -= 2;
			trans.y -= size;
		}

		trans = shape.rotateObjectToWorld(trans);
		shape.translate(trans);

		updateHandles();
	}

	private void updateHandles()
	{
		float halfWidth = shape.getxDiameter() * 0.5f;
		float halfHeight = shape.getyDiameter() * 0.5f;

		topLeft.copyValues(-halfWidth, -halfHeight);
		topRight.copyValues(halfWidth, -halfHeight);
		bottomLeft.copyValues(-halfWidth, halfHeight);
		bottomRight.copyValues(halfWidth, halfHeight);

		rotate.copyValues(0, -halfHeight - 30f);
	}
}
