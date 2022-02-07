package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class LevyCCurve {

  private static Complex c(Vector2D v) {
    return new Complex(v.getX(), v.getY());
  }

  private static Vector2D v(Complex c) {
    return new Vector2D(c.getReal(), c.getImaginary());
  }

  public static void main(String[] arguments) throws IOException {

    Complex a = new Complex(0.5, 0.5);

    List<Transformation> functions = new ArrayList<>();
    functions.add((v,k) -> v(c(v).multiply(a)));
    functions.add((v,k) -> v(a.negate().add(Complex.ONE).multiply(c(v)).add(a)));

    int[][] histogram = ChaosGame.chaosGame(functions, 25000, 2500, new Rectangle2D.Double(-0.6, -0.4, 2.2, 1.5),
        new Dimension(1100, 750));
    IO.generateImage(histogram, new LogDensity(), new File("lcc.png"));
  }

}
