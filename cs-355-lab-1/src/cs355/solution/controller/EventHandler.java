package cs355.solution.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs355.solution.controller.IController;
import cs355.solution.util.math.Vector2D;

public class EventHandler extends MouseAdapter
{
	private final IController	controller;

	public EventHandler(IController controller)
	{
		this.controller = controller;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		controller.setDrawingStartPoint(p);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		controller.updateDrawingEndPoint(p);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		controller.updateDrawingEndPoint(p);
		controller.endDrawing();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		controller.registerClick(p);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		controller.registerMove(p);
	}

	// @Override
	// public void mouseEntered(MouseEvent e)
	// {
	// }
	//
	// @Override
	// public void mouseExited(MouseEvent e)
	// {
	// }
}
