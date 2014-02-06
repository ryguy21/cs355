package cs355.solution.controller.controls;

import java.awt.Graphics2D;

import cs355.solution.controller.interfaces.Control;
import cs355.solution.util.math.Vector2D;

public class SelectionControl extends Vector2D implements Control
{
	public SelectionControl(Vector2D p)
	{
		super(p);
	}

	@Override
	public void draw(Graphics2D g)
	{

	}

	@Override
	public boolean contains(Vector2D p)
	{
		return false;
	}
}
