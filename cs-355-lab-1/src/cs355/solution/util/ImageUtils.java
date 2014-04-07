package cs355.solution.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class ImageUtils
{
	public static boolean	NORMALIZE_DATA	= true;

	public static BufferedImage getImage(String filename) throws IOException
	{
		System.out.println("Reading file " + filename);
		BufferedImage image = ImageIO.read(new File(filename));

		return image;
	}

	public static BufferedImage getCopy(BufferedImage image)
	{
		// create a fresh copy of the raster
		WritableRaster raster = image.copyData(null);

		ColorModel cm = image.getColorModel();
		return new BufferedImage(cm, raster, cm.isAlphaPremultiplied(), null);
	}

	public static int[][] getCopy(int[][] data)
	{
		int[][] copy = new int[data.length][data[0].length];

		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[i].length; j++)
				copy[i][j] = data[i][j];

		return copy;
	}

	public static void saveImage(BufferedImage image, String file) throws IOException
	{
		// override extension to be png so other programs can read it
		if (file.lastIndexOf('.') != -1) file = file.substring(0, file.lastIndexOf('.'));

		String extension = "png";
		file += "." + extension;

		// most of this code was taken from example code from Canvas
		System.out.println("Saving image to file " + file);
		ImageWriter writer = ImageIO.getImageWritersBySuffix(extension).next();
		ImageOutputStream out = ImageIO.createImageOutputStream(new File(file));
		writer.setOutput(out);
		writer.write(image);
		out.close();
	}

	public static void saveImage(int[][] data, String file) throws IOException
	{
		saveImage(createFromData(data), file);
	}

	public static BufferedImage createFilterImage(int image_size, int filter_size)
	{
		int[][] data = new int[image_size][image_size];

		int start = (image_size - filter_size) / 2;
		int end = start + filter_size;

		for (int i = start; i < end; i++)
			for (int j = start; j < end; j++)
				data[i][j] = 255;

		return createFromData(data);
	}

	public static BufferedImage createFromData(int[][] pixels)
	{
		int width = pixels.length;
		int height = pixels[0].length;

		double scalar = 1.0;

		if (NORMALIZE_DATA)
		{
			int max = 0;

			for (int x = 0; x < pixels.length; x++)
				for (int y = 0; y < pixels[x].length; y++)
					if (pixels[x][y] > max) max = pixels[x][y];

			if (max > 255.0) scalar = 255.0 / max;
		}

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = image.getRaster();

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				double val = pixels[x][y] * scalar;

				raster.setPixel(x, y, new double[]
				{
						val,
						val,
						val
				});
			}
		}

		image.setData(raster);
		return image;
	}

	public static double[][] getData(BufferedImage image)
	{
		return getData(image, 0);
	}

	public static double[][] getData(BufferedImage image, int band)
	{
		WritableRaster raster = image.getRaster();
		int width = raster.getWidth();
		int height = raster.getHeight();

		double[][] data = new double[raster.getWidth()][raster.getHeight()];

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
				data[x][y] = raster.getSampleDouble(x, y, band);
		}

		return data;
	}

	public static double[][][] getColorData(BufferedImage image)
	{
		WritableRaster raster = image.getRaster();
		int width = raster.getWidth();
		int height = raster.getHeight();

		double[][][] data = new double[raster.getWidth()][raster.getHeight()][3];

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				for (int band = 0; band < raster.getNumBands(); band++)
					data[x][y][band] = raster.getSampleDouble(x, y, band);
			}
		}

		return data;
	}

	public static int normalize(double val)
	{
		if (val > 255.0)
			return 255;
		else if (val < 0.0)
			return 0;
		else
			return (int) val;
	}
}
