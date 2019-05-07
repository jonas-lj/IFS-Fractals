package dk.jonaslindstrom.ifs.transformations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Translation implements Transformation {

  private Vector2D delta;

  public Translation(double x, double y) {
    this.delta = new Vector2D(x, y);
  }

  public Translation(Vector2D delta) {
    this.delta = delta;
  }

  @Override
  public Vector2D apply(Vector2D v) {
    return v.add(delta);
  }

}
