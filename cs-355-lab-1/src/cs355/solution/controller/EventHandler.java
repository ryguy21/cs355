package cs355.solution.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs355.solution.controller.interfaces.InputResponder;
import cs355.solution.util.math.Vector2D;

public class EventHandler extends MouseAdapter
{
	private InputResponder			responder;
	private ViewTransformController	controller;

	public EventHandler()
	{}

	public void setViewTransformController(ViewTransformController controller)
	{
		this.controller = controller;
	}

	public void registerInputResponder(InputResponder responder)
	{
		this.responder = responder;
	}

	public void unregisterInputResponder()
	{
		responder = null;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		p = controller.viewToWorld(p);
		if (responder != null)
			responder.mousePressed(p);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		p = controller.viewToWorld(p);
		if (responder != null)
			responder.mouseDragged(p);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		p = controller.viewToWorld(p);
		if (responder != null)
			responder.mouseReleased(p);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		p = controller.viewToWorld(p);
		if (responder != null)
			responder.mouseClicked(p);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		p = controller.viewToWorld(p);
		if (responder != null)
			responder.mouseMoved(p);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		p = controller.viewToWorld(p);
		if (responder != null)
			responder.mouseEntered(p);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		Vector2D p = new Vector2D(e.getPoint());
		p = controller.viewToWorld(p);
		if (responder != null)
			responder.mouseExited(p);
	}
}
