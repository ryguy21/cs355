package cs355.solution.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs355.solution.controller.IController;
import cs355.solution.model.IModelManager;
import cs355.solution.util.math.Vector2D;

public class EventHandler extends MouseAdapter
{
	private final IModelManager	model;
	private final IController	controller;

	public EventHandler(IModelManager model, IController controller)
	{
		this.model = model;
		this.controller = controller;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		System.out.println("Mouse pressed event called at " + e.getPoint());

		Vector2D p = new Vector2D(e.getPoint());
		controller.setDrawingStartPoint(p);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		System.out.println("Mouse dragged event called at " + e.getPoint());

		Vector2D p = new Vector2D(e.getPoint());
		controller.updateDrawingEndPoint(p);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		System.out.println("Mouse released event called at " + e.getPoint());

		Vector2D p = new Vector2D(e.getPoint());
		controller.updateDrawingEndPoint(p);
		controller.endDrawing();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		System.out.println("Mouse clicked event called at " + e.getPoint());

		Vector2D p = new Vector2D(e.getPoint());
		controller.registerClick(p);
	}

	// @Override
	// public void mouseMoved(MouseEvent e)
	// {
	// System.out.println("Mouse moved event called at " + e.getPoint());
	// }
	//
	// @Override
	// public void mouseEntered(MouseEvent e)
	// {
	// System.out.println("Mouse entered event called at " + e.getPoint());
	// }
	//
	// @Override
	// public void mouseExited(MouseEvent e)
	// {
	// System.out.println("Mouse exited event called at " + e.getPoint());
	// }
}
