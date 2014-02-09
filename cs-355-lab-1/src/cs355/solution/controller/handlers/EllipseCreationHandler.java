package cs355.solution.controller.handlers;

import java.awt.Color;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Ellipse;
import cs355.solution.util.math.Vector2D;

public class EllipseCreationHandler extends ShapeCreationHandler
{
	private Ellipse		ellipse;
	private Vector2D	startPoint;

	public EllipseCreationHandler(IController controller, Color color)
	{
		super(controller, color);
	}

	@Override
	public void cancel()
	{
		if (ellipse != null)
		{
			controller.removeShape(ellipse);
			ellipse = null;
		}
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		ellipse = new Ellipse(color, p, 0f, 0f);
		controller.addShape(ellipse);
		startPoint = p;
		controller.refresh();
	}

	@Override
	public void mouseDragged(Vector2D end)
	{
		Vector2D center = Vector2D.average(startPoint, end);
		float radX = Math.abs(startPoint.x - end.x) * 0.5f;
		float radY = Math.abs(startPoint.y - end.y) * 0.5f;

		ellipse.setCenter(center);
		ellipse.setxRadius(radX);
		ellipse.setyRadius(radY);
		controller.refresh();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		mouseDragged(p);
		controller.inputSequenceComplete();
	}
}
