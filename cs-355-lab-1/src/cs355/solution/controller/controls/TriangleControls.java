package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Triangle;
import cs355.solution.util.math.Vector2D;

public class TriangleControls extends SelectionControls<Triangle>
{
	private final HandleControl	handle1, handle2, handle3, rHandle;

	private int					activeHandle;
	private Vector2D			oldPoint;

	public TriangleControls(IController controller, Triangle triangle)
	{
		super(controller, triangle);

		Vector2D center = triangle.getCenter();
		handle1 = new HandleControl(triangle.getPoint1());
		handle2 = new HandleControl(triangle.getPoint2());
		handle3 = new HandleControl(triangle.getPoint3());
		rHandle = new HandleControl(center);

		positionRotateHandle();

		activeHandle = -1;
	}

	private void positionRotateHandle()
	{
		float minY = handle1.y;
		if (handle2.y < minY)
			minY = handle2.y;
		if (handle3.y < minY)
			minY = handle3.y;

		rHandle.copyValues(shape.getCenter().x, minY - 30f);
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
	public boolean contains(Vector2D p)
	{
		if (handle1.contains(p))
			return true;
		else if (handle2.contains(p))
			return true;
		else if (handle3.contains(p))
			return true;
		else if (rHandle.contains(p))
			return true;
		else if (shape.contains(p))
			return true;
		else
			return false;
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		if (handle1.contains(p))
		{
			activeHandle = 1;
			oldPoint = p;
		}
		else if (handle2.contains(p))
		{
			activeHandle = 2;
			oldPoint = p;
		}
		else if (handle3.contains(p))
		{
			activeHandle = 3;
			oldPoint = p;
		}
		else if (rHandle.contains(p))
		{
			activeHandle = 4;
			oldPoint = p;
		}
		else if (shape.contains(p))
		{
			activeHandle = 0;
			oldPoint = p;
		}
	}

	@Override
	public void mouseDragged(Vector2D p)
	{
		Vector2D trans;

		switch (activeHandle)
		{
			case 0:
				trans = p.getSubtractedCopy(oldPoint);
				shape.translate(trans);
				handle1.add(trans);
				handle2.add(trans);
				handle3.add(trans);
				rHandle.add(trans);
				oldPoint = p;

				controller.refresh();
				break;
			case 1:
				trans = p.getSubtractedCopy(oldPoint);
				shape.translatePoint1(trans);
				handle1.add(trans);
				oldPoint = p;

				positionRotateHandle();
				controller.refresh();
				break;
			case 2:
				trans = p.getSubtractedCopy(oldPoint);
				shape.translatePoint2(trans);
				handle2.add(trans);
				oldPoint = p;

				positionRotateHandle();
				controller.refresh();
				break;
			case 3:
				trans = p.getSubtractedCopy(oldPoint);
				shape.translatePoint3(trans);
				handle3.add(trans);
				oldPoint = p;

				positionRotateHandle();
				controller.refresh();
				break;
			case 4:

				break;
			default:
				return;
		}
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		activeHandle = -1;
	}
}
