package cs355.solution.controller;

import java.awt.Color;

import cs355.solution.model.shapes.*;
import cs355.solution.util.math.Vector2D;

public class ShapeCreator
{
	private static abstract class SingletonHolder
	{
		public static final ShapeCreator	INSTANCE	= new ShapeCreator();
	}

	public static ShapeCreator getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private ShapeCreator()
	{}

	public Shape createShape(DrawingState state)
	{
		return createShape(state.getCurrentShape(), state.getColor(), state.getStartPoint());
	}

	public Shape createShape(ShapeType type, Color color, Vector2D startPoint)
	{
		switch (type)
		{
			case CIRCLE:
				return createCircle(color, startPoint);
			case ELLIPSE:
				return createEllipse(color, startPoint);
			case LINE:
				return createLine(color, startPoint);
			case RECTANGLE:
				return createRectangle(color, startPoint);
			case SQUARE:
				return createSquare(color, startPoint);
			case TRIANGLE:
				return createTriangle(color, startPoint);
			default:
				return null;
		}
	}

	public Circle createCircle(Color c, Vector2D startPoint)
	{
		return new Circle(c, startPoint, 0);
	}

	public Ellipse createEllipse(Color c, Vector2D startPoint)
	{
		return new Ellipse(c, startPoint, 0, 0);
	}

	public Line createLine(Color c, Vector2D startPoint)
	{
		return new Line(c, startPoint, startPoint);
	}

	public Rectangle createRectangle(Color c, Vector2D startPoint)
	{
		return new Rectangle(c, startPoint, 0, 0);
	}

	public Square createSquare(Color c, Vector2D startPoint)
	{
		return new Square(c, startPoint, 0);
	}

	public Triangle createTriangle(Color c, Vector2D startPoint)
	{
		return new Triangle(c, startPoint, startPoint, startPoint);
	}

	public void updateShape(DrawingState drawingState, Shape shape)
	{
		switch (shape.getType())
		{
			case CIRCLE:
				updateShape((Circle) shape, drawingState);
				break;
			case ELLIPSE:
				updateShape((Ellipse) shape, drawingState);
				break;
			case LINE:
				updateShape((Line) shape, drawingState);
				break;
			case RECTANGLE:
				updateShape((Rectangle) shape, drawingState);
				break;
			case SQUARE:
				updateShape((Square) shape, drawingState);
				break;
			case TRIANGLE:
				updateShape((Triangle) shape, drawingState);
				break;
			default:
				break;

		}
	}

	public void updateShape(Circle c, DrawingState state)
	{
		Vector2D start = state.getStartPoint();
		Vector2D end = state.getEndPoint();

		float diameter = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		float radius = diameter * 0.5f;
		float multiplierX = end.x > start.x ? 0 : -1;
		float multiplierY = end.y > start.y ? 0 : -1;

		Vector2D center = new Vector2D(start.x + diameter * multiplierX + radius, start.y + diameter * multiplierY + radius);

		c.setCenter(center);
		c.setRadius(radius);
	}

	public void updateShape(Ellipse e, DrawingState state)
	{
		Vector2D start = state.getStartPoint();
		Vector2D end = state.getEndPoint();

		Vector2D center = Vector2D.average(start, end);
		float radX = Math.abs(start.x - end.x) * 0.5f;
		float radY = Math.abs(start.y - end.y) * 0.5f;

		e.setCenter(center);
		e.setxRadius(radX);
		e.setyRadius(radY);
	}

	public void updateShape(Line l, DrawingState state)
	{
		l.setEndPoint(state.getEndPoint());
	}

	public void updateShape(Rectangle r, DrawingState state)
	{
		Vector2D start = state.getStartPoint();
		Vector2D end = state.getEndPoint();

		float leftX = Math.min(start.x, end.x);
		float topY = Math.min(start.y, end.y);
		float width = Math.abs(start.x - end.x);
		float height = Math.abs(start.y - end.y);

		r.setTopLeftCorner(new Vector2D(leftX, topY));
		r.setWidth(width);
		r.setHeight(height);
	}

	public void updateShape(Square s, DrawingState state)
	{
		Vector2D start = state.getStartPoint();
		Vector2D end = state.getEndPoint();

		float size = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		float multiplierX = end.x > start.x ? 0 : -1;
		float multiplierY = end.y > start.y ? 0 : -1;

		Vector2D topLeftCorner = new Vector2D(start.x + size * multiplierX, start.y + size * multiplierY);

		s.setTopLeftCorner(topLeftCorner);
		s.setSize(size);
	}

	public void updateShape(Triangle t, DrawingState state)
	{
		Vector2D p2 = state.getIntermediatePoint();
		Vector2D p3 = state.getEndPoint();

		if (p2 != null)
			t.setPoint2(p2);

		if (p3 != null)
			t.setPoint3(p3);
	}
}
