package dk.jonaslindstrom.ifs.curves;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

public class LissajousCurve implements ParameterizedCurve {

  private double a, b, δ;

  public LissajousCurve(double a, double b, double δ) {
    this.a = a;
    this.b = b;
    this.δ = δ;
  }
  
  @Override
  public Vector2D apply(double t) {
    return new Vector2D(FastMath.sin(2.0 * FastMath.PI * a * t + δ), FastMath.cos(2.0 * FastMath.PI * b * t));
  }

}
