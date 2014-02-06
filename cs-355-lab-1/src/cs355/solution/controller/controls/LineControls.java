package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.model.shapes.Line;
import cs355.solution.util.math.Vector2D;

public class LineControls extends SelectionControls<Line>
{
	private final HandleControl	handle1, handle2;

	public LineControls(Line l)
	{
		super(l);

		handle1 = new HandleControl(l.getStartPoint());
		handle2 = new HandleControl(l.getEndPoint());
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
}
