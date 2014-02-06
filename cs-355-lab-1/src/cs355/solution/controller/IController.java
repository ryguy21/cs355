package cs355.solution.controller;

import java.util.Iterator;

import cs355.CS355Controller;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.util.math.Vector2D;

public interface IController extends CS355Controller
{
	public void setDrawingStartPoint(Vector2D p);

	public void updateDrawingEndPoint(Vector2D p);

	public void endDrawing();

	public void processClick(Vector2D p);

	public void registerMove(Vector2D p);

	public void registerControl(Control control);

	public void unregisterControl(Control control);

	public Iterator<Control> getControls();
}
