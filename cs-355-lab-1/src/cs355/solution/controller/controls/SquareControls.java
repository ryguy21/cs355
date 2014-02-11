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
	public void mouseDragged(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		Vector2D transW = w.getSubtractedCopy(oldPointW);
		Vector2D transO = o.getSubtractedCopy(oldPointO);

		Vector2D halfTransO = transO.getScaledCopy(0.5f);

		float oldSize = ((Square) shape).getSize();

		switch (activeHandle)
		{
			case 0:
				shape.translate(transW);
				o.subtract(transW);
				break;
			case 1:
				updateSize(bottomRight, o);
				updateLocation(oldSize, w);
				o.subtract(halfTransO);
				break;
			case 2:
				updateSize(bottomLeft, o);
				updateLocation(oldSize, w);
				o.subtract(halfTransO);
				break;
			case 3:
				updateSize(topRight, o);
				updateLocation(oldSize, w);
				o.subtract(halfTransO);
				break;
			case 4:
				updateSize(topLeft, o);
				updateLocation(oldSize, w);
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

	private void updateSize(Vector2D start, Vector2D end)
	{
		float size = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));

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

		updateHandles();
	}

	private void updateLocation(float oldSize, Vector2D w)
	{
		float dsize = (((Square) shape).getSize() - oldSize) * 0.5f;
		Vector2D trans;

		switch (activeHandle)
		{
			case 1:
				trans = new Vector2D(-dsize, -dsize);
				break;
			case 2:
				trans = new Vector2D(dsize, -dsize);
				break;
			case 3:
				trans = new Vector2D(-dsize, dsize);
				break;
			case 4:
				trans = new Vector2D(dsize, dsize);
				break;
			default:
				return;
		}

		trans = ((Square) shape).rotateObjectToWorld(trans);
		shape.translate(trans);
	}

	private void updateHandles()
	{
		float halfWidth = shape.getWidth() * 0.5f;
		float halfHeight = shape.getHeight() * 0.5f;

		topLeft.copyValues(-halfWidth, -halfHeight);
		topRight.copyValues(halfWidth, -halfHeight);
		bottomLeft.copyValues(-halfWidth, halfHeight);
		bottomRight.copyValues(halfWidth, halfHeight);

		rotate.copyValues(0, -halfHeight - 30f);
	}
}
