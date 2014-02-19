package cs355.solution.controller.handlers.modification;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import cs355.solution.controller.interfaces.Selectable;
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public class HandleControl extends Vector2D implements Selectable
{
	public static final BasicStroke	STROKE			= new BasicStroke(2f);
	public static final int			HALF_SIZE		= 5;
	public static final int			SIZE			= HALF_SIZE * 2;
	private static final int		HALF_SIZE_SQ	= HALF_SIZE * HALF_SIZE;

	public HandleControl()
	{
		super();
	}

	public HandleControl(Vector2D p)
	{
		super(p);
	}

	public HandleControl(float x, float y)
	{
		super(x, y);
	}

	public void draw(Graphics2D g, Matrix otov)
	{
		Stroke oldStroke = g.getStroke();
		g.setStroke(STROKE);

		Vector2D v = getMultipliedCopy(otov);

		int x = (int) v.x - HALF_SIZE;
		int y = (int) v.y - HALF_SIZE;

		g.drawArc(x, y, SIZE, SIZE, 0, 360);
		g.setStroke(oldStroke);
	}

	@Override
	public boolean contains(Vector2D p)
	{
		return distanceSquaredTo(p) <= HALF_SIZE_SQ;
	}
}
