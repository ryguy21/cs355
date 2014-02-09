package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Circle;
import cs355.solution.util.math.Vector2D;

public class CircleControls extends SelectionControls<Circle>
{
	public CircleControls(IController controller, Circle circle)
	{
		super(controller, circle);
	}

	@Override
	public void drawComponents(Graphics2D g)
	{
		Vector2D center = shape.getCenter();

		int x = (int) (center.x - shape.getRadius());
		int y = (int) (center.y - shape.getRadius());
		int size = (int) shape.getDiameter();

		g.drawArc(x, y, size, size, 0, 360);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		return shape.contains(p);
	}
}
