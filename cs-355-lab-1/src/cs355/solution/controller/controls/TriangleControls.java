package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Triangle;
import cs355.solution.util.math.Vector2D;

public class TriangleControls extends SelectionControls<Triangle>
{
	private final HandleControl	handle1, handle2, handle3, rHandle;

	private int					activeHandle;
	private Vector2D			oldPointW, oldPointO;

	public TriangleControls(IController controller, Triangle triangle)
	{
		super(controller, triangle);

		Vector2D center = triangle.getCenter();
		handle1 = new HandleControl(triangle.getPoint1());
		handle2 = new HandleControl(triangle.getPoint2());
		handle3 = new HandleControl(triangle.getPoint3());
		rHandle = new HandleControl(center);

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

		rHandle.copyValues(0, minY - 30f);
	}

	@Override
	public void drawComponents(Graphics2D g)
	{
		handle1.draw(g);
		handle2.draw(g);
		handle3.draw(g);
		rHandle.draw(g);

		int[] xs = new int[3];
		int[] ys = new int[3];

		xs[0] = (int) handle1.x;
		xs[1] = (int) handle2.x;
		xs[2] = (int) handle3.x;

		ys[0] = (int) handle1.y;
		ys[1] = (int) handle2.y;
		ys[2] = (int) handle3.y;

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
		else if (rHandle.contains(o))
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
		else if (rHandle.contains(o))
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
