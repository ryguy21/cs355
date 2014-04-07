package cs355.solution.model.images;

import cs355.solution.util.ImageUtils;

public class SobelMask extends Mask
{
	public static class SobelYMask extends Mask
	{
		public SobelYMask()
		{
			super(3, 3);
			mask = new double[]
			{
					1,
					0,
					-1,
					2,
					0,
					-2,
					1,
					0,
					-1
			};

			scalar = 8;
		}

		@Override
		protected int maskPoint(int[][] in, int i, int j)
		{
			int left = Math.max(0, i - ((m - 1) / 2));
			int top = Math.max(0, j - ((n - 1) / 2));
			int right = Math.min(in.length - 1, i + (m / 2));
			int bottom = Math.min(in[0].length - 1, j + (n / 2));

			double total = 0;
			for (int x = left; x <= right; x++)
			{
				for (int y = top; y <= bottom; y++)
				{
					total += in[x][y] * getValue(x - left, y - top);
				}
			}

			return ImageUtils.normalize(Math.abs(total / scalar));
		}
	}

	public static class SobelXMask extends Mask
	{
		public SobelXMask()
		{
			super(3, 3);
			mask = new double[]
			{
					1,
					2,
					1,
					0,
					0,
					0,
					-1,
					-2,
					-1
			};

			scalar = 8;
		}

		@Override
		protected int maskPoint(int[][] in, int i, int j)
		{
			int left = Math.max(0, i - ((m - 1) / 2));
			int top = Math.max(0, j - ((n - 1) / 2));
			int right = Math.min(in.length - 1, i + (m / 2));
			int bottom = Math.min(in[0].length - 1, j + (n / 2));

			double total = 0;
			for (int x = left; x <= right; x++)
			{
				for (int y = top; y <= bottom; y++)
				{
					total += in[x][y] * getValue(x - left, y - top);
				}
			}

			return ImageUtils.normalize(Math.abs(total / scalar));
		}
	}

	public SobelMask()
	{
		super(3, 3);
	}

	@Override
	public int[][] filter(int[][] data, int left, int top, int width, int height)
	{
		SobelXMask sobelX = new SobelXMask();
		SobelYMask sobelY = new SobelYMask();

		int[][] in1 = ImageUtils.getCopy(data);
		int[][] in2 = ImageUtils.getCopy(data);

		in1 = sobelX.filter(in1, left, top, width, height);
		in2 = sobelY.filter(in2, left, top, width, height);

		int[][] out = new int[data.length][data[0].length];

		for (int x = 0; x < in1.length; x++)
		{
			for (int y = 0; y < in1[x].length; y++)
			{
				int v1 = in1[x][y];
				int v2 = in2[x][y];

				out[x][y] = (int) Math.hypot(v1, v2);
			}
		}

		return out;
	}
}
