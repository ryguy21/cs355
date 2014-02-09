package cs355.solution.controller.interfaces;

import cs355.solution.util.math.Vector2D;

public abstract class InputResponder
{
	protected final IController	controller;

	public InputResponder(IController controller)
	{
		this.controller = controller;
	}

	public void mouseDragged(Vector2D p)
	{}

	public void mouseMoved(Vector2D p)
	{}

	public void mouseClicked(Vector2D p)
	{}

	public void mousePressed(Vector2D p)
	{}

	public void mouseReleased(Vector2D p)
	{}

	public void mouseEntered(Vector2D p)
	{}

	public void mouseExited(Vector2D p)
	{}
}
