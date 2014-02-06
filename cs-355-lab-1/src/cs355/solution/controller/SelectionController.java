package cs355.solution.controller;

import java.util.Iterator;

import cs355.solution.controller.controls.SelectionControlsFactory;
import cs355.solution.controller.interfaces.ClickListener;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.math.Vector2D;

public class SelectionController extends ClickListener
{
	private final IModelManager	model;
	private final IController	controller;

	public SelectionController(IModelManager model, IController controller)
	{
		this.model = model;
		this.controller = controller;
	}

	@Override
	public boolean processClick(Vector2D p)
	{
		Control control = controller.getControl();

		if (control != null && control.contains(p))
		{
			// Log.d("%s contains %s", control, p);
			return true;
		}

		controller.unsetControl();

		Iterator<Shape> shapeItr = model.getShapesInReverseOrder();

		while (shapeItr.hasNext())
		{
			Shape shape = shapeItr.next();
			if (shape.contains(p))
			{
				// Log.d("%s contains %s", shape, p);
				control = SelectionControlsFactory.createControl(shape);
				controller.setCurrentControl(control);

				return true;
			}
		}

		// Log.d("Nothing contains %s", p);

		return false;
	}

}
