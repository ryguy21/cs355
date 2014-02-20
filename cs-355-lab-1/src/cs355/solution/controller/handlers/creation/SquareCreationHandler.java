package cs355.solution.controller.handlers.creation;

import java.awt.Color;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Square;
import cs355.solution.util.math.Vector2D;

public class SquareCreationHandler extends ShapeCreationHandler
{
	private Square		square;
	private Vector2D	start;

	public SquareCreationHandler(IController controller, Color color)
	{
		super(controller, color);
	}

	@Override
	public void cancel()
	{
		if (square != null)
		{
			controller.removeShape(square);
			square = null;
		}
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		square = new Square(color, p, 0f);
		controller.addShape(square);
		start = p;
		controller.refresh();
	}

	@Override
	public void mouseDragged(Vector2D end)
	{
		float size = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		float half_size = size * 0.5f;
		float multiplierX = end.x > start.x ? 0 : -1;
		float multiplierY = end.y > start.y ? 0 : -1;

		Vector2D center = new Vector2D(start.x + size * multiplierX + half_size, start.y + size * multiplierY + half_size);

		square.setCenter(center);
		square.setSize(size);
		controller.refresh();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		mouseDragged(p);
		controller.inputSequenceComplete();
	}
}
