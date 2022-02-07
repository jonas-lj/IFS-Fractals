package dk.jonaslindstrom.ifs.image;

import java.awt.Color;
import java.util.function.Function;

public interface ColorMapWithClass extends Function<int[][][], Color[][]> {

  public Color getBackgroundColor();

}
