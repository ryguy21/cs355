package cs355.solution.model.images;

import cs355.solution.util.ImageUtils;

class Mask
{
	protected double[]	mask;
	protected int		n, m;
	protected double	scalar;

	public Mask(int size)
	{
		this(size, size);
	}

	public Mask(int m, int n)
	{
		mask = new double[m * n];
		this.m = m;
		this.n = n;
		scalar = 1;
	}

	public void setValues(double value)
	{
		for (int i = 0; i < m * n; i++)
			mask[i] = value;
	}

	public void setValue(int i, int j, double value)
	{
		mask[i * n + j] = value;
	}

	public void setScalar(double scalar)
	{
		this.scalar = scalar;
	}

	public double getValue(int i, int j)
	{
		return mask[i * n + j];
	}

	public int[][] filter(int[][] data, int left, int top, int width, int height)
	{
		int[][] out = new int[Image.WIDTH][Image.HEIGHT];

		int right = left + width;
		int bottom = top + height;

		for (int x = left; x < right; x++)
			for (int y = top; y < bottom; y++)
				out[x][y] = maskPoint(data, x, y);

		return out;
	}

	protected int maskPoint(int[][] in, int i, int j)
	{
		int left = Math.max(0, i - ((m - 1) / 2));
		int top = Math.max(0, j - ((n - 1) / 2));
		int right = Math.min(in.length - 1, i + (m / 2));
		int bottom = Math.min(in[i].length - 1, j + (n / 2));

		int total = 0;
		for (int x = left; x <= right; x++)
		{
			for (int y = top; y <= bottom; y++)
			{
				int val = in[x][y];
				total += val * getValue(x - left, y - top);
			}
		}

		return ImageUtils.normalize(total / scalar);
	}

	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(getClass().getSimpleName());
		s.append(" [");
		s.append(m);
		s.append("x");
		s.append(n);
		s.append("\n");

		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < n; j++)
			{
				s.append(String.format(" %6.3f", mask[i * n + j]));
			}
			s.append("\n");
		}

		s.append("scalar=");
		s.append(scalar);
		s.append("]");

		return s.toString();
	}
}