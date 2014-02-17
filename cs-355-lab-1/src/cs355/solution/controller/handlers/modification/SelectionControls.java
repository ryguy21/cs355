package cs355.solution.controller.handlers.modification;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import cs355.solution.controller.interfaces.Control;
import cs355.solution.controller.interfaces.IController;
import cs355.solution.controller.interfaces.InputResponder;
import cs355.solution.model.shapes.Shape;

public abstract class SelectionControls<T extends Shape> extends InputResponder implements Control
{
	private static final Stroke	STROKE			= new BasicStroke(1.5f);
	private static final Color	STROKE_COLOR	= Color.cyan;

	protected final T			shape;

	public SelectionControls(IController controller, T shape)
	{
		super(controller);
		this.shape = shape;
	}

	public void setShapeColor(Color c)
	{
		shape.setColor(c);
		controller.refresh();
	}

	@Override
	public void draw(Graphics2D g)
	{
		Stroke stroke = g.getStroke();
		Color color = g.getColor();
		AffineTransform transform = g.getTransform();

		g.setStroke(STROKE);
		g.setColor(STROKE_COLOR);
		g.setTransform(shape.getTransform().toAffineTransform());

		drawComponents(g);

		g.setStroke(stroke);
		g.setColor(color);
		g.setTransform(transform);
	}

	protected abstract void drawComponents(Graphics2D g);
}
