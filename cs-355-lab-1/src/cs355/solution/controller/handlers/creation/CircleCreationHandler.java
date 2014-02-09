package cs355.solution.controller.handlers.creation;

import java.awt.Color;

import cs355.solution.controller.handlers.ShapeCreationHandler;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Circle;
import cs355.solution.util.math.Vector2D;

public class CircleCreationHandler extends ShapeCreationHandler
{
	private Circle		circle;
	private Vector2D	startPoint;

	public CircleCreationHandler(IController controller, Color color)
	{
		super(controller, color);
	}

	@Override
	public void cancel()
	{
		if (circle != null)
		{
			controller.removeShape(circle);
			circle = null;
		}
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		circle = new Circle(color, p, 0f);
		controller.addShape(circle);
		startPoint = p.getCopy();
		controller.refresh();
	}

	@Override
	public void mouseDragged(Vector2D end)
	{
		float diameter = Math.min(Math.abs(startPoint.x - end.x), Math.abs(startPoint.y - end.y));
		float radius = diameter * 0.5f;
		float multiplierX = end.x > startPoint.x ? 0 : -1;
		float multiplierY = end.y > startPoint.y ? 0 : -1;

		Vector2D center = new Vector2D(startPoint.x + diameter * multiplierX + radius, startPoint.y + diameter * multiplierY + radius);

		circle.setCenter(center);
		circle.setRadius(radius);
		controller.refresh();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		mouseDragged(p);
		controller.inputSequenceComplete();
	}
}
