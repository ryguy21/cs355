package cs355.solution.controller.handlers.creation;

import java.awt.Color;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Triangle;
import cs355.solution.util.math.Vector2D;

public class TriangleCreationHandler extends ShapeCreationHandler
{
	private Triangle	triangle;

	private State		state;

	private enum State
	{
		NO_POINTS, ONE_POINT, TWO_POINTS, DONE
	}

	public TriangleCreationHandler(IController controller, Color color)
	{
		super(controller, color);

		state = State.NO_POINTS;
	}

	@Override
	public void cancel()
	{
		if (triangle != null)
		{
			controller.removeShape(triangle);
			triangle = null;
		}
	}

	@Override
	public void mouseClicked(Vector2D p)
	{
		switch (state)
		{
			case NO_POINTS:
				triangle = new Triangle(color, p, p, p);
				controller.addShape(triangle);
				state = State.ONE_POINT;
				break;
			case ONE_POINT:
				triangle.setPoint2(p);
				state = State.TWO_POINTS;
				break;
			case TWO_POINTS:
				triangle.setPoint3(p);
				state = State.DONE;
				controller.inputSequenceComplete();
				break;
			default:
				return;
		}
		controller.refresh();
	}

	@Override
	public void mouseMoved(Vector2D p)
	{
		switch (state)
		{
			case ONE_POINT:
				triangle.setPoint2(p);
				break;
			case TWO_POINTS:
				triangle.setPoint3(p);
				break;
			default:
				return;
		}
		controller.refresh();
	}
}
