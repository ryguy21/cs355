package cs355.solution.controller;

import java.awt.Color;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.solution.controller.controls.SelectionControls;
import cs355.solution.controller.controls.SelectionControlsFactory;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.controller.interfaces.InputResponder;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.math.Vector2D;

public class SelectionController extends InputResponder
{
	private final IModelManager		model;
	private SelectionControls<?>	control;

	public SelectionController(IModelManager model, IController controller)
	{
		super(controller);
		this.model = model;
	}

	@Override
	public void mouseClicked(Vector2D p)
	{
		if (control != null)
		{
			if (control.contains(p))
			{
				control.mouseClicked(p);
				return;
			}
		}

		Iterator<Shape> shapeItr = model.getShapesInReverseOrder();

		while (shapeItr.hasNext())
		{
			Shape shape = shapeItr.next();
			if (shape.contains(p))
			{
				control = SelectionControlsFactory.createControl(controller, shape);
				GUIFunctions.changeSelectedColor(shape.getColor());
				controller.refresh();
				return;
			}
		}

		control = null;
		controller.refresh();
	}

	public Control getControl()
	{
		return control;
	}

	public void setColor(Color c)
	{
		if (control != null)
		{
			control.setShapeColor(c);
		}
	}

	@Override
	public void mousePressed(Vector2D p)
	{
		if (control != null)
		{
			if (control.contains(p))
			{
				control.mousePressed(p);
				return;
			}
		}

		control = null;
		controller.refresh();
	}

	@Override
	public void mouseDragged(Vector2D p)
	{
		if (control != null)
			control.mouseDragged(p);
	}

	@Override
	public void mouseReleased(Vector2D p)
	{
		if (control != null)
			control.mouseReleased(p);
	}
}
