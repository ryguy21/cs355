package cs355.solution.controller.controls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import cs355.solution.controller.interfaces.Control;
import cs355.solution.model.shapes.Triangle;
import cs355.solution.util.math.Vector2D;

public class TriangleControls implements Control
{
	private static final Stroke	STROKE	= new BasicStroke(1.5f);

	private final SelectionControl	p1control, p2control, p3control;

	public TriangleControls(Triangle t)
	{
		Vector2D center = t.getCenter();
		p1control = new SelectionControl(t.getPoint1().add(center));
		p2control = new SelectionControl(t.getPoint2().add(center));
		p3control = new SelectionControl(t.getPoint3().add(center));
	}

	@Override
	public void draw(Graphics2D g)
	{
		Stroke stroke = g.getStroke();
		g.setColor(Color.cyan);

		p1control.draw(g);
		p2control.draw(g);
		p3control.draw(g);

		g.setStroke(STROKE);

		int[] xs = new int[3];
		int[] ys = new int[3];

		xs[0] = (int) p1control.x;
		xs[1] = (int) p2control.x;
		xs[2] = (int) p3control.x;

		ys[0] = (int) p1control.y;
		ys[1] = (int) p2control.y;
		ys[2] = (int) p3control.y;

		g.drawPolygon(xs, ys, 3);

		g.setStroke(stroke);
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
		else
			return false;
	}
}
