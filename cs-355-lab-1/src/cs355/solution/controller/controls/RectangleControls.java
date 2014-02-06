package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.model.shapes.Rectangle;
import cs355.solution.util.math.Vector2D;

public class RectangleControls extends SelectionControls<Rectangle>
{
	private final HandleControl	topLeft, topRight, bottomLeft, bottomRight, rotate;

	public RectangleControls(Rectangle s)
	{
		super(s);

		Vector2D tlCorner = shape.getTopLeftCorner();

		float right = tlCorner.x + shape.getWidth();
		float bottom = tlCorner.y + shape.getHeight();

		topLeft = new HandleControl(tlCorner);
		topRight = new HandleControl(right, tlCorner.y);
		bottomLeft = new HandleControl(tlCorner.x, bottom);
		bottomRight = new HandleControl(right, bottom);

		rotate = new HandleControl(shape.getCenter().x, tlCorner.y - 30f);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		if (shape.contains(p))
			return true;
		else if (topLeft.contains(p))
			return true;
		else if (topRight.contains(p))
			return true;
		else if (bottomLeft.contains(p))
			return true;
		else if (bottomRight.contains(p))
			return true;
		else
			return false;
	}

	@Override
	protected void drawComponents(Graphics2D g)
	{
		topLeft.draw(g);
		topRight.draw(g);
		bottomLeft.draw(g);
		bottomRight.draw(g);
		rotate.draw(g);

		Vector2D tlCorner = shape.getTopLeftCorner();
		int x = (int) tlCorner.x;
		int y = (int) tlCorner.y;
		int width = (int) shape.getWidth();
		int height = (int) shape.getHeight();

		g.drawRect(x, y, width, height);
	}
}
