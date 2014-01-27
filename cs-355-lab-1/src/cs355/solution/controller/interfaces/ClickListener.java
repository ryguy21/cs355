package cs355.solution.controller.interfaces;

import cs355.solution.util.math.Vector2D;

public abstract class ClickListener
{
	private ClickListener	next;

	public final boolean mouseClicked(Vector2D p)
	{
		if (processClick(p))
			return true;
		else if (next != null)
			return next.mouseClicked(p);
		else
			return false;
	}

	protected abstract boolean processClick(Vector2D p);

	public final void addNextClickListener(ClickListener listener)
	{
		if (next == null)
			next = listener;
		else
			next.addNextClickListener(listener);
	}
}
