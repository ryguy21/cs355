package cs355.solution.controller.handlers.modification;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Triangle;
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public class TriangleControls extends SelectionControls<Triangle>
{
	private final HandleControl	handle1, handle2, handle3, rotate;

	private int					activeHandle;
	private Vector2D			oldPointW, oldPointO;

	public TriangleControls(IController controller, Triangle triangle)
	{
		super(controller, triangle);

		handle1 = new HandleControl();
		handle2 = new HandleControl();
		handle3 = new HandleControl();
		rotate = new HandleControl();

		positionHandles();

		activeHandle = -1;
	}

	private void positionHandles()
	{
		handle1.copyValues(shape.getPoint1());
		handle2.copyValues(shape.getPoint2());
		handle3.copyValues(shape.getPoint3());

		float minY = handle1.y;
		if (handle2.y < minY)
			minY = handle2.y;
		if (handle3.y < minY)
			minY = handle3.y;

		rotate.copyValues(0, minY);
	}

	@Override
	public void drawComponents(Graphics2D g, Matrix otov)
	{
		handle1.draw(g, otov);
		handle2.draw(g, otov);
		handle3.draw(g, otov);
		drawRotateHandle(rotate, g, otov);

		Vector2D p1 = handle1.getMultipliedCopy(otov);
		Vector2D p2 = handle2.getMultipliedCopy(otov);
		Vector2D p3 = handle3.getMultipliedCopy(otov);

		int[] xs = new int[3];
		int[] ys = new int[3];

		xs[0] = (int) p1.x;
		xs[1] = (int) p2.x;
		xs[2] = (int) p3.x;

		ys[0] = (int) p1.y;
		ys[1] = (int) p2.y;
		ys[2] = (int) p3.y;

		g.drawPolygon(xs, ys, 3);
	}

	@Override
	public boolean contains(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		if (handle1.contains(o))
			return true;
		else if (handle2.contains(o))
			return true;
		else if (handle3.contains(o))
			return true;
		else if (rotate.contains(o))
			return true;
		else if (shape.contains(w))
			return true;
		else
			return false;
	}

	@Override
	public void mousePressed(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		if (handle1.contains(o))
		{
			activeHandle = 1;
			oldPointW = w;
			oldPointO = o;
		}
		else if (handle2.contains(o))
		{
			activeHandle = 2;
			oldPointW = w;
			oldPointO = o;
		}
		else if (handle3.contains(o))
		{
			activeHandle = 3;
			oldPointW = w;
			oldPointO = o;
		}
		else if (rotate.contains(o))
		{
			activeHandle = 4;
			oldPointW = w;
			oldPointO = o;
		}
		else if (shape.contains(w))
		{
			activeHandle = 0;
			oldPointW = w;
			oldPointO = o;
		}
	}

	@Override
	public void mouseDragged(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		Vector2D transW = w.getSubtractedCopy(oldPointW);
		Vector2D transO = o.getSubtractedCopy(oldPointO);

		switch (activeHandle)
		{
			case 0:
				shape.translate(transW);
				break;
			case 1:
				shape.translatePoint1(transO);

				positionHandles();
				oldPointO = handle1.getCopy();
				break;
			case 2:
				shape.translatePoint2(transO);

				positionHandles();
				oldPointO = handle2.getCopy();
				break;
			case 3:
				shape.translatePoint3(transO);

				positionHandles();
				oldPointO = handle3.getCopy();
				break;
			case 4:
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

	@Override
	public void mouseReleased(Vector2D p)
	{
		activeHandle = -1;
	}
}
