package cs355.solution.util.math;

public class Matrix3
{
	private float	m00, m01, m02;
	private float	m10, m11, m12;
	private float	m20, m21, m22;

	public Matrix3()
	{
		m01 = m02 = m10 = m12 = m20 = m21 = 0f;
		m00 = m11 = m22 = 1f;
	}

	public Matrix3(Matrix3 m)
	{
		copyValues(m);
	}

	public Matrix3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22)
	{
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
	}

	public void copyValues(Matrix3 m)
	{
		m00 = m.m00;
		m01 = m.m01;
		m02 = m.m02;
		m10 = m.m10;
		m11 = m.m11;
		m12 = m.m12;
		m20 = m.m20;
		m21 = m.m21;
		m22 = m.m22;
	}

	public Matrix3 getCopy()
	{
		return new Matrix3(this);
	}

	public Matrix3 transpose()
	{
		float temp = m01;
		m01 = m10;
		m10 = temp;

		temp = m02;
		m02 = m20;
		m20 = temp;

		temp = m12;
		m12 = m21;
		m21 = temp;

		return this;
	}

	public Matrix3 getTransposedCopy()
	{
		return new Matrix3(m00, m10, m20, m01, m11, m21, m02, m12, m22);
	}

}
