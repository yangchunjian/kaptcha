package com.google.code.kaptcha.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Helper methods for tests.
 * 
 * @author cliffano
 */
public class TestUtil
{
    protected static final String OUTPUT_DIRECTORY = ".output";

	/**
	 * Writes PNG image file at project's output folder.
	 *
	 * @param filename
	 *            The file name without extension.
	 * @param image
	 *            The image to write to file.
	 * @throws IOException
	 *             when there's a problem with creating the file.
	 */
	public static void writePngImageFile(String filename, BufferedImage image)
			throws IOException
	{
        File folder = new File(OUTPUT_DIRECTORY);
        if (!folder.exists()) {
            folder.mkdir();
        }
		File file = new File(OUTPUT_DIRECTORY + File.separator + filename + ".png");
		ImageIO.write(image, "png", file);
	}

	/**
	 * @return a base image of 300 x 300 dimension
	 */
	public static BufferedImage createBaseImage()
	{
		return new BufferedImage(300, 80, BufferedImage.TYPE_INT_ARGB);
	}
}
