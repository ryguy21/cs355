package cs355.solution.model;

import cs355.solution.model.shapes.Circle;
import cs355.solution.model.shapes.ShapeType;

public class Model implements IModelManager
{
	private final IShapeStorage	shapeStorage;

	public Model()
	{
		shapeStorage = new ShapeStorage();
	}

	@Override
	public IShapeStorage getShapeStorage()
	{
		return shapeStorage;
	}
}
