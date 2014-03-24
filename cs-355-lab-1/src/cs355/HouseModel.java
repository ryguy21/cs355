package cs355;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cs355.solution.util.math.Vector3D;

/**
 * 
 * @author Brennan Smith
 */
public class HouseModel extends WireFrame
{
	List<Line3D>	lines	= new ArrayList<>();

	public HouseModel()
	{
		// Make the object:
		// Floor
		lines.add(new Line3D(new Vector3D(-5, 0, -5, 1), new Vector3D(5, 0, -5, 1)));
		lines.add(new Line3D(new Vector3D(5, 0, -5, 1), new Vector3D(5, 0, 5, 1)));
		lines.add(new Line3D(new Vector3D(5, 0, 5, 1), new Vector3D(-5, 0, 5, 1)));
		lines.add(new Line3D(new Vector3D(-5, 0, 5, 1), new Vector3D(-5, 0, -5, 1)));
		// Ceiling
		lines.add(new Line3D(new Vector3D(-5, 5, -5, 1), new Vector3D(5, 5, -5, 1)));
		lines.add(new Line3D(new Vector3D(5, 5, -5, 1), new Vector3D(5, 5, 5, 1)));
		lines.add(new Line3D(new Vector3D(5, 5, 5, 1), new Vector3D(-5, 5, 5, 1)));
		lines.add(new Line3D(new Vector3D(-5, 5, 5, 1), new Vector3D(-5, 5, -5, 1)));
		// Walls
		lines.add(new Line3D(new Vector3D(-5, 5, -5, 1), new Vector3D(-5, 0, -5, 1)));
		lines.add(new Line3D(new Vector3D(5, 5, -5, 1), new Vector3D(5, 0, -5, 1)));
		lines.add(new Line3D(new Vector3D(5, 5, 5, 1), new Vector3D(5, 0, 5, 1)));
		lines.add(new Line3D(new Vector3D(-5, 5, 5, 1), new Vector3D(-5, 0, 5, 1)));
		// Roof
		lines.add(new Line3D(new Vector3D(-5, 5, -5, 1), new Vector3D(0, 8, -5, 1)));
		lines.add(new Line3D(new Vector3D(0, 8, -5, 1), new Vector3D(5, 5, -5, 1)));
		lines.add(new Line3D(new Vector3D(-5, 5, 5, 1), new Vector3D(0, 8, 5, 1)));
		lines.add(new Line3D(new Vector3D(0, 8, 5, 1), new Vector3D(5, 5, 5, 1)));
		lines.add(new Line3D(new Vector3D(0, 8, -5, 1), new Vector3D(0, 8, 5, 1)));
		// Door
		lines.add(new Line3D(new Vector3D(1, 0, 5, 1), new Vector3D(1, 3, 5, 1)));
		lines.add(new Line3D(new Vector3D(-1, 0, 5, 1), new Vector3D(-1, 3, 5, 1)));
		lines.add(new Line3D(new Vector3D(1, 3, 5, 1), new Vector3D(-1, 3, 5, 1)));
	}

	@Override
	public Iterator<Line3D> getLines()
	{
		return lines.iterator();
	}
}
