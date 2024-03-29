package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import dk.jonaslindstrom.ifs.transformations.Transformation;

public class Spherical implements Transformation {

  @Override
  public Vector2D apply(Vector2D p, ComputationKernel kernel) {
    return p.scalarMultiply(1.0 / p.getNormSq());
  }

}
