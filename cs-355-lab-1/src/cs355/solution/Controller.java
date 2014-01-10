package cs355.solution;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import cs355.CS355Controller;

public class Controller implements CS355Controller
{

	@Override
	public void colorButtonHit(Color c)
	{}

	@Override
	public void triangleButtonHit()
	{}

	@Override
	public void squareButtonHit()
	{}

	@Override
	public void rectangleButtonHit()
	{}

	@Override
	public void circleButtonHit()
	{}

	@Override
	public void ellipseButtonHit()
	{}

	@Override
	public void lineButtonHit()
	{}

	@Override
	public void selectButtonHit()
	{}

	@Override
	public void zoomInButtonHit()
	{}

	@Override
	public void zoomOutButtonHit()
	{}

	@Override
	public void hScrollbarChanged(int value)
	{}

	@Override
	public void vScrollbarChanged(int value)
	{}

	@Override
	public void toggle3DModelDisplay()
	{}

	@Override
	public void keyPressed(Iterator<Integer> iterator)
	{}

	@Override
	public void doEdgeDetection()
	{}

	@Override
	public void doSharpen()
	{}

	@Override
	public void doMedianBlur()
	{}

	@Override
	public void doUniformBlur()
	{}

	@Override
	public void doChangeContrast(int contrastAmountNum)
	{}

	@Override
	public void doChangeBrightness(int brightnessAmountNum)
	{}

	@Override
	public void doLoadImage(BufferedImage openImage)
	{}

	@Override
	public void toggleBackgroundDisplay()
	{}

}
