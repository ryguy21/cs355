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
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector2D;

public abstract class SelectionControls<T extends Shape> extends InputResponder implements Control
{
	protected static final float	STROKE_WIDTH	= 1.5f;

	private static final Stroke		STROKE			= new BasicStroke(STROKE_WIDTH);
	private static final Color		STROKE_COLOR	= Color.cyan;

	protected final T				shape;

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
		Matrix otov = getTransform(shape);

		g.setStroke(STROKE);
		g.setColor(STROKE_COLOR);

		drawComponents(g, otov);

		g.setStroke(stroke);
		g.setColor(color);
		g.setTransform(transform);
	}

	private Matrix getTransform(Shape s)
	{
		Matrix otow = s.getTransform();
		Matrix wtov = controller.getViewTransformController().getTransform();

		return wtov.getMultipliedCopy(otow);
	}

	protected Vector2D getRotationHandlePosition(float y)
	{
		Vector2D offset = new Vector2D(0f, -30f);
		offset.scale(controller.getViewTransformController().getInverseTransform().getScaleX());

		return offset.add(0, y);
	}

	protected abstract void drawComponents(Graphics2D g, Matrix otov);
}
