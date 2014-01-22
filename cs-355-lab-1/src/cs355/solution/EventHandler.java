package cs355.solution;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EventHandler implements MouseListener, MouseMotionListener
{
	@Override
	public void mouseDragged(MouseEvent e)
	{
		System.out.println("Mouse dragged event called at " + e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		System.out.println("Mouse moved event called at " + e.getPoint());
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		System.out.println("Mouse clicked event called at " + e.getPoint());
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		System.out.println("Mouse pressed event called at " + e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		System.out.println("Mouse released event called at " + e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		System.out.println("Mouse entered event called at " + e.getPoint());
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		System.out.println("Mouse exited event called at " + e.getPoint());
	}
}
