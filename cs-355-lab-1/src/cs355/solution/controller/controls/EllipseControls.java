package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Ellipse;
import cs355.solution.util.math.Vector2D;

public class EllipseControls extends SelectionControls<Ellipse>
{
	private final HandleControl	rotate;

	private int					activeHandle;
	private Vector2D			oldPoint;

	public EllipseControls(IController controller, Ellipse e)
	{
		super(controller, e);

		rotate = new HandleControl(e.getCenter().subtract(0, e.getyRadius() + 30));

		activeHandle = -1;
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

	@Override
	public void mousePressed(Vector2D p)
	{
		if (rotate.contains(p))
		{
			activeHandle = 1;
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
		switch (activeHandle)
		{
			case 1:
				Vector2D center = shape.getCenter();
				Vector2D before = oldPoint.getSubtractedCopy(center);
				Vector2D after = p.getSubtractedCopy(center);
				float angle = Vector2D.angleBetween(before, after);
				shape.rotate(angle);
				break;
			case 0:
				Vector2D trans = p.getSubtractedCopy(oldPoint);
				shape.translate(trans);
				rotate.add(trans);
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
