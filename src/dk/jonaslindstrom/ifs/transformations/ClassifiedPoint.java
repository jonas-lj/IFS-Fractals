package dk.jonaslindstrom.ifs.transformations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class ClassifiedPoint extends Vector2D {

  private final int classification;

  public ClassifiedPoint(double x, double y, int classification) {
    super(x, y);
    this.classification = classification;
  }

  public ClassifiedPoint(Vector2D point, int classification) {
    super(point.getX(), point.getY());
    this.classification = classification;
  }

  public ClassifiedPoint(Vector2D point) {
    this(point, 0);
  }


  public int getClassification() {
    return classification;
  }

}
