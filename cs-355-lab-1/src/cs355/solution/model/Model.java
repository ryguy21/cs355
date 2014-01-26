package cs355.solution.model;

public class Model implements IModelManager
{
	private final IShapeStorage	shapeStorage	= new ShapeStorage();

	@Override
	public IShapeStorage getShapeStorage()
	{
		return shapeStorage;
	}
}
