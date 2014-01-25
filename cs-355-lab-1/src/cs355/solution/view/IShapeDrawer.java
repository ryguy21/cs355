package cs355.solution.view;

import java.awt.Graphics2D;

import cs355.solution.model.*;

public interface IShapeDrawer
{
	public void drawShape(Shape s, Graphics2D g);

	public void drawCircle(Circle c, Graphics2D g);

	public void drawEllipse(Ellipse e, Graphics2D g);

	public void drawLine(Line l, Graphics2D g);

	public void drawRectangle(Rectangle r, Graphics2D g);

	public void drawSquare(Square s, Graphics2D g);

	public void drawTriangle(Triangle t, Graphics2D g);
}
