package cs355.solution.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import cs355.solution.model.shapes.Shape;

public class Model implements IModelManager
{
	private final IShapeStorage	shapeStorage;

	public Model()
	{
		shapeStorage = new ShapeStorage();
	}

	@Override
	public Iterator<Shape> getShapes()
	{
		return shapeStorage.getShapes();
	}

	@Override
	public Iterator<Shape> getShapesInReverseOrder()
	{
		ArrayList<Shape> reverseShapes = new ArrayList<Shape>(shapeStorage.getShapeCount());

		Iterator<Shape> itr = getShapes();
		while (itr.hasNext())
			reverseShapes.add(0, itr.next());

		return Collections.unmodifiableList(reverseShapes).iterator();
	}

	@Override
	public void addShape(Shape shape)
	{
		shapeStorage.addShape(shape);
	}

	@Override
	public void removeShape(Shape shape)
	{
		shapeStorage.removeShape(shape);
	}

	@Override
	public int getShapeCount()
	{
		return shapeStorage.getShapeCount();
	}
}
