package dk.jonaslindstrom.ifs.curves;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class Circle implements ParameterizedCurve {

  private Vector2D c;
  private double r;

  public Circle(Vector2D c, double r) {
    this.c = c;
    this.r = r;
  }

  @Override
  public Vector2D apply(double t) {
    return new Vector2D(r * FastMath.cos(2.0 * Math.PI * t), r * FastMath.sin(2.0 * Math.PI * t)).add(c);
  }
  
}
