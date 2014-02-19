package cs355.solution.util.math;

import java.awt.geom.AffineTransform;

public class Matrix
{
	float	m00, m01, m02, m03;
	float	m10, m11, m12, m13;
	float	m20, m21, m22, m23;
	float	m30, m31, m32, m33;

	public static Matrix getTranslateInstance(float x, float y)
	{
		Matrix m = new Matrix();
		m.m03 = x;
		m.m13 = y;

		return m;
	}

	/**
	 * Constructs a new identity matrix
	 */
	public Matrix()
	{
		m01 = m02 = m03 = m10 = m12 = m13 = m20 = m21 = m23 = m30 = m31 = m32 = 0f;
		m00 = m11 = m22 = m33 = 1f;
	}

	public Matrix(Matrix m)
	{
		copyValues(m);
	}

	public Matrix(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22,
			float m23, float m30, float m31, float m32, float m33)
	{
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m03 = m03;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m30 = m30;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
	}

	public Matrix copyValues(Matrix m)
	{
		m00 = m.m00;
		m01 = m.m01;
		m02 = m.m02;
		m03 = m.m03;
		m10 = m.m10;
		m11 = m.m11;
		m12 = m.m12;
		m13 = m.m13;
		m20 = m.m20;
		m21 = m.m21;
		m22 = m.m22;
		m23 = m.m23;
		m30 = m.m30;
		m31 = m.m31;
		m32 = m.m32;
		m33 = m.m33;

		return this;
	}

	public Matrix getCopy()
	{
		return new Matrix(this);
	}

	public float getValue(int row, int column)
	{
		switch (row * 4 + column)
		{
			case 0:
				return m00;
			case 1:
				return m01;
			case 2:
				return m02;
			case 3:
				return m03;
			case 4:
				return m10;
			case 5:
				return m11;
			case 6:
				return m12;
			case 7:
				return m13;
			case 8:
				return m20;
			case 9:
				return m21;
			case 10:
				return m22;
			case 11:
				return m23;
			case 12:
				return m30;
			case 13:
				return m31;
			case 14:
				return m32;
			case 15:
				return m33;
			default:
				return Float.NaN;
		}
	}

	public Matrix setValue(int row, int column, float val)
	{
		switch (row * 4 + column)
		{
			case 0:
				m00 = val;
				break;
			case 1:
				m01 = val;
				break;
			case 2:
				m02 = val;
				break;
			case 3:
				m03 = val;
				break;
			case 4:
				m10 = val;
				break;
			case 5:
				m11 = val;
				break;
			case 6:
				m12 = val;
				break;
			case 7:
				m13 = val;
				break;
			case 8:
				m20 = val;
				break;
			case 9:
				m21 = val;
				break;
			case 10:
				m22 = val;
				break;
			case 11:
				m23 = val;
				break;
			case 12:
				m30 = val;
				break;
			case 13:
				m31 = val;
				break;
			case 14:
				m32 = val;
				break;
			case 15:
				m33 = val;
				break;
		}

		return this;
	}

	public Matrix setValues(float val)
	{
		m00 = m01 = m02 = m03 = m10 = m11 = m12 = m13 = m20 = m21 = m22 = m23 = m30 = m31 = m32 = m33 = val;

		return this;
	}

	public Matrix setValues(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21,
			float m22, float m23, float m30, float m31, float m32, float m33)
	{
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m03 = m03;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m30 = m30;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;

		return this;
	}

	public Matrix transpose()
	{
		float temp = m01;
		m01 = m10;
		m10 = temp;

		temp = m02;
		m02 = m20;
		m20 = temp;

		temp = m03;
		m03 = m30;
		m30 = temp;

		temp = m12;
		m12 = m21;
		m21 = temp;

		temp = m13;
		m13 = m31;
		m31 = temp;

		temp = m23;
		m23 = m32;
		m32 = temp;

		return this;
	}

	public Matrix getTransposedCopy()
	{
		return new Matrix(m00, m10, m20, m30, m01, m11, m21, m31, m02, m12, m22, m32, m03, m13, m23, m33);
	}

	public Matrix add(Matrix m)
	{
		m00 += m.m00;
		m01 += m.m01;
		m02 += m.m02;
		m03 += m.m03;
		m10 += m.m10;
		m11 += m.m11;
		m12 += m.m12;
		m13 += m.m13;
		m20 += m.m20;
		m21 += m.m21;
		m22 += m.m22;
		m23 += m.m23;
		m30 += m.m30;
		m31 += m.m31;
		m32 += m.m32;
		m33 += m.m33;

		return this;
	}

	public Matrix getAddedCopy(Matrix m)
	{
		return getCopy().add(m);
	}

