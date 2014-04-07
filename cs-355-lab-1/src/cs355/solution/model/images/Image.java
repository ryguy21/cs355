package cs355.solution.model.images;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import cs355.solution.util.ImageUtils;
import cs355.solution.util.Log;

public class Image
{
	public static final int			WIDTH			= 2048;
	public static final int			HEIGHT			= 2048;

	public static final Object		MEDIAN_BLUR		= new Object();
	public static final Object		UNIFORM_BLUR	= new Object();

	private static final Mask		AVERAGE_MASK	= new Mask(3);
	private static final MedianMask	MEDIAN_MASK		= new MedianMask(3);
	private static final Mask		UNSHARP_MASK	= new Mask(3);
	private static final SobelMask	SOBEL_MASK		= new SobelMask();

	static
	{
		AVERAGE_MASK.setValues(1);
		AVERAGE_MASK.setScalar(9.0);

		UNSHARP_MASK.setValue(1, 0, -1.0);
		UNSHARP_MASK.setValue(0, 1, -1.0);
		UNSHARP_MASK.setValue(1, 2, -1.0);
		UNSHARP_MASK.setValue(2, 1, -1.0);
		UNSHARP_MASK.setValue(1, 1, 6.0);
		UNSHARP_MASK.setScalar(2.0);
	}

	private int						height, width, left, top;
	private int[][]					data;
	private boolean					dirty;
	private BufferedImage			buffer;

	public Image()
	{
		data = new int[WIDTH][HEIGHT];
		dirty = true;
	}

	public void setBuffer(BufferedImage buffer)
	{
		Raster raster = buffer.getRaster();
		width = raster.getWidth();
		height = raster.getHeight();

		Log.v("Importing image size %dx%d", width, height);

		data = new int[WIDTH][HEIGHT];

		left = (WIDTH - width) / 2;
		top = (HEIGHT - height) / 2;

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

		dirty = true;
	}

	public BufferedImage getBuffer()
	{
		if (dirty)
		{
			buffer = ImageUtils.createFromData(data);
			dirty = false;
		}

		return buffer;
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

	public void sharpen()
	{
		data = UNSHARP_MASK.filter(data, left, top, width, height);
		dirty = true;
	}

	public void blur(Object type)
	{
		if (MEDIAN_BLUR.equals(type))
		{
			data = MEDIAN_MASK.filter(data, left, top, width, height);
			dirty = true;
		}
		else if (UNIFORM_BLUR.equals(type))
		{
			data = AVERAGE_MASK.filter(data, left, top, width, height);
			dirty = true;
		}
	}

	public void changeContrast(int delta)
	{
		int right = left + width;
		int bottom = top + height;

		double multiplier = Math.pow((delta + 100.0) / 100.0, 4);

		for (int x = left; x < right; x++)
		{
			for (int y = top; y < bottom; y++)
			{
				double val = multiplier * (data[x][y] - 128.0) + 128.0;

				data[x][y] = ImageUtils.normalize(val);
			}
		}

		dirty = true;
	}

	public void brighten(int delta)
	{
		int right = left + width;
		int bottom = top + height;

		for (int x = left; x < right; x++)
		{
			for (int y = top; y < bottom; y++)
			{
				int val = data[x][y] + delta;

				data[x][y] = ImageUtils.normalize(val);
			}
		}

		dirty = true;
	}

	public void detectEdges()
	{
		data = SOBEL_MASK.filter(data, left, top, width, height);
		dirty = true;
	}
}
