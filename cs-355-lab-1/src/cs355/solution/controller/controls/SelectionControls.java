package cs355.solution.controller.controls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import cs355.solution.controller.interfaces.Control;
import cs355.solution.model.shapes.Shape;

public abstract class SelectionControls<T extends Shape> implements Control
{
	private static final Stroke	STROKE			= new BasicStroke(1.5f);
	private static final Color	STROKE_COLOR	= Color.cyan;

	protected final T			shape;

	public SelectionControls(T shape)
	{
		this.shape = shape;
	}

	public void setShapeColor(Color c)
	{
		shape.setColor(c);
	}

	@Override
	public void draw(Graphics2D g)
	{
		Stroke stroke = g.getStroke();
		Color color = g.getColor();

		g.setStroke(STROKE);
		g.setColor(STROKE_COLOR);

		drawComponents(g);

		g.setStroke(stroke);
		g.setColor(color);
	}

	protected abstract void drawComponents(Graphics2D g);
}
