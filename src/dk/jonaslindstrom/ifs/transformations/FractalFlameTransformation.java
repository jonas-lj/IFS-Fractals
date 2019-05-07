package dk.jonaslindstrom.ifs.transformations;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

public class FractalFlameTransformation implements Transformation {

  private Function<Vector2D, Vector2D> transformation;

  public FractalFlameTransformation(double[] affineParameters, double[] postTransformParameters,
      List<Transformation> variations, double... weights) {
    Transformation affineTransformation = new AffineTransformation(affineParameters);

    List<Pair<Double, Transformation>> weightedVariations = new LinkedList<>();
    for (int i = 0; i < variations.size(); i++) {
      weightedVariations.add(new Pair<>(weights[i], variations.get(i)));
    }

    Transformation postTransform = new AffineTransformation(postTransformParameters);

    this.transformation =
        affineTransformation.andThen(new WeightedSum(weightedVariations)).andThen(postTransform);
  }

  public FractalFlameTransformation(List<Transformation> variations, Random random) {
    this(randomDoubles(random, 6, -0.5, 0.5), randomDoubles(random, 6, -0.5, 0.5), variations,
        randomDoubles(random, variations.size(), 0.0, 1.0));
  }

  private static double[] randomDoubles(Random random, int n, double low, double high) {
    double[] list = new double[n];
    for (int i = 0; i < n; i++) {
      list[i] = random.nextDouble() * (high - low) + low;
    }
    return list;
  }

  @Override
  public Vector2D apply(Vector2D t) {
    return transformation.apply(t);
  }

}
