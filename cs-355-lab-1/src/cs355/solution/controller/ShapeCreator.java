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
				updateCircle((Circle) shape, drawingState);
				break;
			case ELLIPSE:
				updateEllipse((Ellipse) shape, drawingState);
				break;
			case LINE:
				updateLine((Line) shape, drawingState);
				break;
			case RECTANGLE:
				updateRectangle((Rectangle) shape, drawingState);
				break;
			case SQUARE:
				updateSquare((Square) shape, drawingState);
				break;
			case TRIANGLE:
				updateTriangle((Triangle) shape, drawingState);
				break;
			default:
				break;

		}
	}

	public void updateCircle(Circle c, DrawingState state)
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

	public void updateEllipse(Ellipse e, DrawingState state)
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

	public void updateLine(Line l, DrawingState state)
	{
		l.setEndPoint(state.getEndPoint());
	}

	public void updateRectangle(Rectangle r, DrawingState state)
	{
		Vector2D start = state.getStartPoint();
		Vector2D end = state.getEndPoint();

		Vector2D center = Vector2D.average(start, end);
		float width = Math.abs(start.x - end.x);
		float height = Math.abs(start.y - end.y);

		r.setCenter(center);
		r.setWidth(width);
		r.setHeight(height);
	}

	public void updateSquare(Square s, DrawingState state)
	{
		Vector2D start = state.getStartPoint();
		Vector2D end = state.getEndPoint();

		float size = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		float half_size = size * 0.5f;
		float multiplierX = end.x > start.x ? 0 : -1;
		float multiplierY = end.y > start.y ? 0 : -1;

		Vector2D center = new Vector2D(start.x + size * multiplierX + half_size, start.y + size * multiplierY + half_size);

		s.setCenter(center);
		s.setSize(size);
	}

	public void updateTriangle(Triangle t, DrawingState state)
	{
		Vector2D p2 = state.getIntermediatePoint();
		Vector2D p3 = state.getEndPoint();

		if (p2 != null)
			t.setPoint2(p2);

		if (p3 != null)
			t.setPoint3(p3);
	}
}
