package cs355.solution.model;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import cs355.solution.util.Log;

public class Image
{
	private static final int	WIDTH	= 2048;
	private static final int	HEIGHT	= 2048;

	int							height;
	int							width;
	int[][]						data;

	public Image()
	{}

	public void setBuffer(BufferedImage buffer)
	{
		Raster raster = buffer.getRaster();
		width = raster.getWidth();
		height = raster.getHeight();

		Log.v("Importing image size %dx%d", width, height);

		data = new int[WIDTH][HEIGHT];

		int left = (WIDTH - width) / 2;
		int top = (HEIGHT - height) / 2;

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				int r = raster.getSample(x, y, 0);
				int g = raster.getSample(x, y, 1);
				int b = raster.getSample(x, y, 2);
				data[x + left][y + top] = (int) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
			}
		}
	}

	public int[][] getData()
	{
		return data;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
}
