package cs355.solution.model;

import java.util.Iterator;

import cs355.solution.model.shapes.Shape;

public interface IShapeStorage
{
	public void addShape(Shape shape);

	public void removeShape(Shape shape);

	public Iterator<Shape> getShapes();

	public int getShapeCount();
}
