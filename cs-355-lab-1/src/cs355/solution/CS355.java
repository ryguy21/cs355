/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package cs355.solution;

import cs355.CS355Controller;
import cs355.GUIFunctions;
import cs355.ViewRefresher;

/**
 * @author Ryan Cheatham
 */
public class CS355
{

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args)
	{
		CS355Controller controller = new Controller();
		ViewRefresher refresher = new Refresher();
		EventHandler listener = new EventHandler();

		GUIFunctions.createCS355Frame(controller, refresher, listener, listener);
	}
}