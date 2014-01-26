package cs355.solution.view;

import java.awt.Graphics2D;

import cs355.ViewRefresher;
import cs355.solution.model.IModelManager;

public class Refresher implements ViewRefresher
{
	private final IModelManager	model;

	public Refresher(IModelManager model)
	{
		this.model = model;
	}

	@Override
	public void refreshView(Graphics2D g)
	{
		System.out.println("refreshView()");
	}
}
