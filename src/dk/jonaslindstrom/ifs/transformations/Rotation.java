package dk.jonaslindstrom.ifs.transformations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Rotation implements Transformation {

  private final double sin;
  private final double cos;

  public Rotation(double omega) {
    this.sin = FastMath.sin(omega);
    this.cos = FastMath.sin(omega);
  }

  @Override
  public Vector2D apply(Vector2D v, ComputationKernel kernel) {
    return new Vector2D(cos * v.getX() - sin * v.getY(), sin * v.getX() + cos * v.getY());
  }
}
