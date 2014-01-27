package cs355.solution.controller;

import cs355.solution.controller.interfaces.ClickListener;
import cs355.solution.model.IModelManager;
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
		return false;
	}
}
