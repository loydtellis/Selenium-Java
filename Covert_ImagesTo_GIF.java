package javaPrograms;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import com.simiacryptus.util.io.GifSequenceWriter;


//prerequisite: Make sure all the images in the folder to be of same resolution. If it isn't that image won't be included in the GIF.

public class Covert_ImagesTo_GIF {
	
	static final File dir = new File("F://SoftInstall//Selenium eclipse//Selenium SS");
	static final String[] EXTENSIONS = new String[] { "png" // (, "bmp", "jpg") use the code in brackets to add more extension
	};

	// code to verify if the extension in the folder is of format .png or not!
	static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
		@Override
		public boolean accept(final File dir, final String name) {
			for (final String ext : EXTENSIONS) {
				if (name.endsWith("." + ext)) {
					return (true);
				}
			}
			return (false);
		}
	};

	// To get all the images in the folder and convert into GIF
	public static void main(String[] args) throws Exception {
		int count = dir.listFiles(IMAGE_FILTER).length;
		BufferedImage first = ImageIO.read(new File("F://SoftInstall//Selenium eclipse//Selenium SS//Screenshot1.png"));
		ImageOutputStream output = new FileImageOutputStream( new File("F://SoftInstall//Selenium eclipse//Selenium SS//giveAnyName.gif"));
		GifSequenceWriter writer = new GifSequenceWriter(output,
				first.getType(), 400, true);
		writer.writeToSequence(first);
		BufferedImage next = null;
		if (dir.isDirectory()) {
			for (int i = 2; i <= count; i++) {
				try {
					next = ImageIO.read(new File("F://SoftInstall//Selenium eclipse//Selenium SS//Screenshot"+ i + ".png"));
					writer.writeToSequence(next);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			writer.close();
			output.close();
		}
		/*---------------------------------------------------------*/
		// below code is to delete the images
		// use the below snippet in the beginning of selenium code, i.e., before the code starts taking screenshots
		// this is to avoid any extra screenshots that was taken by another selenium code, that may get included
		// during GIF creation. So its better to avoid disaster before it occurs!

		for (int i = 1; i <= count; i++) {
			try {
				Files.deleteIfExists(Paths.get("F://SoftInstall//Selenium eclipse//Selenium SS//Screenshot"+ i + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*---------------------------------------------------------*/

	}
}
