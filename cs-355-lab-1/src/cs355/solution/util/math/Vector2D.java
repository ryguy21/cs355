package cs355.solution.util.math;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Vector2D
{
	public static final Vector2D	ZERO	= new Vector2D();
	public static final Vector2D	X_AXIS	= new Vector2D(1, 0);
	public static final Vector2D	Y_AXIS	= new Vector2D(0, 1);

	public float					x;
	public float					y;

	public Vector2D()
	{
		x = 0;
		y = 0;
	}

	public Vector2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2D(Point p)
	{
		x = p.x;
		y = p.y;
	}

	public Vector2D(Point from, Point to)
	{
		x = to.x - from.x;
		y = to.y - from.y;
	}

	public Vector2D(Vector2D other)
	{
		x = other.x;
		y = other.y;
	}

	public Vector2D(Vector2D to, Vector2D from)
	{
		x = to.x - from.x;
		y = to.y - from.y;
	}

	public Vector2D(Point2D p)
	{
		x = (float) p.getX();
		y = (float) p.getY();
	}

	public Vector2D getCopy()
	{
		return new Vector2D(x, y);
	}

	public Vector2D copyValues(Vector2D other)
	{
		x = other.x;
		y = other.y;

		return this;
	}

	public Vector2D copyValues(float x, float y)
	{
		this.x = x;
		this.y = y;

		return this;
	}

	public Vector2D add(Vector2D add)
	{
		x += add.x;
		y += add.y;

		return this;
	}

	public Vector2D add(float dx, float dy)
	{
		x += dx;
		y += dy;

		return this;
	}

	public Vector2D getAddedCopy(Vector2D add)
	{
		return new Vector2D(x + add.x, y + add.y);
	}

	public Vector2D getAddedCopy(float dx, float dy)
	{
		return new Vector2D(x + dx, y + dy);
	}

	public Vector2D subtract(Vector2D sub)
	{
		x -= sub.x;
		y -= sub.y;

		return this;
	}

	public Vector2D subtract(float dx, float dy)
	{
		x -= dx;
		y -= dy;

		return this;
	}

	public Vector2D getSubtractedCopy(Vector2D sub)
	{
		return new Vector2D(x - sub.x, y - sub.y);
	}

	public Vector2D getSubtractedCopy(float dx, float dy)
	{
		return new Vector2D(x - dx, y - dy);
	}

	public float distanceTo(Vector2D other)
	{
		float dx = x - other.x;
		float dy = y - other.y;

		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	public static float distance(Vector2D v1, Vector2D v2)
	{
		return v1.distanceTo(v2);
	}

	public static float distance(float x1, float y1, float x2, float y2)
	{
		float dx = x1 - x2;
		float dy = y1 - y2;

		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	public float distanceSquaredTo(Vector2D other)
	{
		float dx = x - other.x;
		float dy = y - other.y;

		return dx * dx + dy * dy;
	}

	public static float distanceSquared(Vector2D v1, Vector2D v2)
	{
		return v1.distanceSquaredTo(v2);
	}

	public static float distanceSquared(float x1, float y1, float x2, float y2)
	{
		float dx = x1 - x2;
		float dy = y1 - y2;

		return dx * dx + dy * dy;
	}

	public float length()
	{
		return (float) Math.sqrt(x * x + y * y);
	}

	public float lengthSquared()
	{
		return x * x + y * y;
	}

	public Vector2D invert()
	{
		x *= -1;
		y *= -1;

		return this;
	}

	public Vector2D getInvertedCopy()
	{
		return new Vector2D(x * -1, y * -1);
	}

	public Vector2D scale(float scalar)
	{
		x *= scalar;
		y *= scalar;

		return this;
	}

	public Vector2D getScaledCopy(float scalar)
	{
		return new Vector2D(x * scalar, y * scalar);
	}

	public Vector2D limit(float length)
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

	public Vector2D getLimitedCopy(float length)
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

	public Vector2D normalize()
	{
		float l = length();
		return scale(1 / l);
	}

	public Vector2D getNormalizedCopy()
	{
		float l = length();
		return getScaledCopy(1 / l);
	}

	public Vector2D setLength(float length)
	{
		float scalar = length / length();
		return scale(scalar);
	}

	public Vector2D getCopyOfLength(float length)
	{
		float scalar = length / length();
		return getScaledCopy(scalar);
	}

	public float dot(Vector2D other)
	{
		return x * other.x + y * other.y;
	}

	public static Vector2D average(Vector2D... vs)
	{
		float x = 0f, y = 0f;

		for (Vector2D v : vs)
		{
			x += v.x;
			y += v.y;
		}

		return new Vector2D(x / vs.length, y / vs.length);
	}

	public Vector2D getPerpendicular()
	{
		return new Vector2D(-y, x);
	}

	public float polarAngle()
	{
		return (float) Math.atan2(x, y);
	}

	public static float angleBetween(Vector2D v1, Vector2D v2)
	{
		float a1 = v1.polarAngle();
		float a2 = v2.polarAngle();

		return a1 - a2;
	}

	public Vector2D projectToCoordinates(Vector2D xaxis, Vector2D yaxis)
	{
		float x = xaxis.getNormalizedCopy().dot(this);
		float y = yaxis.getNormalizedCopy().dot(this);

		return new Vector2D(x, y);
	}

	public Vector2D multiply(Matrix m)
	{
		x = m.m00 * x + m.m01 * y + m.m03;
		y = m.m10 * x + m.m11 * y + m.m13;

		return this;
	}

	public Vector2D getMultipliedCopy(Matrix m)
	{
		float x = m.m00 * this.x + m.m01 * y + m.m03;
		float y = m.m10 * this.x + m.m11 * this.y + m.m13;

		return new Vector2D(x, y);
	}

	public Vector2D projectOnto(Vector2D other)
	{
		other = other.getNormalizedCopy();
		float length = dot(other);
		other.scale(length);
		return this.copyValues(other);
	}

	public Vector2D getProjectedOnto(Vector2D other)
	{
		other = other.getNormalizedCopy();
		float length = dot(other);
		return other.scale(length);
	}

	public Vector2D rotate(float angle)
	{
		double cs = Math.cos(angle);
		double sn = Math.sin(angle);

		double px = x * cs - y * sn;
		double py = x * sn + y * cs;

		x = (float) px;
		y = (float) py;

		return this;
	}

	public Vector2D getRotatedCopy(float angle)
	{
		return getCopy().rotate(angle);
	}

	public Point2D toPoint2D()
	{
		return new Point2D.Float(x, y);
	}

	public Vector3D get3DVector()
	{
		return new Vector3D(this);
	}

	public Vector3D get3DVector(float z)
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
		builder.append(getClass().getSimpleName());
		builder.append(" [x=");
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
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector2D))
			return false;
		Vector2D other = (Vector2D) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}
}
