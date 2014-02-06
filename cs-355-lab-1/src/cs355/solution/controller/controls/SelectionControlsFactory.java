package cs355.solution.controller.controls;

import cs355.solution.controller.interfaces.Control;
import cs355.solution.model.shapes.*;

public abstract class SelectionControlsFactory
{
	public static Control createControls(Shape shape)
	{
		switch (shape.getType())
		{
			case CIRCLE:
				return new CircleControls((Circle) shape);
			case ELLIPSE:
				return new EllipseControls((Ellipse) shape);
			case LINE:
				return new LineControls((Line) shape);
			case RECTANGLE:
				return new RectangleControls((Rectangle) shape);
			case SQUARE:
				return new SquareControls((Square) shape);
			case TRIANGLE:
				return new TriangleControls((Triangle) shape);
			default:
				return null;
		}
	}
}
