package dk.jonaslindstrom.ifs.image;

import java.awt.Color;
import org.apache.commons.math3.util.FastMath;

public class LogDensity implements ColorMap {

  private boolean invert;

  public LogDensity(boolean invert) {
    this.invert = true;
  }
  
  public LogDensity() {
    this(false);
  }
  
  @Override
  public Color[][] apply(int[][] pixels) {
    int max = pixels[0][0];
    for (int x = 0; x < pixels.length; x++) {
      for (int y = 0; y < pixels[x].length; y++) {
        max = FastMath.max(max, pixels[x][y]);
      }
    }

    Color[][] colors = new Color[pixels.length][];
    for (int x = 0; x < pixels.length; x++) {
      colors[x] = new Color[pixels[x].length];
      for (int y = 0; y < pixels[x].length; y++) {
        int brightness = 0;
        if (pixels[x][y] > 0) {
          brightness = (int) (255.0 * FastMath.log(pixels[x][y]) / FastMath.log(max));
        }
        if (invert) {
          brightness = 255 - brightness;
        }
        colors[x][y] = new Color(brightness, brightness, brightness);
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
