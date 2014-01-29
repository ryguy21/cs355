package cs355.solution.controller;

import java.util.Iterator;

import cs355.solution.controller.interfaces.ClickListener;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.Log;
import cs355.solution.util.Tools;
import cs355.solution.util.math.Vector2D;

public class SelectionController extends ClickListener
{
	private final IModelManager	model;
	private ClickListener		next;

	public SelectionController(IModelManager model)
	{
		this.model = model;
	}

	@Override
	public boolean processClick(Vector2D p)
	{
		Iterator<Shape> itr = model.getShapesInReverseOrder();

		Tools.printStackTraceAtLogLevel(Log.DEBUG, "Component");

		while (itr.hasNext())
		{
			Shape shape = itr.next();
			if (shape.contains(p))
			{
				// do stuff
				Log.d("%s contains %s", shape, p);

				return true;
			}
		}

		Log.d("Nothing contains %s", p);

		return false;
	}
}
