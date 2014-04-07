package cs355.solution.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import cs355.solution.model.images.Image;
import cs355.solution.model.shapes.Shape;

public class Model implements IModelManager
{
	private final IShapeStorage	shapeStorage;
	private final Image			image;

	public Model()
	{
		shapeStorage = new ShapeStorage();
		image = new Image();
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

	@Override
	public Image getImage()
	{
		return image;
	}

	@Override
	public void setImage(BufferedImage image)
	{
		this.image.setBuffer(image);
	}
}
