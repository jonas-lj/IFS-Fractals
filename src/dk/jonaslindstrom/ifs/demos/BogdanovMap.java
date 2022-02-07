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

/**
 * Demo application generating the attractor for <a href="https://en.wikipedia.org/wiki/Bogdanov_map">Bogdanov map</a>.
 */
public class BogdanovMap {

  public static void main(String[] arguments) throws IOException {
    double ε = 0.0;
    double k = 1.2;
    double μ = 0.0;

    Transformation function =
        (v, kernel) -> {
          double y =  v.getY() + ε * v.getY() + k * v.getX() * (v.getX() - 1.0) + μ * v.getX() * v.getY();
          double x = v.getX() + y;
          return new Vector2D(x, y);
        };

    int[][] histogram = ChaosGame.chaosGame(function, 100000, 10000, new Rectangle2D.Double(-1.0, -1.0, 2.0, 2.0),
        new Dimension(1000, 1000));
    IO.generateImage(histogram, new LogDensity(), new File("bm.png"));
  }
}
