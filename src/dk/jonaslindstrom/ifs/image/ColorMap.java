package dk.jonaslindstrom.ifs.image;

import java.awt.Color;
import java.util.function.Function;

public interface ColorMap extends Function<int[][], Color[][]> {

  public Color getBackgroundColor();
  
}
