package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.image.LogDensityWithClass;
import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NFlake {

  /** Compute the n-vertices of a regular n-gon with center in (0,0) */
  private static List<Vector2D> nGonVertices(int n) {
    return IntStream.range(0, n).mapToObj(i -> new Vector2D(Math.cos(2 * Math.PI * i / n), Math.sin(2 * Math.PI * i / n)).scalarMultiply(n).add(new Vector2D(n, n))).collect(Collectors.toList());
  }

  private static double scaleFactor(int n) {
    return 2.0 *( 1.0 + IntStream.range(1, n / 4).mapToDouble(k -> 2.0 * Math.PI * k / n).sum());
  }

  public static void main(String[] arguments) throws IOException {

    // Draw a "Pentaflake", https://en.wikipedia.org/wiki/N-flake#Pentaflake

    int n = 5;

    double r = scaleFactor(n);
    List<Vector2D> vertices = nGonVertices(n);

    List<Transformation> functions = vertices.stream().map(v -> (Transformation) (Vector2D w, ComputationKernel k) ->
            v.scalarMultiply(1/r).add(w.scalarMultiply((r-1) / r))).collect(Collectors.toList());

    List<Color> colors = List.of(
            Color.decode("#204C5B"),
            Color.decode("#867088"),
            Color.decode("#00C6A8"),
            Color.decode("#F59432"),
            Color.decode("#924755")
    );

    int[][][] histogram = ChaosGame.chaosGameWithClass(functions, 100000, 5000, new Rectangle2D.Double(0.0, 0.0, 2*n, 2*n),
        new Dimension(2000, 2000));
    IO.generateImage(histogram, new LogDensityWithClass(colors), new File("pf.png"));
  }

}
