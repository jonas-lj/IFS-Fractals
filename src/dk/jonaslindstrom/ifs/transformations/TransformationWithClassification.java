package dk.jonaslindstrom.ifs.transformations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public interface TransformationWithClassification extends Transformation {

  ClassifiedPoint applyWithClass(ClassifiedPoint classifiedPoint, ComputationKernel kernel);

  @Override
  default Vector2D apply(Vector2D point, ComputationKernel kernel) {
    return apply(new ClassifiedPoint(point, 0), kernel);
  }

  int classCount();
}
