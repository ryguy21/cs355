package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Circle;
import cs355.solution.util.math.Vector2D;

public class CircleControls extends SelectionControls<Circle>
{
	private Vector2D	oldPoint;

	public CircleControls(IController controller, Circle circle)
	{
		super(controller, circle);
	}

	@Override
	public void drawComponents(Graphics2D g)
	{
		int x = (int) (-shape.getRadius());
		int y = (int) (-shape.getRadius());
		int size = (int) shape.getDiameter();

		g.drawArc(x, y, size, size, 0, 360);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		return shape.contains(p);
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		if (shape.contains(p))
		{
			oldPoint = p;
		}
	}

	@Override
	public void mouseDragged(Vector2D p)
	{
		if (oldPoint != null)
		{
			Vector2D trans = p.getSubtractedCopy(oldPoint);
			shape.translate(trans);
			oldPoint = p;

			controller.refresh();
		}
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		mouseDragged(p);
		oldPoint = null;
	}
}
