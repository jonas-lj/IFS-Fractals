package dk.jonaslindstrom.ifs.transformations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class AffineTransformation implements Transformation {

  private Transformation linear;
  private Transformation translation;

  public AffineTransformation(double[] parameters) {
    this(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5]);
  }

  public AffineTransformation(double a, double b, double c, double d, double e, double f) {
    this.linear = new LinearTransformation(a, b, c, d);
    this.translation = new Translation(e, f);
  }

  @Override
  public Vector2D apply(Vector2D v, ComputationKernel kernel) {
    return translation.apply(linear.apply(v, kernel), kernel);
  }

}
