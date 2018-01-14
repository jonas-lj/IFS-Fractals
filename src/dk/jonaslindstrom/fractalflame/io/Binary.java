package dk.jonaslindstrom.fractalflame.io;

import java.awt.Color;

public class Binary implements ColorMap {

	@Override
	public Color[][] apply(int[][] pixels) {
		Color[][] colors = new Color[pixels.length][pixels[0].length];

		for (int x = 0; x < pixels.length; x++) {
			for (int y = 0; y < pixels[x].length; y++) {
				if (pixels[x][y] > 0) {
					colors[x][y] = Color.white;
				} else {
					colors[x][y] = Color.black;
				}
			}
		}
		return colors;
	}

}
