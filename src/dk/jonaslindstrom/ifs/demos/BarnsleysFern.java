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
import org.apache.commons.math3.util.Pair;

/**
 * Demo application generating <a href="https://en.wikipedia.org/wiki/Barnsley_fern">Barnsley's
 * fern</a> using the {@link ChaosGame}.
 * 
 * @author Jonas Lindstrøm (jonas.lindstrom@alexandra.dk)
 *
 */
public class BarnsleysFern {

  public static void main(String[] arguments) throws IOException {

    
    List<Pair<Transformation, Double>> functions = new ArrayList<>();
    functions.add(new Pair<Transformation, Double>(
        new AffineTransformation(0.85, 0.04, -0.04, 0.85, 0.0, 1.60), 0.85));
    functions.add(new Pair<Transformation, Double>(
        new AffineTransformation(-0.15, 0.28, 0.26, 0.24, 0.0, 0.44), 0.07));
    functions.add(new Pair<Transformation, Double>(
        new AffineTransformation(0.20, -0.26, 0.23, 0.22, 0.0, 1.60), 0.07));
    functions
        .add(new Pair<Transformation, Double>(new LinearTransformation(0.0, 0.0, 0.0, 0.16), 0.01));

    ChaosGame ff = new ChaosGame(new StochasticFunctionSet(functions));
    int[][] histogram = ff.chaosGame(10000, 5000, new Rectangle2D.Double(-5.5, -1.0, 12.0, 12.0),
        new Dimension(1000, 1000));
    IO.generateImage(histogram, new LogDensity(), new File("bf.png"));
  }
}
