package cs355.solution.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Iterator;

import cs355.Line3D;
import cs355.solution.controller.Controller3D;
import cs355.solution.util.math.Vector3D;

public class Refresher3D
{
	private Controller3D	controller;

	public void setController(Controller3D controller)
	{
		this.controller = controller;
	}

	public void draw(Graphics2D g, float zoom)
	{
		if (controller.isEnabled())
		{
			Iterator<Line3D> house = controller.getHouseModel().getLines();
			g.setColor(Color.cyan);

			Stroke originalStroke = g.getStroke();
			Stroke stroke = new BasicStroke(1f / zoom);
			g.setStroke(stroke);

			while (house.hasNext())
			{
				Line3D line = house.next();

				line = transform(line);

				if (line != null)
				{
					g.drawLine((int) line.start.x, (int) line.start.y, (int) line.end.x, (int) line.end.y);
				}
			}

			g.setStroke(originalStroke);
		}
	}

	private Vector3D objectToClip(Vector3D o)
	{
		return o.getMultipliedCopy(controller.getObjectToClipTransform());
	}

	private Line3D transform(Line3D line)
	{
		Vector3D start = objectToClip(line.start);
		Vector3D end = objectToClip(line.end);

		if (clip(start, end))
			return null;

		start.scale(1f / start.w);
		end.scale(1f / end.w);

		start.multiply(controller.getClipToScreenTransform());
		end.multiply(controller.getClipToScreenTransform());

		return new Line3D(start, end);
	}

	private boolean clip(Vector3D start, Vector3D end)
	{
		if (start.x > start.w && end.x > end.w)
			return true;
		if (start.x < -start.w && end.x < -end.w)
			return true;
		if (start.y > start.w && end.y > end.w)
			return true;
		if (start.y < -start.w && end.y < -end.w)
			return true;
		if (start.z > start.w && end.z > end.w)
			return true;
		if (start.z < -start.w || end.z < -end.w)
			return true;

		return false;
	}
}
