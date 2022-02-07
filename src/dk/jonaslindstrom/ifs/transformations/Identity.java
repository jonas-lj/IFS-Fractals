package dk.jonaslindstrom.ifs.transformations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Identity implements Transformation {

  @Override
  public Vector2D apply(Vector2D p, ComputationKernel kernel) {
    return new Vector2D(p.getX(), p.getY());
  }

}
