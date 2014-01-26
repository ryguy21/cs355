package cs355.solution.controller;

import cs355.CS355Controller;
import cs355.solution.util.math.Vector2D;

public interface IController extends CS355Controller
{
	public void setDrawingStartPoint(Vector2D p);

	public void updateDrawingEndPoint(Vector2D p);

	public void endDrawing();

	public void registerClick(Vector2D p);
}
