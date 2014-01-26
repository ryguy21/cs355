package cs355.solution.model;

public class Model implements IModel
{
	private abstract static class SingletonHolder
	{
		public static final Model	INSTANCE	= new Model();
	}

	public static IModel getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private final IShapeStorage	shapeStorage	= new ShapeStorage();

	private Model()
	{}

	@Override
	public IShapeStorage getShapeStorage()
	{
		return shapeStorage;
	}
}
