package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Ellipse;
import cs355.solution.util.math.Vector2D;

public class EllipseControls extends SelectionControls<Ellipse>
{
	private final HandleControl	rotate;

	public EllipseControls(IController controller, Ellipse e)
	{
		super(controller, e);

		rotate = new HandleControl(e.getCenter().subtract(0, e.getyRadius() + 30));
	}

	@Override
	public boolean contains(Vector2D p)
	{
		if (shape.contains(p))
			return true;
		else if (rotate.contains(p))
			return true;
		else
			return false;
	}

	@Override
	protected void drawComponents(Graphics2D g)
	{
		rotate.draw(g);

		Vector2D center = shape.getCenter();

		int x = (int) (center.x - shape.getxRadius());
		int y = (int) (center.y - shape.getyRadius());
		int width = (int) shape.getxDiameter();
		int height = (int) shape.getyDiameter();

		g.drawArc(x, y, width, height, 0, 360);
	}
}
