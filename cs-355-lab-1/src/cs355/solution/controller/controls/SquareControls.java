package cs355.solution.controller.controls;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.shapes.Square;

public class SquareControls extends RectangleControls
{
	public SquareControls(IController controller, Square s)
	{
		super(controller, s);
	}
}
