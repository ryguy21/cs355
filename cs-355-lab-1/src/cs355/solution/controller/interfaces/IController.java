package cs355.solution.controller.interfaces;

import cs355.CS355Controller;
import cs355.solution.util.math.Vector2D;

public interface IController extends CS355Controller
{
	public void setDrawingStartPoint(Vector2D p);

	public void updateDrawingEndPoint(Vector2D p);

	public void endDrawing();

	public void processClick(Vector2D p);

	public void registerMove(Vector2D p);

	public void setCurrentControl(Control control);

	public void unsetControl();

	public Control getControl();
}
