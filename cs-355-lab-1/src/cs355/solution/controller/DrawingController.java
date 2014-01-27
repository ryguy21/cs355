package cs355.solution.controller;

import java.awt.Color;

import cs355.solution.controller.interfaces.ClickListener;
import cs355.solution.model.IModelManager;
import cs355.solution.model.shapes.Shape;
import cs355.solution.model.shapes.ShapeType;
import cs355.solution.util.math.Vector2D;

public class DrawingController extends ClickListener
{
	private final IModelManager	model;

	private final DrawingState	drawingState;

	private Shape				currentShape;

	private ClickListener		next;

	public DrawingController(IModelManager model)
	{
		this.model = model;

		drawingState = new DrawingState();
	}

	public void colorButtonHit(Color c)
	{
		drawingState.setColor(c);
	}

	public void triangleButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.TRIANGLE);
	}

	public void squareButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.SQUARE);
	}

	public void rectangleButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.RECTANGLE);
	}

	public void circleButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.CIRCLE);
	}

	public void ellipseButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.ELLIPSE);
	}

	public void lineButtonHit()
	{
		drawingState.setCurrentShape(ShapeType.LINE);
	}

	public void setDrawingStartPoint(Vector2D p)
	{
		if (drawingState.isSetUp() && drawingState.getCurrentShape() != ShapeType.TRIANGLE)
		{
			drawingState.setStartPoint(p);

			currentShape = ShapeCreator.getInstance().createShape(drawingState);

			model.getShapeStorage().addShape(currentShape);
		}
	}

	public void updateDrawingEndPoint(Vector2D p)
	{
		if (drawingState.isSetUp() && drawingState.getCurrentShape() != ShapeType.TRIANGLE)
		{
			drawingState.setEndPoint(p);

			ShapeCreator.getInstance().updateShape(drawingState, currentShape);
		}
	}

	public boolean addTrianglePoint(Vector2D p)
	{
		if (drawingState.isSetUp() && drawingState.getCurrentShape() == ShapeType.TRIANGLE)
		{
			if (!drawingState.hasStartPoint())
			{
				drawingState.setStartPoint(p);
				currentShape = ShapeCreator.getInstance().createShape(drawingState);
				model.getShapeStorage().addShape(currentShape);
			}
			else if (!drawingState.hasIntermediatePoint())
			{
				drawingState.setIntermediatePoint(p);
				ShapeCreator.getInstance().updateShape(drawingState, currentShape);
			}
			else if (!drawingState.hasEndPoint())
			{
				drawingState.setEndPoint(p);
				ShapeCreator.getInstance().updateShape(drawingState, currentShape);
				endDrawingInternal();
			}
			else
				return false;

			return true;
		}
		else
			return false;
	}

	public void updateTrianglePoint(Vector2D p)
	{
		if (drawingState.isSetUp() && drawingState.getCurrentShape() == ShapeType.TRIANGLE && drawingState.hasStartPoint())
		{
			if (!drawingState.hasIntermediatePoint())
			{
				drawingState.setIntermediatePoint(p, false);
				ShapeCreator.getInstance().updateShape(drawingState, currentShape);
			}
			else if (!drawingState.hasEndPoint())
			{
				drawingState.setEndPoint(p, false);
				ShapeCreator.getInstance().updateShape(drawingState, currentShape);
			}
		}
	}

	public void endDrawing()
	{
		if (drawingState.isSetUp() && drawingState.getCurrentShape() != ShapeType.TRIANGLE)
			endDrawingInternal();
	}

	private void endDrawingInternal()
	{
		drawingState.reset();

		currentShape = null;
	}

	@Override
	public boolean processClick(Vector2D p)
	{
		return addTrianglePoint(p);
	}
}
