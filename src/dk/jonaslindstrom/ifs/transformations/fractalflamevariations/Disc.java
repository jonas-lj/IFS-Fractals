package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import dk.jonaslindstrom.ifs.transformations.Transformation;

public class Disc implements Transformation {

  @Override
  public Vector2D apply(Vector2D t, ComputationKernel kernel) {
    double omega = FastMath.atan(t.getX() / t.getY());
    double r = t.getNorm() * Math.PI;
    return new Vector2D(FastMath.sin(r), FastMath.cos(r))
        .scalarMultiply(omega / Math.PI);
  }

}
