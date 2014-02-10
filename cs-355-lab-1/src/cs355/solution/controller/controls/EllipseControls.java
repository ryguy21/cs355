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

		rotate = new HandleControl(new Vector2D(0, -e.getyRadius() - 30));

		activeHandle = -1;
	}

	@Override
	public boolean contains(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		if (shape.contains(w))
			return true;
		else if (rotate.contains(o))
			return true;
		else
			return false;
	}

	@Override
	protected void drawComponents(Graphics2D g)
	{
		rotate.draw(g);

		int x = (int) (-shape.getxRadius());
		int y = (int) (-shape.getyRadius());
		int width = (int) shape.getxDiameter();
		int height = (int) shape.getyDiameter();

		g.drawArc(x, y, width, height, 0, 360);
	}

	@Override
	public void mousePressed(Vector2D w)
	{
		Vector2D o = shape.worldToObject(w);

		if (rotate.contains(o))
		{
			activeHandle = 1;
		}
		else if (shape.contains(w))
		{
			activeHandle = 0;
			oldPoint = w;
		}
	}

	@Override
	public void mouseDragged(Vector2D w)
	{
		switch (activeHandle)
		{
			case 1:
				Vector2D o = w.getSubtractedCopy(shape.getCenter());
				float angle = Vector2D.angleBetween(Vector2D.Y_AXIS.getInvertedCopy(), o);
				shape.setRotation(angle);
				break;
			case 0:
				Vector2D trans = w.getSubtractedCopy(oldPoint);
				shape.translate(trans);
				oldPoint = w;
				break;
			default:
				return;
		}

		controller.refresh();
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		activeHandle = -1;
	}
}
