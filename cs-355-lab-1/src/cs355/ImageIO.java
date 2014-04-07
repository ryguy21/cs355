/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package cs355;

import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author talonos
 */
public class ImageIO
{
	static JFileChooser	fileChooser	= new JFileChooser(".");

	public static BufferedImage openImage()
	{
		try
		{
			File file = selectFile(false);

			if (file != null)
			{
				BufferedImage img = javax.imageio.ImageIO.read(file);

				if (img == null) throw new Exception("unable to read image");

				return img;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static void saveImage(BufferedImage img)
	{
		try
		{
			File file = selectFile(true);

			if (file != null)
			{
				int dot = file.getName().lastIndexOf('.');
				String suffix = file.getName().substring(dot + 1);
				ImageWriter writer = javax.imageio.ImageIO.getImageWritersBySuffix(suffix).next();
				ImageOutputStream out = javax.imageio.ImageIO.createImageOutputStream(file);
				writer.setOutput(out);
				writer.write(img);
				out.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static String	startPath;

	/**
	 * Displays a file chooser dialog, based on the operating system being used, for aesthetic purposes.
	 * 
	 * @param save
	 *            Whether to show a save dialog or an open dialog. Passing a value of <tt>true</tt> opens a save dialog.
	 * @return A file the user selected.
	 */
	public static File selectFile(boolean save)
	{
		if (System.getProperty("os.name").toLowerCase().contains("windows"))
		{
			JFileChooser chooser = new JFileChooser();
			if (startPath != null) chooser.setCurrentDirectory(new File(startPath));
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.ged", "ged"));
			if (save)
				chooser.showSaveDialog(CS355Frame.inst());
			else
				chooser.showOpenDialog(CS355Frame.inst());

			try
			{
				File file = chooser.getSelectedFile();
				startPath = file.getParent();
				return file;
			}
			catch (NullPointerException e)
			{
				return null;
			}
		}
		else
		{
			FileDialog dialog;
			if (save)
				dialog = new FileDialog(CS355Frame.inst(), "Save", FileDialog.SAVE);
			else
				dialog = new FileDialog(CS355Frame.inst(), "Open", FileDialog.LOAD);

			if (startPath == null) startPath = System.getProperty("user.dir");

			dialog.setDirectory(startPath);
			dialog.setVisible(true);

			startPath = dialog.getDirectory();
			String filename = startPath + File.separator + dialog.getFile();

			if (filename.endsWith("null"))
				return null;
			else
				return new File(filename);
		}
	}
}
