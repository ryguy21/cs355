package cs355.solution.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cs355.ViewRefresher;
import cs355.solution.controller.interfaces.Control;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Line;
import cs355.solution.model.shapes.Shape;
import cs355.solution.util.Log;
import cs355.solution.util.math.Vector2D;

public class Refresher implements ViewRefresher
{
	private final IModelManager	model;
	private IController			controller;

	private final List<Line>	lines	= new ArrayList<Line>();

	public Refresher(IModelManager model)
	{
		this.model = model;
		// for debugging
		INSTANCE = this;
	}

	@Override
	public void refreshView(Graphics2D g)
	{
		Log.v("refreshView()");
		Log.v("Drawing %d shapes\n", model.getShapeCount());

		Iterator<Shape> itr = model.getShapes();

		while (itr.hasNext())
		{
			Shape shape = itr.next();
			ShapeDrawer.getInstance().drawShape(shape, g);
		}

		for (Line l : lines)
			ShapeDrawer.getInstance().drawLine(l, g);

		if (controller != null)
		{
			Control control = controller.getControl();
			if (control != null)
				control.draw(g);
		}
	}

	public void setController(IController controller)
	{
		this.controller = controller;
	}

	// for debugging
	private static Refresher	INSTANCE;

	public static Refresher getInstance()
	{
		return INSTANCE;
	}

	public void addLine(Color c, Vector2D start, Vector2D end)
	{
		lines.add(new Line(c, start, end));
	}

	public void clearLines()
	{
		lines.clear();
	}
	// end debugging
}
