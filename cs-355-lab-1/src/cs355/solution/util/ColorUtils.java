package cs355.solution.util;

import java.awt.Color;

public class ColorUtils
{
	public static Color randomColor()
	{
		return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
	}

	/**
	 * Computes and returns a color that contrasts the given color by at least the given amount
	 * 
	 * @param source
	 *            The computed color will contrast this color by at least the given amount
	 * @param minimumContrast
	 *            The minimum contrast value (1..21) the computed color will have to the given color.
	 * @return The computed color which contrasts the source color by at least the given amount.
	 * 
	 * @see <a
	 *      href="http://www.w3.org/TR/2008/REC-WCAG20-20081211/#contrast-ratiodef">http://www.w3.org/TR/2008/REC-WCAG20-20081211/#contrast-ratiodef</a>
	 */
	public static Color getRandomContrastingColor(Color source, double minimumContrast)
	{
		Color color;
		double contrast;

		do
		{
			color = randomColor();
			contrast = getContrast(source, color);
		}
		while (contrast < minimumContrast);

		return color;
	}

	/**
	 * Computes the contrast of the two colors.
	 * 
	 * @param c1
	 *            an instance of Color.
	 * @param c2
	 *            an instance of Color.
	 * @return The contrast (1..21) of the two colors
	 * 
	 * @see <a
	 *      href="http://www.w3.org/TR/2008/REC-WCAG20-20081211/#contrast-ratiodef">http://www.w3.org/TR/2008/REC-WCAG20-20081211/#contrast-ratiodef</a>
	 * 
	 */
	public static double getContrast(Color c1, Color c2)
	{
		double l1 = getLuminance(c1);
		double l2 = getLuminance(c2);
		if (l1 > l2)
			return (l1 + 0.05) / (l2 + 0.05);
		else
			return (l2 + 0.05) / (l1 + 0.05);
	}

	/**
	 * Computes the luminance of the color.
	 * 
	 * @return The luminance (0..1) of the color.
	 * 
	 * @see <a
	 *      href="http://www.w3.org/TR/2008/REC-WCAG20-20081211/#relativeluminancedef">http://www.w3.org/TR/2008/REC-WCAG20-20081211/#relativeluminancedef</a>
	 */
	public static double getLuminance(Color color)
	{
		double _r = color.getRed() / 255d;
		double _g = color.getGreen() / 255d;
		double _b = color.getBlue() / 255d;

		double r = _r <= 0.03928 ? _r / 12.92 : Math.pow(((_r + 0.055) / 1.055), 2.4);
		double g = _g <= 0.03928 ? _g / 12.92 : Math.pow(((_g + 0.055) / 1.055), 2.4);
		double b = _b <= 0.03928 ? _b / 12.92 : Math.pow(((_b + 0.055) / 1.055), 2.4);

		double luminance = 0.2126 * r + 0.7152 * g + 0.0722 * b;
		return luminance;
	}
}
