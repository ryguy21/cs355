package cs355.solution.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import cs355.CS355Controller;
import cs355.solution.model.IModelManager;

public class Controller implements CS355Controller
{
	private final IModelManager	model;

	public Controller(IModelManager model)
	{
		this.model = model;
	}

	@Override
	public void colorButtonHit(Color c)
	{
		System.out.println("colorButtonHit(" + c + ")");
	}

	@Override
	public void triangleButtonHit()
	{
		System.out.println("triangleButtonHit()");
	}

	@Override
	public void squareButtonHit()
	{
		System.out.println("squareButtonHit()");
	}

	@Override
	public void rectangleButtonHit()
	{
		System.out.println("rectangleButtonHit()");
	}

	@Override
	public void circleButtonHit()
	{
		System.out.println("circleButtonHit()");
	}

	@Override
	public void ellipseButtonHit()
	{
		System.out.println("ellipseButtonHit()");
	}

	@Override
	public void lineButtonHit()
	{
		System.out.println("lineButtonHit()");
	}

	@Override
	public void selectButtonHit()
	{
		System.out.println("selectButtonHit()");
	}

	@Override
	public void zoomInButtonHit()
	{
		System.out.println("zoomInButtonHit()");
	}

	@Override
	public void zoomOutButtonHit()
	{
		System.out.println("zoomOutButtonHit()");
	}

	@Override
	public void hScrollbarChanged(int value)
	{
		System.out.println("hScrollbarChanged(" + value + ")");
	}

	@Override
	public void vScrollbarChanged(int value)
	{
		System.out.println("vScrollbarChanged(" + value + ")");
	}

	@Override
	public void toggle3DModelDisplay()
	{
		System.out.println("toggle3DModelDisplay()");
	}

	@Override
	public void keyPressed(Iterator<Integer> iterator)
	{
		System.out.print("keyPressed():");

		while (iterator.hasNext())
		{
			System.out.print(" " + iterator.next());
		}
		System.out.println();
	}

	@Override
	public void doEdgeDetection()
	{
		System.out.println("doEdgeDetection()");
	}

	@Override
	public void doSharpen()
	{
		System.out.println("doSharpen()");
	}

	@Override
	public void doMedianBlur()
	{
		System.out.println("doMedianBlur()");
	}

	@Override
	public void doUniformBlur()
	{
		System.out.println("doUniformBlur()");
	}

	@Override
	public void doChangeContrast(int delta)
	{
		System.out.println("doChangeContrast(" + delta + ")");
	}

	@Override
	public void doChangeBrightness(int delta)
	{
		System.out.println("doChangeBrightness(" + delta + ")");
	}

	@Override
	public void doLoadImage(BufferedImage openImage)
	{
		System.out.println("doLoadImage()");
	}

	@Override
	public void toggleBackgroundDisplay()
	{
		System.out.println("toggleBackgroundDisplay()");
	}
}
