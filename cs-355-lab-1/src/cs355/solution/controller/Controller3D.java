package cs355.solution.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import cs355.HouseModel;
import cs355.Line3D;
import cs355.WireFrame;
import cs355.solution.util.math.Matrix;
import cs355.solution.util.math.Vector3D;

public class Controller3D
{
	private final int		view_height		= 2048;
	private final int		view_width		= 2048;
	private final float		aspect			= view_width * 1f / view_height;
	private final float		fov_x			= (float) (Math.PI * 0.35);
	private final float		fov_y			= fov_x * aspect;
	private final float		zoom_x			= (float) (1.0 / Math.tan(fov_x * 0.5));
	private final float		zoom_y			= (float) (1.0 / Math.tan(fov_y * 0.5));
	private final float		scalar			= 0.25f;
	private final float		z_near			= 0.1f;
	private final int		z_far			= 100;
	private final float		rotate_speed	= (float) (Math.PI / 128f);

	private Controller		controller;
	private boolean			enabled;
	private WireFrame		model;

	private final Vector3D	forward;
	private final Vector3D	up				= Vector3D.Y_AXIS.getScaledCopy(scalar);
	private final Vector3D	camera;
	private boolean			dirty;

	private final Matrix	wtocTranslation;
	private final Matrix	wtocRotation;
	private final Matrix	clip;

	private Matrix			wtoc;
	private Matrix			wtoClip;
	private final Matrix	clipToScreen;

	public Controller3D(Controller controller)
	{
		this();
		this.controller = controller;
	}

	public Controller3D()
	{
		enable(false);

		camera = new Vector3D(0, -4, -20);
		forward = Vector3D.Z_AXIS.getScaledCopy(-1f);

		wtocTranslation = Matrix.createTranslationMatrix(camera);
		clip = Matrix.createClipMatrix(zoom_x, zoom_y, z_far, z_near);
		wtocRotation = Matrix.createWorldToCameraRotationMatrix(camera, camera.getAddedCopy(forward), up);
		clipToScreen = Matrix.createClipToScreenMatrix(view_width, view_height);

		dirty = true;

		if (controller != null)
		{}
	}

	public Vector3D objectToCamera(Vector3D o)
	{
		return o.getMultipliedCopy(getObjectToCameraTransform());
	}

	public Vector3D objectToClip(Vector3D o)
	{
		return o.getMultipliedCopy(getObjectToClipTransform());
	}

	private Matrix getObjectToCameraTransform()
	{
		if (dirty)
			update();

		return wtoc;
	}

	private Matrix getObjectToClipTransform()
	{
		if (dirty)
			update();

		return wtoClip;
	}

	private void update()
	{
		wtoc = wtocRotation.getMultipliedCopy(wtocTranslation);
		wtoClip = clip.getMultipliedCopy(wtoc);
		dirty = false;
	}

	public Line3D transform(Line3D line)
	{
		Vector3D start = objectToClip(line.start);
		Vector3D end = objectToClip(line.end);

		if (clip(start, end))
			return null;

		start.scale(1f / start.w);
		end.scale(1f / end.w);

		start.multiply(clipToScreen);
		end.multiply(clipToScreen);

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

	public boolean isEnabled()
	{
		return enabled;
	}

	public void enable()
	{
		enable(true);
	}

	public void enable(boolean enable)
	{
		enabled = enable;
		if (enabled)
		{
			switch ((int) (Math.random() * 1))
			{
				case 0:
					model = new HouseModel();
			}
		}
	}

	public boolean keyPressed(Iterator<Integer> itr)
	{
		ArrayList<Integer> keys = new ArrayList<Integer>();
		while (itr.hasNext())
			keys.add(itr.next());

		if (keys.contains(KeyEvent.VK_W))
		{
			Vector3D trans = forward.getInvertedCopy();
			wtocTranslation.translate(trans);
			dirty = true;
		}
		if (keys.contains(KeyEvent.VK_S))
		{
			wtocTranslation.translate(forward);
			dirty = true;
		}
		if (keys.contains(KeyEvent.VK_A))
		{
			Vector3D left = up.cross(forward);
			wtocTranslation.translate(left);
			dirty = true;
		}
		if (keys.contains(KeyEvent.VK_D))
		{
			Vector3D right = forward.cross(up);
			wtocTranslation.translate(right);
			dirty = true;
		}
		if (keys.contains(KeyEvent.VK_Q))
		{
			forward.rotateY(-rotate_speed);
			wtocRotation.rotateY(rotate_speed);
			dirty = true;
		}
		if (keys.contains(KeyEvent.VK_E))
		{
			forward.rotateY(rotate_speed);
			wtocRotation.rotateY(-rotate_speed);
			dirty = true;
		}
		if (keys.contains(KeyEvent.VK_R))
		{
			Vector3D trans = up.getInvertedCopy();
			wtocTranslation.translate(trans);
			dirty = true;
		}
		if (keys.contains(KeyEvent.VK_F))
		{
			wtocTranslation.translate(up);
			dirty = true;
		}

		return dirty;
	}

	public void draw(Graphics2D g, float zoom)
	{
		if (enabled)
		{
			Iterator<Line3D> house = model.getLines();
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
}
