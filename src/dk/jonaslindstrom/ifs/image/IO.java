package dk.jonaslindstrom.ifs.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IO {

	public static void generateImage(int[][] image, ColorMap colormap, File file) throws IOException {
		
		int width = image.length;
		int height = image[0].length;
				
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = bi.createGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, width, height);
		
		Color[][] colors = colormap.apply(image);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				bi.setRGB(x, y, colors[x][y].getRGB());
			}
		}
		
		ImageIO.write(bi, "PNG", file);
		System.out.println("Done!");
	}
	
}
