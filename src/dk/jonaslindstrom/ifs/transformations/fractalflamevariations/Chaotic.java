package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Chaotic implements Transformation {

  private RealDistribution distribution;

  public Chaotic() {
    this(new NormalDistribution(0.0, 0.1));
  }

  public Chaotic(RealDistribution distribution) {
    this.distribution = distribution;
  }

  @Override
  public Vector2D apply(Vector2D t, ComputationKernel kernel) {
    return new Vector2D(t.getX() + distribution.sample(),
        t.getY() + distribution.sample());
  }

}
