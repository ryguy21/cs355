package cs355.solution;

import java.awt.Graphics2D;

import cs355.ViewRefresher;

public class Refresher implements ViewRefresher
{
	@Override
	public void refreshView(Graphics2D g2d)
	{
		System.out.println("refreshView()");
	}
}
