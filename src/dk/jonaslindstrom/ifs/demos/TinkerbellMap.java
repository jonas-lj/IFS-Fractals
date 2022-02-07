package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.transformations.AffineTransformation;
import dk.jonaslindstrom.ifs.transformations.LinearTransformation;
import dk.jonaslindstrom.ifs.transformations.StochasticFunctionSet;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

/**
 * Demo application generating <a href="https://en.wikipedia.org/wiki/Tinkerbell_map">Tinkerbell map</a> using the {@link ChaosGame}.
 * 
 * @author Jonas Lindstrøm (jonas.lindstrom@alexandra.dk)
 */
public class TinkerbellMap {

  public static void main(String[] arguments) throws IOException {

    double a = 0.9;
    double b = -0.6013;
    double c = 2.0;
    double d = 0.5;
    
    Transformation function =
        (v, k) -> {
          double x = v.getX();
          double y = v.getY();
          return new Vector2D(x * x - y * y + a * x + b * y,
              2 * x * y + c * x + d * y);
        };

    int[][] histogram = ChaosGame.chaosGame(function, 100000, 10000, new Rectangle2D.Double(-2.0, -2.0, 3.0, 3.0),
        new Dimension(1000, 1000));
    IO.generateImage(histogram, new LogDensity(), new File("tm.png"));
  }
}
