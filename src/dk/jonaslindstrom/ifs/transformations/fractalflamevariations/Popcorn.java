package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import dk.jonaslindstrom.ifs.transformations.Transformation;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Popcorn implements Transformation {

  public double c, f;

  public Popcorn(double c, double f) {
    this.c = c;
    this.f = f;
  }

  @Override
  public Vector2D apply(Vector2D t) {
    return new Vector2D(t.getX() + c * FastMath.sin(FastMath.tan(3 * t.getY())),
        t.getY() + f * FastMath.sin(FastMath.tan(3 * t.getX())));
  }

}
