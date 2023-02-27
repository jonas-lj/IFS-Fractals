package dk.jonaslindstrom.ifs.transformations;

import dk.jonaslindstrom.ifs.kernels.ComputationKernel;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

public class StochasticFunctionSet implements TransformationWithClassification {

  private final List<Transformation> transformations;
  private final EnumeratedIntegerDistribution distribution;

  public StochasticFunctionSet(List<Transformation> transformations,
      double[] probabilities,
      long seed) {
    this.transformations = transformations;
    this.distribution = new EnumeratedIntegerDistribution(IntStream.range(0, transformations.size()).toArray(), probabilities);
    this.distribution.reseedRandomGenerator(seed);
  }

  public StochasticFunctionSet(List<Pair<Transformation, Double>> probabilities) {
    this.transformations = probabilities.stream().map(Pair::getFirst).collect(Collectors.toList());
    this.distribution = new EnumeratedIntegerDistribution(
        IntStream.range(0, transformations.size()).toArray(),
        probabilities.stream().mapToDouble(Pair::getSecond).toArray());
  }

  @Override
  public ClassifiedPoint applyWithClass(ClassifiedPoint classifiedPoint, ComputationKernel kernel) {
    int i = distribution.sample();
    return new ClassifiedPoint(transformations.get(i).apply(classifiedPoint, kernel), i);
  }

  @Override
  public Vector2D apply(Vector2D p, ComputationKernel kernel) {
    Transformation f = transformations.get(this.distribution.sample());
    return f.apply(p, kernel);
  }

  @Override
  public int classCount() {
    return transformations.size();
  }


}
