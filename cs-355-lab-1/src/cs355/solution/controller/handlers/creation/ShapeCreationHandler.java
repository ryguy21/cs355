package cs355.solution.controller.handlers.creation;

import java.awt.Color;

import cs355.solution.controller.interfaces.IController;
import cs355.solution.controller.interfaces.InputResponder;

public abstract class ShapeCreationHandler extends InputResponder
{
	protected Color	color;

	public ShapeCreationHandler(IController controller, Color color)
	{
		super(controller);
		this.color = color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public Color getColor()
	{
		return color;
	}

	public abstract void cancel();
}
