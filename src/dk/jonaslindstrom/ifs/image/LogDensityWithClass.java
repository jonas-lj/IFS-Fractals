package dk.jonaslindstrom.ifs.image;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.util.FastMath;

public class LogDensityWithClass implements ColorMapWithClass {

  private final List<Color> bases;
  private boolean invert;

  public LogDensityWithClass(List<Color> bases) {
    this.bases = bases;
  }

  private static int sum(int[] array) {
    return Arrays.stream(array).sum();
  }

  @Override
  public Color[][] apply(int[][][] pixels) {
    int classes = pixels[0][0].length;
    int[] max = new int[classes];
    for (int x = 0; x < pixels.length; x++) {
      for (int y = 0; y < pixels[x].length; y++) {
        for (int c = 0; c < classes; c++) {
          max[c] = FastMath.max(max[c], pixels[x][y][c]);
        }
      }
    }

    Color[][] colors = new Color[pixels.length][];
    for (int x = 0; x < pixels.length; x++) {
      colors[x] = new Color[pixels[x].length];
      for (int y = 0; y < pixels[x].length; y++) {
        double r = 0, g = 0, b = 0;
        for (int c = 0; c < classes; c++) {
          if (pixels[x][y][c] > 0) {
            double ratio = FastMath.log(pixels[x][y][c]) / FastMath.log(max[c]);
            r += ratio * ((255 - bases.get(c).getRed()) / 255.0);
            g += ratio * ((255 - bases.get(c).getGreen()) / 255.0);
            b += ratio * ((255 - bases.get(c).getBlue()) / 255.0);
          }
        }

        r = Math.max(0.0, Math.min(1.0, r));
        g = Math.max(0.0, Math.min(1.0, g));
        b = Math.max(0.0, Math.min(1.0, b));

        int red = (int) (255 - 255 * r);
        int green = (int) (255 - 255 * g);
        int blue = (int) (255 - 255 * b);
        colors[x][y] = new Color(red, green, blue);
      }
    }
    return colors;
  }

  @Override
  public Color getBackgroundColor() {
    if (invert) {
      return Color.WHITE;
    } else {
      return Color.BLACK;
    }
  }

}
