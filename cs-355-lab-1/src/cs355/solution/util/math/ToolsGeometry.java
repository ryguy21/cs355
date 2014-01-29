package cs355.solution.util.math;

import cs355.solution.model.shapes.Line;

public class ToolsGeometry
{
	public static float calculateAngle(float dx, float dy)
	{
		if (dx == 0f)
		{
			if (dy == 0f)
			{
				return 0f;
			}
			else if (dy > 0f)
			{
				return (float) (Math.PI * 0.5d);
			}
			else
			{
				return (float) (Math.PI * 1.5d);
			}
		}

		float theta = (float) Math.atan(dy / dx);

		if (dx < 0f)
			theta += Math.PI;
		else if (dx > 0f && dy < 0f)
			theta += 2f * Math.PI;

		return theta;
	}

	public static float distanceFromPointToLine(Vector2D point, Line line)
	{
		return -1f;
	}
}
