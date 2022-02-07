package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import dk.jonaslindstrom.ifs.transformations.Transformation;

public class Heart implements Transformation {

  @Override
  public Vector2D apply(Vector2D t, ComputationKernel kernel) {
    double omega = FastMath.atan(t.getX() / t.getY());
    double r = t.getNorm();
    double omegaR = omega * r;
    return new Vector2D(FastMath.sin(omegaR), -FastMath.cos(omegaR)).scalarMultiply(r);
  }

}
