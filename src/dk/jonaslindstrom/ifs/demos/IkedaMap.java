package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

/**
 * Demo application generating
 * <a href="https://en.wikipedia.org/wiki/Ikeda_map">Ikeda map</a>.
 */
public class IkedaMap {

  public static void main(String[] arguments) throws IOException {

    double u = 0.918;

    Transformation function =
        (v, k) -> {
          double t = 0.4 - 6.0 / (1.0 + v.getX() * v.getX() + v.getY() * v.getY());
          double x = 1 + u * (v.getX() * FastMath.cos(t) - v.getY() * FastMath.sin(t));
          double y = u * (v.getX() * FastMath.sin(t) + v.getY() * FastMath.cos(t));
          return new Vector2D(x, y);
        };

    int[][] histogram = ChaosGame
        .chaosGame(function, 100000, 10000, new Rectangle2D.Double(-10.0, -10.0, 20.0, 20.0),
            new Dimension(1000, 1000));
    IO.generateImage(histogram, new LogDensity(), new File("im.png"));
  }
}
