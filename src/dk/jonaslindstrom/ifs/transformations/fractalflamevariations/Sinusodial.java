package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import dk.jonaslindstrom.ifs.transformations.Transformation;

public class Sinusodial implements Transformation {

  @Override
  public Vector2D apply(Vector2D p, ComputationKernel kernel) {
    return new Vector2D(FastMath.sin(p.getX()), FastMath.sin(p.getY()));
  }

}
