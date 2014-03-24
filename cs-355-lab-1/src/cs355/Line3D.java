package cs355;

import cs355.solution.util.math.Vector3D;

/**
 * @author Brennan Smith
 */
public class Line3D
{
	public Vector3D	start;
	public Vector3D	end;

	public Line3D(Vector3D s, Vector3D e)
	{
		start = s;
		end = e;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Line3D [start=");
		builder.append(start);
		builder.append(", end=");
		builder.append(end);
		builder.append("]");
		return builder.toString();
	}
}
