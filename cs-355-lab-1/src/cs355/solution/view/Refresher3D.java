package cs355.solution.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Iterator;

import cs355.Line3D;
import cs355.solution.controller.Controller3D;

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
			Iterator<Line3D> house = controller.getModel().getLines();
			g.setColor(Color.cyan);

			Stroke originalStroke = g.getStroke();
			Stroke stroke = new BasicStroke(1f / zoom);
			g.setStroke(stroke);

			while (house.hasNext())
			{
				Line3D line = house.next();

				line = controller.transform(line);

				if (line != null)
				{
					g.drawLine((int) line.start.x, (int) line.start.y, (int) line.end.x, (int) line.end.y);
				}
			}

			g.setStroke(originalStroke);
		}
	}
}
