package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import dk.jonaslindstrom.ifs.transformations.Transformation;

public class Swirl implements Transformation {

  @Override
  public Vector2D apply(Vector2D t, ComputationKernel kernel) {
    double rSquared = t.getNormSq();
    double cos = FastMath.cos(rSquared);
    double sin = FastMath.sin(rSquared);
    return new Vector2D(t.getX() * sin - t.getY() * cos,
        t.getX() * sin + t.getY() * cos);
  }

}
