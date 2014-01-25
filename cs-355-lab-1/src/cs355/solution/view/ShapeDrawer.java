package cs355.solution.view;

import java.awt.Graphics2D;

import cs355.solution.model.*;

public class ShapeDrawer implements IShapeDrawer
{
	private abstract static class SingletonHolder
	{
		public static ShapeDrawer	INSTANCE	= new ShapeDrawer();
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
			case ELLIPSE:
				drawEllipse((Ellipse) shape, g);
			case LINE:
				drawLine((Line) shape, g);
			case RECTANGLE:
				drawRectangle((Rectangle) shape, g);
			case SQUARE:
				drawSquare((Square) shape, g);
			case TRIANGLE:
				drawTriangle((Triangle) shape, g);
		}
	}

	@Override
	public void drawCircle(Circle c, Graphics2D g)
	{

	}

	@Override
	public void drawEllipse(Ellipse e, Graphics2D g)
	{

	}

	@Override
	public void drawLine(Line l, Graphics2D g)
	{

	}

	@Override
	public void drawRectangle(Rectangle r, Graphics2D g)
	{

	}

	@Override
	public void drawSquare(Square s, Graphics2D g)
	{

	}

	@Override
	public void drawTriangle(Triangle t, Graphics2D g)
	{

	}
}
