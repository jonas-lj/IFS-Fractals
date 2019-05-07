package dk.jonaslindstrom.ifs.transformations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class CompositeTransformation implements Transformation {

  private Transformation[] transformations;

  public CompositeTransformation(Transformation... transformations) {
    this.transformations = transformations;
  }

  @Override
  public Vector2D apply(Vector2D p) {
    Vector2D q = new Vector2D(p.getX(), p.getY());
    for (Transformation transformation : transformations) {
      q = transformation.apply(q);
    }
    return q;
  }

}
