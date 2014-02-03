package cs355.solution.view;

import java.awt.Graphics2D;

import cs355.solution.model.shapes.*;
import cs355.solution.util.math.Vector2D;

public class ShapeDrawer implements IShapeDrawer
{
	private abstract static class SingletonHolder
	{
		public static final ShapeDrawer	INSTANCE	= new ShapeDrawer();
	}

	private ShapeDrawer()
	{}

	public static ShapeDrawer getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	@Override
	public void drawShape(Shape shape, Graphics2D g)
	{
		switch (shape.getType())
		{
			case CIRCLE:
				drawCircle((Circle) shape, g);
				break;
			case ELLIPSE:
				drawEllipse((Ellipse) shape, g);
				break;
			case LINE:
				drawLine((Line) shape, g);
				break;
			case RECTANGLE:
				drawRectangle((Rectangle) shape, g);
				break;
			case SQUARE:
				drawSquare((Square) shape, g);
				break;
			case TRIANGLE:
				drawTriangle((Triangle) shape, g);
		}
	}

	@Override
	public void drawCircle(Circle c, Graphics2D g)
	{
		drawEllipse(c, g);
	}

	@Override
	public void drawEllipse(Ellipse e, Graphics2D g)
	{
		g.setPaint(e.getColor());

		Vector2D tlc = e.getTopLeftCorner();

		g.fillOval((int) tlc.x, (int) tlc.y, (int) e.getxDiameter(), (int) e.getyDiameter());
	}

	@Override
	public void drawLine(Line l, Graphics2D g)
	{
		g.setPaint(l.getColor());

		Vector2D start = l.getStartPoint();
		Vector2D end = l.getEndPoint();

		g.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
	}

	@Override
	public void drawRectangle(Rectangle r, Graphics2D g)
	{
		g.setPaint(r.getColor());

		Vector2D tlc = r.getTopLeftCorner();

		g.fillRect((int) tlc.x, (int) tlc.y, (int) r.getWidth(), (int) r.getHeight());
	}

	@Override
	public void drawSquare(Square s, Graphics2D g)
	{
		drawRectangle(s, g);
	}

	@Override
	public void drawTriangle(Triangle t, Graphics2D g)
	{
		g.setPaint(t.getColor());

		int[] xs = new int[3];
		int[] ys = new int[3];

		Vector2D c = t.getCenter();

		Vector2D p1 = t.getPoint1().add(c);
		Vector2D p2 = t.getPoint2().add(c);
		Vector2D p3 = t.getPoint3().add(c);

		xs[0] = (int) p1.x;
		xs[1] = (int) p2.x;
		xs[2] = (int) p3.x;

		ys[0] = (int) p1.y;
		ys[1] = (int) p2.y;
		ys[2] = (int) p3.y;

		if (p1.equals(p2) || p2.equals(p3) || p3.equals(p1))
			g.drawPolygon(xs, ys, 3);
		else
			g.fillPolygon(xs, ys, 3);
	}
}
