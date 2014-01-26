package cs355.solution.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import cs355.solution.model.shapes.Shape;

public class ShapeStorage implements IShapeStorage
{
	private final ArrayList<Shape>	shapes;

	public ShapeStorage()
	{
		shapes = new ArrayList<Shape>();
	}

	@Override
	public void addShape(Shape shape)
	{
		shapes.add(shape);
	}

	@Override
	public void removeShape(Shape shape)
	{
		shapes.remove(shape);
	}

	@Override
	public Iterator<Shape> getShapes()
	{
		return Collections.unmodifiableList(shapes).iterator();
	}

	@Override
	public int getShapeCount()
	{
		return shapes.size();
	}
}
