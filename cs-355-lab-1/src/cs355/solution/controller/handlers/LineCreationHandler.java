package cs355.solution.controller.handlers;

import java.awt.Color;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Line;
import cs355.solution.util.math.Vector2D;

public class LineCreationHandler extends ShapeCreationHandler
{
	private Line	line;

	public LineCreationHandler(IController controller, Color color)
	{
		super(controller, color);
	}

	@Override
	public void cancel()
	{
		if (line != null)
		{
			controller.removeShape(line);
			line = null;
		}
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		line = new Line(color, p, p);
		controller.addShape(line);
		controller.refresh();
	}

	@Override
	public void mouseDragged(Vector2D p)
	{
		line.setEndPoint(p);
		controller.refresh();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		line.setEndPoint(p);
		controller.inputSequenceComplete();
		controller.refresh();
	}
}
