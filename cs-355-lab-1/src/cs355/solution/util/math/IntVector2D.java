package cs355.solution.util.math;

import java.awt.Point;

public class IntVector2D
{
	public static final IntVector2D	ZERO	= new IntVector2D();
	public static final IntVector2D	X_AXIS	= new IntVector2D(1, 0);
	public static final IntVector2D	Y_AXIS	= new IntVector2D(0, 1);

	public int						x;
	public int						y;

	public IntVector2D()
	{
		x = 0;
		y = 0;
	}

	public IntVector2D(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public IntVector2D(Point p)
	{
		x = p.x;
		y = p.y;
	}

	public IntVector2D(Point from, Point to)
	{
		x = to.x - from.x;
		y = to.y - from.y;
	}

	public IntVector2D(IntVector2D other)
	{
		x = other.x;
		y = other.y;
	}

	public IntVector2D(IntVector2D to, IntVector2D from)
	{
		x = to.x - from.x;
		y = to.y - from.y;
	}

	public IntVector2D(Vector2D other)
	{
		x = (int) other.x;
		y = (int) other.y;
	}

	public IntVector2D(Vector2D to, Vector2D from)
	{
		x = (int) (to.x - from.x);
		y = (int) (to.y - from.y);
	}

	public IntVector2D getCopy()
	{
		return new IntVector2D(x, y);
	}

	public IntVector2D copyValues(IntVector2D other)
	{
		x = other.x;
		y = other.y;

		return this;
	}

	public IntVector2D copyValues(int x, int y)
	{
		this.x = x;
		this.y = y;

		return this;
	}

	public IntVector2D add(IntVector2D add)
	{
		x += add.x;
		y += add.y;

		return this;
	}

	public IntVector2D add(int dx, int dy)
	{
		x += dx;
		y += dy;

		return this;
	}

	public IntVector2D getAddedCopy(IntVector2D add)
	{
		return new IntVector2D(x + add.x, y + add.y);
	}

	public IntVector2D getAddedCopy(int dx, int dy)
	{
		return new IntVector2D(x + dx, y + dy);
	}

	public IntVector2D subtract(IntVector2D sub)
	{
		x -= sub.x;
		y -= sub.y;

		return this;
	}

	public IntVector2D subtract(int dx, int dy)
	{
		x -= dx;
		y -= dy;

		return this;
	}

	public IntVector2D getSubtractedCopy(IntVector2D sub)
	{
		return new IntVector2D(x - sub.x, y - sub.y);
	}

	public IntVector2D getSubtractedCopy(int dx, int dy)
	{
		return new IntVector2D(x - dx, y - dy);
	}

	public int distanceTo(IntVector2D other)
	{
		int dx = x - other.x;
		int dy = y - other.y;

		return (int) Math.sqrt(dx * dx + dy * dy);
	}

	public static int distance(IntVector2D v1, IntVector2D v2)
	{
		return v1.distanceTo(v2);
	}

	public static int distance(int x1, int y1, int x2, int y2)
	{
		int dx = x1 - x2;
		int dy = y1 - y2;

		return (int) Math.sqrt(dx * dx + dy * dy);
	}

	public int distanceSquaredTo(IntVector2D other)
	{
		int dx = x - other.x;
		int dy = y - other.y;

		return dx * dx + dy * dy;
	}

	public static int distanceSquared(IntVector2D v1, IntVector2D v2)
	{
		return v1.distanceSquaredTo(v2);
	}

	public static int distanceSquared(int x1, int y1, int x2, int y2)
	{
		int dx = x1 - x2;
		int dy = y1 - y2;

		return dx * dx + dy * dy;
	}

	public int length()
	{
		return (int) Math.sqrt(x * x + y * y);
	}

	public int lengthSquared()
	{
		return x * x + y * y;
	}

	public IntVector2D invert()
	{
		x *= -1;
		y *= -1;

		return this;
	}

	public IntVector2D getInvertedCopy()
	{
		return new IntVector2D(x * -1, y * -1);
	}

	public IntVector2D scale(int scalar)
	{
		x *= scalar;
		y *= scalar;

		return this;
	}

	public IntVector2D getScaledCopy(int scalar)
	{
		return new IntVector2D(x * scalar, y * scalar);
	}

	public IntVector2D limit(int length)
	{
		int l = length();
		if (l > length)
		{
			int scalar = length / length();
			return scale(scalar);
		}
		else
			return this;
	}

	public IntVector2D getLimitedCopy(int length)
	{
		int l = length();
		if (l > length)
		{
			int scalar = length / length();
			return getScaledCopy(scalar);
		}
		else
			return getCopy();
	}

	public IntVector2D normalize()
	{
		return limit(1);
	}

	public IntVector2D getNormalizedCopy()
	{
		return getLimitedCopy(1);
	}

	public IntVector2D setLength(int length)
	{
		int scalar = length / length();
		return scale(scalar);
	}

	public IntVector2D getCopyOfLength(int length)
	{
		int scalar = length / length();
		return getScaledCopy(scalar);
	}

	public int dot(IntVector2D other)
	{
		return x * other.x + y * other.y;
	}

	public Vector3D get3DVector()
	{
		return new Vector3D(this);
	}

	public Vector3D get3DVector(int z)
	{
		return new Vector3D(this, z);
	}

	public String print()
	{
		return String.format("(%4.0f,%4.0f)", x, y);
	}

	public String print(int digitsAfterDecimal)
	{
		if (digitsAfterDecimal == 0)
			return print();

		String str = String.format("(%%%1$d.%2$df,%%%1$d.%2$df)", digitsAfterDecimal + 5, digitsAfterDecimal);
		return String.format(str, x, y);
	}

	public String print(int digitsBeforeDecimal, int digitsAfterDecimal)
	{
		if (digitsAfterDecimal > 0)
			digitsBeforeDecimal += 1;

		String str = String.format("(%%%1$d.%2$df,%%%1$d.%2$df)", digitsBeforeDecimal + digitsAfterDecimal, digitsAfterDecimal);
		return String.format(str, x, y);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Vector2D [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IntVector2D))
			return false;
		IntVector2D other = (IntVector2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
