package cs355.solution.model.images;

import java.util.Arrays;

class MedianMask extends Mask
{
	public MedianMask(int size)
	{
		super(size, size);
	}

	public MedianMask(int m, int n)
	{
		super(m, n);
	}

	@Override
	protected int maskPoint(int[][] in, int i, int j)
	{
		int left = Math.max(0, i - ((m - 1) / 2));
		int top = Math.max(0, j - ((n - 1) / 2));
		int right = Math.min(in.length - 1, i + (m / 2));
		int bottom = Math.min(in[i].length - 1, j + (n / 2));

		double[] values = new double[m * n];

		for (int x = left; x <= right; x++)
		{
			for (int y = top; y <= bottom; y++)
			{
				int val = in[x][y];
				values[(x - left) * n + (y - top)] = val;
			}
		}

		Arrays.sort(values);

		double high = values[values.length / 2];
		double low = values[(values.length - 1) / 2];
		return (int) ((high + low) * 0.5);
	}
}