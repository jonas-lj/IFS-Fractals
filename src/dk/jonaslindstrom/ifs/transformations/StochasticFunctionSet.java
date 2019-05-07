package dk.jonaslindstrom.ifs.transformations;

import java.util.List;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

public class StochasticFunctionSet implements Transformation {

  private EnumeratedDistribution<Transformation> distribution;

  public StochasticFunctionSet(List<Pair<Transformation, Double>> propabilities, long seed) {
    this.distribution = new EnumeratedDistribution<>(propabilities);
    this.distribution.reseedRandomGenerator(seed);
  }

  public StochasticFunctionSet(List<Pair<Transformation, Double>> propabilities) {
    this.distribution = new EnumeratedDistribution<>(propabilities);
  }
  
  @Override
  public Vector2D apply(Vector2D p) {
    Transformation f = this.distribution.sample();
    return f.apply(p);
  }

}
