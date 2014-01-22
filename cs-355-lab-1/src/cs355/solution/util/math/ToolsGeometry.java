package cs355.solution.util.math;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ToolsGeometry
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				JFrame frame = new JFrame();
				frame.getContentPane().setLayout(new BorderLayout());
				frame.getContentPane().add(new JPanel()
				{
					private static final long	serialVersionUID	= -3169018354835325748L;

					@Override
					public void paintComponent(Graphics g1)
					{
						super.paintComponent(g1);
						Graphics2D g = (Graphics2D) g1;

						Vector2D from = new Vector2D(110, 10);
						Vector2D to = new Vector2D(10, 110);
						float radius = 100;
						boolean clockwise = false;
						boolean largeArc = false;
						int segments = 50;

						g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

						List<Vector2D> verts = generateArc(from, to, radius, clockwise, largeArc, segments);

						for (int i = 0; i < verts.size() - 1; i++)
						{
							Vector2D v1 = verts.get(i);
							Vector2D v2 = verts.get(i + 1);
							g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
						}
					}
				});
				frame.setPreferredSize(new Dimension(400, 300));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public static List<Vector2D> generateArc(Vector2D from, Vector2D to, float r, boolean clockwise, boolean largeArc, int segments)
	{
		// this algorithm is for coordinates with the lower left corner being 0, 0. Changing the clockwise setting alters the algorithm so
		// it
		// acts as if the top left corner is 0, 0.
		clockwise = !clockwise;

		double dx = to.x - from.x;
		double dy = to.y - from.y;

		double theta = Math.acos(Math.sqrt(dx * dx + dy * dy) / (2 * r));
		if (theta == Double.NaN)
			throw new IllegalArgumentException(String.format("The radius (%f) is not large enough - no arc can be created! from=%s to=%s",
					r, from.print(0, 6), to.print(0, 6)));

		double phi = calculateAngle(dx, dy);

		double gamma = phi;
		if (clockwise == largeArc)
			gamma += theta;
		else
			gamma -= theta;

		float cx = from.x;
		float cy = from.y;

		cx += r * Math.cos(gamma);
		cy += r * Math.sin(gamma);

		Vector2D arcCenter = new Vector2D(cx, cy);

		double startAngle = (gamma + Math.PI) % (2 * Math.PI);
		double endAngle = calculateAngle(to.x - arcCenter.x, to.y - arcCenter.y);

		if (clockwise && startAngle < endAngle)
			endAngle -= 2 * Math.PI;
		else if (!clockwise && endAngle < startAngle)
			endAngle += 2 * Math.PI;

		// System.out.printf("dx=%3.0f dy=%3.0f theta=%4.2fpi phi=%4.2fpi gamma=%4.2fpi center=%s, start=%4.2fpi end=%4.2fpi\n", dx, dy,
		// theta
		// / Math.PI, phi / Math.PI, gamma / Math.PI, c.print(2, 3), startAngle / Math.PI, endAngle / Math.PI);

		return generateArc(arcCenter, r, startAngle, endAngle, segments);
	}

	public static List<Vector2D> generateArc(Vector2D center, float r, double start, double end, int segments)
	{
		double total = start - end;
		double percentOfCircle = total / (2 * Math.PI);
		segments *= Math.abs(percentOfCircle);
		double dt = total / segments;

		List<Vector2D> verts = new ArrayList<Vector2D>(segments + 1);

		double theta = start;
		for (int i = 0; i <= segments; i++)
		{
			float x = center.x + (float) (r * Math.cos(theta));
			float y = center.y + (float) (r * Math.sin(theta));

			verts.add(new Vector2D(x, y));

			theta += dt;
		}

		return verts;
	}

	public static double calculateAngle(double dx, double dy)
	{
		// System.out.printf("dx=%20.15f dy=%20.15f ", dx, dy);

		if (dx == 0)
		{
			if (dy == 0)
			{
				// System.out.println("returning 0.0000pi");
				return 0;
			}
			else if (dy > 0)
			{
				// System.out.println("returning 0.5000pi");
				return Math.PI / 2;
			}
			else
			{
				// System.out.println("returning 1.5000pi");
				return Math.PI * 3 / 2;
			}
		}

		double phi = Math.atan(dy / dx);

		if (dx < 0)
			phi += Math.PI;
		else if (dx > 0 && dy < 0)
			phi += 2 * Math.PI;

		// System.out.printf("returning %6.4fpi\n", phi / Math.PI);

		return phi;
	}
}
