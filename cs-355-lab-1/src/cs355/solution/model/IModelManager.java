package cs355.solution.model;

import java.util.Iterator;

import cs355.solution.model.shapes.Shape;

public interface IModelManager
{
	public Iterator<Shape> getShapes();

	public Iterator<Shape> getShapesInReverseOrder();

	public void addShape(Shape shape);

	public void removeShape(Shape shape);

	public int getShapeCount();
}
