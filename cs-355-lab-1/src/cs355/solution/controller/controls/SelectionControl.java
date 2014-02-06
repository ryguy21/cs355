package cs355.solution.controller.controls;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import cs355.solution.controller.interfaces.Control;
import cs355.solution.util.math.Vector2D;

public class SelectionControl extends Vector2D implements Control
{
	private static final Stroke	STROKE			= new BasicStroke(2.5f);
	private static final int	HALF_SIZE		= 4;
	private static final int	SIZE			= HALF_SIZE * 2;
	private static final int	HALF_SIZE_SQ	= HALF_SIZE * HALF_SIZE;

	public SelectionControl(Vector2D p)
	{
		super(p);
	}

	@Override
	public void draw(Graphics2D g)
	{
		Stroke stroke = g.getStroke();
		g.setStroke(STROKE);

		int x = (int) this.x - HALF_SIZE;
		int y = (int) this.y - HALF_SIZE;

		g.drawArc(x, y, SIZE, SIZE, 0, 360);
		g.setStroke(stroke);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		return distanceSquaredTo(p) <= HALF_SIZE_SQ;
	}
}
