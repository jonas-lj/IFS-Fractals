package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import dk.jonaslindstrom.ifs.transformations.Transformation;

public class Horseshoe implements Transformation {

  @Override
  public Vector2D apply(Vector2D p, ComputationKernel kernel) {
    double r = p.getNorm();
    return new Vector2D((p.getX() - p.getY()) / (p.getX() + p.getY()) / r,
        2.0 * p.getX() * p.getY() / r);
  }

}
