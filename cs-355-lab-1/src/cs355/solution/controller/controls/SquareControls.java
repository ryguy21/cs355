package cs355.solution.controller.controls;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Square;
import cs355.solution.util.math.Vector2D;

public class SquareControls extends RectangleControls
{
	public SquareControls(IController controller, Square s)
	{
		super(controller, s);
	}

	@Override
	public void mouseDragged(Vector2D p)
	{
		Vector2D trans = p.getSubtractedCopy(oldPoint);

		switch (activeHandle)
		{
			case 0:
				shape.translate(trans);
				topLeft.add(trans);
				topRight.add(trans);
				bottomLeft.add(trans);
				bottomRight.add(trans);
				rotate.add(trans);
				break;
			case 1:
				update(bottomRight, p);
				updateHandles();
				break;
			case 2:
				update(bottomLeft, p);
				updateHandles();
				break;
			case 3:
				update(topRight, p);
				updateHandles();
				break;
			case 4:
				update(topLeft, p);
				updateHandles();
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

	private void update(Vector2D start, Vector2D end)
	{
		float size = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		float half_size = size * 0.5f;
		float multiplierX = end.x > start.x ? 0 : -1;
		float multiplierY = end.y > start.y ? 0 : -1;

		Vector2D center = new Vector2D(start.x + size * multiplierX + half_size, start.y + size * multiplierY + half_size);

		shape.setCenter(center);
		((Square) shape).setSize(size);

		if (activeHandle % 2 == 1 && end.x > start.x)
		{
			activeHandle++;
		}
		else if (activeHandle % 2 == 0 && end.x < start.x)
		{
			activeHandle--;
		}
		if (activeHandle <= 2 && end.y > start.y)
		{
			activeHandle += 2;
		}
		else if (activeHandle > 2 && end.y < start.y)
		{
			activeHandle -= 2;
		}
	}

	private void updateHandles()
	{
		Vector2D tlCorner = shape.getTopLeftCorner();

		float right = tlCorner.x + shape.getWidth();
		float bottom = tlCorner.y + shape.getHeight();

		topLeft.copyValues(tlCorner);
		topRight.copyValues(right, tlCorner.y);
		bottomLeft.copyValues(tlCorner.x, bottom);
		bottomRight.copyValues(right, bottom);

		rotate.copyValues(shape.getCenter().x, tlCorner.y - 30f);
	}
}
