package cs355.solution.controller.interfaces;

import cs355.CS355Controller;
import cs355.solution.controller.Controller3D;
import cs355.solution.controller.ViewTransformController;
import cs355.solution.model.shapes.Shape;

public interface IController extends CS355Controller
{
	public void initialize();

	public void addShape(Shape shape);

	public void removeShape(Shape shape);

	public void refresh();

	public void inputSequenceComplete();

	public Control getControl();

	public ViewTransformController getViewTransformController();

	public Controller3D get3DController();

	public boolean isBackgroundEnabled();
}
