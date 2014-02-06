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
	private static final Stroke	rectangleStroke;
	private static final Stroke	lineStroke;
	private static final Stroke	basicStroke;

	static
	{
		rectangleStroke = new BasicStroke(2.5f);
		lineStroke = new BasicStroke(1.5f);
		basicStroke = new BasicStroke(1f);
	}

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
		g.setColor(Color.cyan);
		g.setStroke(rectangleStroke);

		g.drawArc((int) p1control.x, (int) p1control.y - 4, 8, 8, 0, 360);
		// g.drawRect((int) p1control.x - 4, (int) p1control.y - 4, 8, 8);
		g.drawRect((int) p2control.x - 4, (int) p2control.y - 4, 8, 8);
		g.drawRect((int) p3control.x - 4, (int) p3control.y - 4, 8, 8);

		g.setStroke(lineStroke);

		int[] xs = new int[3];
		int[] ys = new int[3];

		xs[0] = (int) p1control.x;
		xs[1] = (int) p2control.x;
		xs[2] = (int) p3control.x;

		ys[0] = (int) p1control.y;
		ys[1] = (int) p2control.y;
		ys[2] = (int) p3control.y;

		g.drawPolygon(xs, ys, 3);

		g.setStroke(basicStroke);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		return false;
	}
}
