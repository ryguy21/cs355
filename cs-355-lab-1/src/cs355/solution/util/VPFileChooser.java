package cs355.solution.util;

import java.awt.FileDialog;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class VPFileChooser
{
	private static VPFileChooser	instance;

	public static VPFileChooser getInstance()
	{
		return instance;
	}

	private final JFrame	frame;

	public VPFileChooser(JFrame frame)
	{
		this.frame = frame;
		VPFileChooser.instance = this;
	}

	/**
	 * Displays a file chooser dialog, based on the operating system being used, for aesthetic purposes.
	 * 
	 * @return A string representation of a path to a file in the filesystem.
	 */
	public String selectFileToOpen()
	{
		String filename = System.getProperty("user.dir");
		return selectFileToOpen(filename);
	}

	/**
	 * Displays a file chooser dialog, based on the operating system being used, for aesthetic purposes.
	 * 
	 * @param startPath
	 *            The path to the folder or file the dialog will show when first opened.
	 * @return A string representation of a path to a file in the filesystem.
	 */
	public String selectFileToOpen(String startPath)
	{
		if (System.getProperty("os.name").toLowerCase().contains("windows"))
		{
			JFileChooser chooser = new JFileChooser();
			if (startPath != null)
				chooser.setCurrentDirectory(new File(startPath));
			chooser.showOpenDialog(frame);

			try
			{
				return chooser.getSelectedFile().getPath();
			}
			catch (NullPointerException e)
			{
				return null;
			}
		}
		else
		{
			FileDialog dialog = new FileDialog(frame, "Open", FileDialog.LOAD);
			if (startPath == null)
				startPath = System.getProperty("user.dir");

			dialog.setDirectory(startPath);
			dialog.setVisible(true);

			String filename = dialog.getDirectory() + System.getProperty("file.separator") + dialog.getFile();
			if (filename.endsWith("null"))
				return null;
			else
				return filename;
		}
	}

	/**
	 * Displays a file chooser dialog, based on the operating system being used, for aesthetic purposes.
	 * 
	 * @return A string representation of a path to a file in the filesystem.
	 */
	public String selectFileToSave()
	{
		String filename = System.getProperty("user.dir");
		return selectFileToSave(filename);
	}

	/**
	 * Displays a file chooser dialog, based on the operating system being used, for aesthetic purposes.
	 * 
	 * @param startPath
	 *            The path to the folder or file the dialog will show when first opened.
	 * @return A string representation of a path to a file in the filesystem.
	 */
	public String selectFileToSave(String startPath)
	{
		if (System.getProperty("os.name").toLowerCase().contains("windows"))
		{
			JFileChooser chooser = new JFileChooser();
			if (startPath != null)
				chooser.setCurrentDirectory(new File(startPath));
			chooser.showSaveDialog(frame);

			try
			{
				return chooser.getSelectedFile().getPath();
			}
			catch (NullPointerException e)
			{
				return null;
			}
		}
		else
		{
			FileDialog dialog = new FileDialog(frame, "Save", FileDialog.SAVE);
			if (startPath == null)
				startPath = System.getProperty("user.dir");

			dialog.setDirectory(startPath);
			dialog.setVisible(true);

			String filename = dialog.getDirectory() + System.getProperty("file.separator") + dialog.getFile();
			if (filename.endsWith("null"))
				return null;
			else
				return filename;
		}
	}
}