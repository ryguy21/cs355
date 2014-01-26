package cs355.solution.model.shapes;

public enum ShapeType
{
	CIRCLE(Circle.class), ELLIPSE(Ellipse.class), LINE(Line.class), RECTANGLE(Rectangle.class), SQUARE(Square.class), TRIANGLE(
			Triangle.class);

	private ShapeType(Class<? extends Shape> c)
	{
		clazz = c;
	}

	public Class<? extends Shape>	clazz;
}