	public Matrix subtract(Matrix m)
	{
		m00 -= m.m00;
		m01 -= m.m01;
		m02 -= m.m02;
		m03 -= m.m03;
		m10 -= m.m10;
		m11 -= m.m11;
		m12 -= m.m12;
		m13 -= m.m13;
		m20 -= m.m20;
		m21 -= m.m21;
		m22 -= m.m22;
		m23 -= m.m23;
		m30 -= m.m30;
		m31 -= m.m31;
		m32 -= m.m32;
		m33 -= m.m33;

		return this;
	}

	public Matrix getSubtractedCopy(Matrix m)
	{
		return getCopy().subtract(m);
	}

	public Matrix multiply(Matrix m)
	{
		float m00 = this.m00 * m.m00 + m01 * m.m10 + m02 * m.m20 + m03 * m.m30;
		float m01 = this.m00 * m.m01 + this.m01 * m.m11 + m02 * m.m21 + m03 * m.m31;
		float m02 = this.m00 * m.m02 + this.m01 * m.m12 + this.m02 * m.m22 + m03 * m.m32;
		float m03 = this.m00 * m.m03 + this.m01 * m.m13 + this.m02 * m.m23 + this.m03 * m.m33;

		float m10 = this.m10 * m.m00 + m11 * m.m10 + m12 * m.m20 + m13 * m.m30;
		float m11 = this.m10 * m.m01 + this.m11 * m.m11 + m12 * m.m21 + m13 * m.m31;
		float m12 = this.m10 * m.m02 + this.m11 * m.m12 + this.m12 * m.m22 + m13 * m.m32;
		float m13 = this.m10 * m.m03 + this.m11 * m.m13 + this.m12 * m.m23 + this.m13 * m.m33;

		float m20 = this.m20 * m.m00 + m21 * m.m10 + m22 * m.m20 + m23 * m.m30;
		float m21 = this.m20 * m.m01 + this.m21 * m.m11 + m22 * m.m21 + m23 * m.m31;
		float m22 = this.m20 * m.m02 + this.m21 * m.m12 + this.m22 * m.m22 + m23 * m.m32;
		float m23 = this.m20 * m.m03 + this.m21 * m.m13 + this.m22 * m.m23 + this.m23 * m.m33;

		float m30 = this.m30 * m.m00 + m31 * m.m10 + m32 * m.m20 + m33 * m.m30;
		float m31 = this.m30 * m.m01 + this.m31 * m.m11 + m32 * m.m21 + m33 * m.m31;
		float m32 = this.m30 * m.m02 + this.m31 * m.m12 + this.m32 * m.m22 + m33 * m.m32;
		float m33 = this.m30 * m.m03 + this.m31 * m.m13 + this.m32 * m.m23 + this.m33 * m.m33;

		return setValues(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}

	public Matrix getMultipliedCopy(Matrix m)
	{
		return getCopy().multiply(m);
	}

	public boolean isIdentity()
	{
		return m00 == 1 && m01 == 0 && m02 == 0 && m03 == 0 && m10 == 0 && m11 == 1 && m12 == 0 && m13 == 0 && m20 == 0 && m21 == 0
				&& m22 == 1 && m23 == 0 && m30 == 0 && m31 == 0 && m32 == 0 && m33 == 1;
	}

	public Matrix scale(float scale)
	{
		m00 *= scale;
		m11 *= scale;
		m22 *= scale;

		return this;
	}

	public Matrix getScaledCopy(float scale)
	{
		return getCopy().scale(scale);
	}

	public float getScaleX()
	{
		return m00;
	}

	public float getScaleY()
	{
		return m11;
	}

	public float getScaleZ()
	{
		return m22;
	}

	public Matrix setXRotation(float angle)
	{
		m11 = (float) Math.cos(angle);
		m12 = (float) -Math.sin(angle);
		m21 = (float) Math.sin(angle);
		m22 = (float) Math.cos(angle);

		return this;
	}

	public Matrix rotateX(float angle)
	{
		Matrix m = new Matrix();
		m.setXRotation(angle);

		return multiply(m);
	}

	public Matrix getRotatedXCopy(float angle)
	{
		return getCopy().rotateX(angle);
	}

	public Matrix setYRotation(float angle)
	{
		m00 = (float) Math.cos(angle);
		m02 = (float) Math.sin(angle);
		m20 = (float) -Math.sin(angle);
		m22 = (float) Math.cos(angle);

		return this;
	}

	public Matrix rotateY(float angle)
	{
		Matrix m = new Matrix();
		m.setYRotation(angle);

		return multiply(m);
	}

	public Matrix getRotatedYCopy(float angle)
	{
		return getCopy().rotateY(angle);
	}

	public Matrix setZRotation(float angle)
	{
		m00 = (float) Math.cos(angle);
		m01 = (float) -Math.sin(angle);
		m10 = (float) Math.sin(angle);
		m11 = (float) Math.cos(angle);

		return this;
	}

	public Matrix rotateZ(float angle)
	{
		Matrix m = new Matrix();
		m.setZRotation(angle);

		return copyValues(m.multiply(this));
	}

	public Matrix getRotatedZCopy(float angle)
	{
		return getCopy().rotateZ(angle);
	}

	public Matrix rotate2D(float angle)
	{
		return rotateZ(angle);
	}

	public Matrix getRotated2DCopy(float angle)
	{
		return getRotatedZCopy(angle);
	}

	public Matrix translate(float x, float y, float z)
	{
		m03 += x;
		m13 += y;
		m23 += z;

		return this;
	}

	public Matrix translate2D(float x, float y)
	{
		return translate(x, y, 0);
	}

	public Matrix setTranslation(float x, float y, float z)
	{
		m03 = x;
		m13 = y;
		m23 = z;

		return this;
	}

	public Matrix setTranslation(Vector3D v)
	{
		return setTranslation(v.x, v.y, v.z);
	}

	public Matrix setTranslation2D(float x, float y)
	{
		return setTranslation(x, y, m23);
	}

	public Matrix setTranslation2D(Vector2D v)
	{
		return setTranslation(v.x, v.y, m23);
	}

	public AffineTransform toAffineTransform()
	{
		double[] arr = new double[]
		{
				m00,
				m10,
				m01,
				m11,
				m03,
				m13
		};

		return new AffineTransform(arr);
	}

	@Override
	public String toString()
	{
		return String.format(
				"Matrix [\n\t%6.2f %6.2f %6.2f %6.2f\n\t%6.2f %6.2f %6.2f %6.2f\n\t%6.2f %6.2f %6.2f %6.2f\n\t%6.2f %6.2f %6.2f %6.2f\n]",
				m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(m00);
		result = prime * result + Float.floatToIntBits(m01);
		result = prime * result + Float.floatToIntBits(m02);
		result = prime * result + Float.floatToIntBits(m03);
		result = prime * result + Float.floatToIntBits(m10);
		result = prime * result + Float.floatToIntBits(m11);
		result = prime * result + Float.floatToIntBits(m12);
		result = prime * result + Float.floatToIntBits(m13);
		result = prime * result + Float.floatToIntBits(m20);
		result = prime * result + Float.floatToIntBits(m21);
		result = prime * result + Float.floatToIntBits(m22);
		result = prime * result + Float.floatToIntBits(m23);
		result = prime * result + Float.floatToIntBits(m30);
		result = prime * result + Float.floatToIntBits(m31);
		result = prime * result + Float.floatToIntBits(m32);
		result = prime * result + Float.floatToIntBits(m33);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Matrix))
			return false;
		Matrix other = (Matrix) obj;
		if (Float.floatToIntBits(m00) != Float.floatToIntBits(other.m00))
			return false;
		if (Float.floatToIntBits(m01) != Float.floatToIntBits(other.m01))
			return false;
		if (Float.floatToIntBits(m02) != Float.floatToIntBits(other.m02))
			return false;
		if (Float.floatToIntBits(m03) != Float.floatToIntBits(other.m03))
			return false;
		if (Float.floatToIntBits(m10) != Float.floatToIntBits(other.m10))
			return false;
		if (Float.floatToIntBits(m11) != Float.floatToIntBits(other.m11))
			return false;
		if (Float.floatToIntBits(m12) != Float.floatToIntBits(other.m12))
			return false;
		if (Float.floatToIntBits(m13) != Float.floatToIntBits(other.m13))
			return false;
		if (Float.floatToIntBits(m20) != Float.floatToIntBits(other.m20))
			return false;
		if (Float.floatToIntBits(m21) != Float.floatToIntBits(other.m21))
			return false;
		if (Float.floatToIntBits(m22) != Float.floatToIntBits(other.m22))
			return false;
		if (Float.floatToIntBits(m23) != Float.floatToIntBits(other.m23))
			return false;
		if (Float.floatToIntBits(m30) != Float.floatToIntBits(other.m30))
			return false;
		if (Float.floatToIntBits(m31) != Float.floatToIntBits(other.m31))
			return false;
		if (Float.floatToIntBits(m32) != Float.floatToIntBits(other.m32))
			return false;
		if (Float.floatToIntBits(m33) != Float.floatToIntBits(other.m33))
			return false;
		return true;
	}
}
