package cs355.solution.controller.handlers.creation;

import java.awt.Color;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Rectangle;
import cs355.solution.util.math.Vector2D;

public class RectangleCreationHandler extends ShapeCreationHandler
{
	private Rectangle	rectangle;
	private Vector2D	startPoint;

	public RectangleCreationHandler(IController controller, Color color)
	{
		super(controller, color);
	}

	@Override
	public void cancel()
	{
		if (rectangle != null)
		{
			controller.removeShape(rectangle);
			rectangle = null;
		}
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		rectangle = new Rectangle(color, p, 0f, 0f);
		controller.addShape(rectangle);
		startPoint = p;
		controller.refresh();
	}

	@Override
	public void mouseDragged(Vector2D end)
	{
		Vector2D center = Vector2D.average(startPoint, end);
		float width = Math.abs(startPoint.x - end.x);
		float height = Math.abs(startPoint.y - end.y);

		rectangle.setCenter(center);
		rectangle.setWidth(width);
		rectangle.setHeight(height);
		controller.refresh();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		mouseDragged(p);
		controller.inputSequenceComplete();
	}
}
