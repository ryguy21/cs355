package cs355.solution.util.math;

public class Vector3D
{
	public static final Vector3D	ZERO	= new Vector3D();
	public static final Vector3D	X_AXIS	= new Vector3D(1, 0, 0, 0);
	public static final Vector3D	Y_AXIS	= new Vector3D(0, 1, 0, 0);
	public static final Vector3D	Z_AXIS	= new Vector3D(0, 0, 1, 0);

	public float					x;
	public float					y;
	public float					z;
	public float					w;

	public Vector3D()
	{
		this(0, 0, 0, 0);
	}

	public Vector3D(Vector2D v)
	{
		this(v.x, v.y, 0, 0);
	}

	public Vector3D(Vector2D v, float z)
	{
		this(v.x, v.y, z, 0);
	}

	public Vector3D(Vector3D o)
	{
		this(o.x, o.y, o.y, o.w);
	}

	public Vector3D(Vector3D from, Vector3D to)
	{
		this(to.x - from.x, to.y - from.y, to.z - from.z, 0);
	}

	public Vector3D(float x, float y, float z)
	{
		this(x, y, z, 0);
	}

	public Vector3D(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vector3D getCopy()
	{
		return new Vector3D(x, y, z, w);
	}

	public Vector3D copyValues(Vector3D other)
	{
		x = other.x;
		y = other.y;
		z = other.z;
		w = other.w;

		return this;
	}

	public Vector3D copyValues(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;

		return this;
	}

	public Vector3D copyValues(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;

		return this;
	}

	public Vector3D add(Vector3D add)
	{
		x += add.x;
		y += add.y;
		z += add.z;

		return this;
	}

	public Vector3D add(float dx, float dy, float dz)
	{
		x += dx;
		y += dy;
		z += dz;

		return this;
	}

	public Vector3D getAddedCopy(Vector3D add)
	{
		return new Vector3D(x + add.x, y + add.y, z + add.z, w);
	}

	public Vector3D getAddedCopy(float dx, float dy, float dz)
	{
		return new Vector3D(x + dx, y + dy, z + dz, w);
	}

	public Vector3D subtract(Vector3D sub)
	{
		x -= sub.x;
		y -= sub.y;
		z -= sub.z;

		return this;
	}

	public Vector3D subtract(float dx, float dy, float dz)
	{
		x -= dx;
		y -= dy;
		z -= dz;

		return this;
	}

	public Vector3D getSubtractedCopy(Vector3D sub)
	{
		return new Vector3D(x - sub.x, y - sub.y, z - sub.z, w);
	}

	public Vector3D getSubtractedCopy(float dx, float dy, float dz)
	{
		return new Vector3D(x - dx, y - dy, z - dz, w);
	}

	public float distanceTo(Vector3D other)
	{
		float dx = x - other.x;
		float dy = y - other.y;
		float dz = z - other.z;

		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public static float distance(Vector3D v1, Vector3D v2)
	{
		return v1.distanceTo(v2);
	}

	public static float distance(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		float dx = x1 - x2;
		float dy = y1 - y2;
		float dz = z1 - z2;

		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public float distanceSquaredTo(Vector3D other)
	{
		float dx = x - other.x;
		float dy = y - other.y;
		float dz = z - other.z;

		return dx * dx + dy * dy + dz * dz;
	}

	public static float distanceSquared(Vector3D v1, Vector3D v2)
	{
		return v1.distanceSquaredTo(v2);
	}

	public static float distanceSquared(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		float dx = x1 - x2;
		float dy = y1 - y2;
		float dz = z1 - z2;

		return dx * dx + dy * dy + dz * dz;
	}

	public float length()
	{
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float lengthSquared()
	{
		return x * x + y * y + z * z;
	}

	public Vector3D invert()
	{
		x *= -1;
		y *= -1;
		z *= -1;

		return this;
	}

	public Vector3D getInvertedCopy()
	{
		return new Vector3D(x * -1, y * -1, z * -1);
	}

	public Vector3D scale(float scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;

		return this;
	}

	public Vector3D getScaledCopy(float scalar)
	{
		return new Vector3D(x * scalar, y * scalar, z * scalar);
	}

	public Vector3D limit(float length)
	{
		float l = length();
		if (l > length)
		{
			float scalar = length / length();
			return scale(scalar);
		}
		else
			return this;
	}

	public Vector3D getLimitedCopy(float length)
	{
		float l = length();
		if (l > length)
		{
			float scalar = length / length();
			return getScaledCopy(scalar);
		}
		else
			return getCopy();
	}

	public Vector3D normalize()
	{
		return limit(1);
	}

	public Vector3D getNormalizedCopy()
	{
		return getLimitedCopy(1);
	}

	public Vector3D setLength(float length)
	{
		float scalar = length / length();
		return scale(scalar);
	}

	public Vector3D getCopyOfLength(float length)
	{
		float scalar = length / length();
		return getScaledCopy(scalar);
	}

	public float dot(Vector3D other)
	{
		return x * other.x + y * other.y + z * other.z;
	}

	public Vector3D cross(Vector3D o)
	{
		float _x = y * o.z - z * o.y;
		float _y = z * o.x - x * o.z;
		float _z = x * o.y - y * o.x;
		float _w = 0;

		return new Vector3D(_x, _y, _z, _w);
	}

	public String print()
	{
		return String.format("(%5.1f,%5.1f,%5.1f,%5.1f)", x, y, z, w);
	}

	public String print(int digitsAfterDecimal)
	{
		if (digitsAfterDecimal == 0)
			return print();

		String str = String.format("(%%%1$d.%2$df,%%%1$d.%2$df,%%%1$d.%2$df,%%%1$d.%2$df)", digitsAfterDecimal + 5, digitsAfterDecimal);
		return String.format(str, x, y, z, w);
	}

	public String print(int digitsBeforeDecimal, int digitsAfterDecimal)
	{
		if (digitsAfterDecimal > 0)
			digitsBeforeDecimal += 1;

		String str = String.format("(%%%1$d.%2$df,%%%1$d.%2$df,%%%1$d.%2$df,%%%1$d.%2$df)", digitsBeforeDecimal + digitsAfterDecimal,
				digitsAfterDecimal);
		return String.format(str, x, y, z, w);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(w);
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector3D))
			return false;
		Vector3D other = (Vector3D) obj;
		if (Float.floatToIntBits(w) != Float.floatToIntBits(other.w))
			return false;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Vector3D [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append(", w=");
		builder.append(w);
		builder.append("]");
		return builder.toString();
	}
}
