package dk.jonaslindstrom.ifs.transformations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

public class WeightedSum implements Transformation {

  private final List<Pair<Double, Transformation>> transformations;

  public WeightedSum(List<Pair<Double, Transformation>> transformations) {
    this.transformations = transformations;
  }

  @Override
  public Vector2D apply(Vector2D t, ComputationKernel kernel) {
    Vector2D p = new Vector2D(0.0, 0.0);
    for (Pair<Double, Transformation> transformation : transformations) {
      p = p.add(transformation.getSecond().apply(t, kernel).scalarMultiply(transformation.getFirst()));
    }
    return p;
  }

}
