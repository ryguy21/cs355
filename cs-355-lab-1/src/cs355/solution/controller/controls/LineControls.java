package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Line;
import cs355.solution.util.math.Vector2D;

public class LineControls extends SelectionControls<Line>
{
	private final HandleControl	handle1, handle2;

	private int					activeHandle;
	private Vector2D			oldPoint;

	public LineControls(IController controller, Line l)
	{
		super(controller, l);

		handle1 = new HandleControl(l.getStartPoint());
		handle2 = new HandleControl(l.getEndPoint());

		activeHandle = -1;
	}

	@Override
	public boolean contains(Vector2D p)
	{
		if (shape.contains(p))
			return true;
		else if (handle1.contains(p))
			return true;
		else if (handle2.contains(p))
			return true;
		else
			return false;
	}

	@Override
	protected void drawComponents(Graphics2D g)
	{
		handle1.draw(g);
		handle2.draw(g);
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		if (handle1.contains(p))
		{
			activeHandle = 1;
			oldPoint = p;
		}
		else if (handle2.contains(p))
		{
			activeHandle = 2;
			oldPoint = p;
		}
		else if (shape.contains(p))
		{
			activeHandle = 0;
			oldPoint = p;
		}
	}

	@Override
	public void mouseDragged(Vector2D p)
	{
		Vector2D trans;

		switch (activeHandle)
		{
			case 0:
				trans = p.getSubtractedCopy(oldPoint);
				shape.translateStartPoint(trans);
				shape.translateEndPoint(trans);
				handle1.add(trans);
				handle2.add(trans);
			case 1:
				trans = p.getSubtractedCopy(oldPoint);
				shape.translateStartPoint(trans);
				handle1.add(trans);
				break;
			case 2:
				trans = p.getSubtractedCopy(oldPoint);
				shape.translateEndPoint(trans);
				handle2.add(trans);
				break;
			default:
				return;
		}

		oldPoint = p;
		controller.refresh();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		activeHandle = -1;
	}
}
