package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.Control;
import cs355.solution.model.shapes.Circle;
import cs355.solution.util.math.Vector2D;

public class CircleControls implements Control
{
	public CircleControls(Circle s)
	{}

	@Override
	public void draw(Graphics2D g)
	{}

	@Override
	public boolean contains(Vector2D p)
	{
		return false;
	}
}
