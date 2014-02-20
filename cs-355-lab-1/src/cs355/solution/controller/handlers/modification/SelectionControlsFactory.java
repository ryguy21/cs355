package cs355.solution.controller.handlers.modification;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.*;

public abstract class SelectionControlsFactory
{
	public static SelectionControls<?> createControl(IController controller, Shape shape)
	{
		switch (shape.getType())
		{
			case CIRCLE:
				return new CircleControls(controller, (Circle) shape);
			case ELLIPSE:
				return new EllipseControls(controller, (Ellipse) shape);
			case LINE:
				return new LineControls(controller, (Line) shape);
			case RECTANGLE:
				return new RectangleControls(controller, (Rectangle) shape);
			case SQUARE:
				return new SquareControls(controller, (Square) shape);
			case TRIANGLE:
				return new TriangleControls(controller, (Triangle) shape);
			default:
				return null;
		}
	}
}
