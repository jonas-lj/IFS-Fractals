package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.image.LogDensityWithClass;
import dk.jonaslindstrom.ifs.transformations.AffineTransformation;
import dk.jonaslindstrom.ifs.transformations.LinearTransformation;
import dk.jonaslindstrom.ifs.transformations.StochasticFunctionSet;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.util.Pair;

/**
 * Demo application generating <a href="https://en.wikipedia.org/wiki/Barnsley_fern">Barnsley's
 * fern</a> using the {@link ChaosGame}.
 */
public class BarnsleysFern {

  public static void main(String[] arguments) throws IOException {

    List<Pair<Transformation, Double>> functions = new ArrayList<>();
    functions.add(new Pair<>(new AffineTransformation(0.85, 0.04, -0.04, 0.85, 0.0, 1.60), 0.85));
    functions.add(new Pair<>(new AffineTransformation(-0.15, 0.28, 0.26, 0.24, 0.0, 0.44), 0.07));
    functions.add(new Pair<>(new AffineTransformation(0.20, -0.26, 0.23, 0.22, 0.0, 1.60), 0.07));
    functions.add(new Pair<>(new LinearTransformation(0.0, 0.0, 0.0, 0.16), 0.01));

    int[][] histogram = ChaosGame.chaosGame(new StochasticFunctionSet(functions), 100000, 10000, new Rectangle2D.Double(-2.3, -0.0, 4.9, 10.0),
        new Dimension(3508, 4967), "bf");
    IO.generateImage(histogram, new LogDensity(), new File("bf.png"));
  }
}
