package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.model.shapes.Triangle;
import cs355.solution.util.math.Vector2D;

public class TriangleControls extends SelectionControls<Triangle>
{
	private final HandleControl	p1control, p2control, p3control, rControl;

	public TriangleControls(Triangle triangle)
	{
		super(triangle);

		Vector2D center = triangle.getCenter();
		p1control = new HandleControl(triangle.getPoint1());
		p2control = new HandleControl(triangle.getPoint2());
		p3control = new HandleControl(triangle.getPoint3());

		float minY = p1control.y;
		if (p2control.y < minY)
			minY = p2control.y;
		if (p3control.y < minY)
			minY = p3control.y;

		center.y = minY - 30f;

		rControl = new HandleControl(center);
	}

	@Override
	public void drawComponents(Graphics2D g)
	{
		p1control.draw(g);
		p2control.draw(g);
		p3control.draw(g);
		rControl.draw(g);

		int[] xs = new int[3];
		int[] ys = new int[3];

		xs[0] = (int) p1control.x;
		xs[1] = (int) p2control.x;
		xs[2] = (int) p3control.x;

		ys[0] = (int) p1control.y;
		ys[1] = (int) p2control.y;
		ys[2] = (int) p3control.y;

		g.drawPolygon(xs, ys, 3);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		if (p1control.contains(p))
			return true;
		else if (p2control.contains(p))
			return true;
		else if (p3control.contains(p))
			return true;
		else if (rControl.contains(p))
			return true;
		else
			return false;
	}
}
