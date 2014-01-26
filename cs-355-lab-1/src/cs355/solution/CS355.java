/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package cs355.solution;

import cs355.CS355Controller;
import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.solution.controller.Controller;
import cs355.solution.model.IModelManager;
import cs355.solution.model.Model;
import cs355.solution.view.EventHandler;
import cs355.solution.view.Refresher;

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
		IModelManager model = new Model();

		CS355Controller controller = new Controller(model);
		ViewRefresher refresher = new Refresher(model);
		EventHandler listener = new EventHandler(model);

		GUIFunctions.createCS355Frame(controller, refresher, listener, listener);
	}
}