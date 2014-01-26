package cs355.solution.view;

import java.awt.Graphics2D;
import java.util.Iterator;

import cs355.ViewRefresher;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Shape;

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

		Iterator<Shape> itr = model.getShapeStorage().getShapes();

		while (itr.hasNext())
		{
			Shape shape = itr.next();
			ShapeDrawer.getInstance().drawShape(shape, g);
		}
	}
}
