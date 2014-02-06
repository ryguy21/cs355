package cs355.solution.controller;

import java.util.Iterator;

import cs355.solution.controller.controls.SelectionControlsFactory;
import cs355.solution.controller.interfaces.ClickListener;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.math.Vector2D;

public class SelectionController extends ClickListener
{
	private final IModelManager	model;
	private final IController	controller;

	private Control				controls;

	public SelectionController(IModelManager model, IController controller)
	{
		this.model = model;
		this.controller = controller;
	}

	@Override
	public boolean processClick(Vector2D p)
	{
		Iterator<Control> controlItr = controller.getControls();

		while (controlItr.hasNext())
		{
			Control control = controlItr.next();
			if (control.contains(p))
			{
				// Log.d("%s contains %s", control, p);
				return true;
			}
		}

		if (controls != null)
			controller.unregisterControl(controls);

		Iterator<Shape> shapeItr = model.getShapesInReverseOrder();

		while (shapeItr.hasNext())
		{
			Shape shape = shapeItr.next();
			if (shape.contains(p))
			{
				// Log.d("%s contains %s", shape, p);
				controls = SelectionControlsFactory.createControls(shape);

				if (controls != null)
					controller.unregisterControl(controls);

				controller.registerControl(controls);

				return true;
			}
		}

		// Log.d("Nothing contains %s", p);

		return false;
	}

}
