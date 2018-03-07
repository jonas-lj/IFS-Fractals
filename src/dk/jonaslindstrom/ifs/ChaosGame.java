package dk.jonaslindstrom.ifs;

import dk.jonaslindstrom.ifs.transformations.StochasticFunctionSet;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

public class ChaosGame {

  private Random random;
  private StochasticFunctionSet transformations;
  private Function<Vector2D, Point> toPixel;

  public ChaosGame(StochasticFunctionSet transformations) {
    this.random = new Random();
    this.transformations = transformations;
  }

  /**
   * Create a new chaos game on the specified transformations. Random points in the plotArea are
   * iterated through the provided functions at random.
   * 
   * @param transformations
   * @param points
   * @param iterations
   * @param plotArea
   * @param imageSize
   */
  public ChaosGame(List<Transformation> transformations) {
    this(buildUniformlyDistributedStochasticFunctionSet(transformations));
  }

  private static StochasticFunctionSet buildUniformlyDistributedStochasticFunctionSet(
      List<Transformation> transformations) {
    List<Pair<Transformation, Double>> uniformlyDistributedTransformation = new ArrayList<>();
    for (Transformation transformation : transformations) {
      uniformlyDistributedTransformation.add(new Pair<>(transformation, 1.0));
    }
    return new StochasticFunctionSet(uniformlyDistributedTransformation);
  }

  /**
   * Sample a random point in the plot area.
   * 
   * @return
   */
  private Vector2D samplePoint(Rectangle2D.Double area) {
    double x = area.getX() + random.nextDouble() * area.getWidth();
    double y = area.getY() + random.nextDouble() * area.getHeight();
    return new Vector2D(x, y);
  }

  /**
   * Return a histogram of where the sequence we get by repeatedly applying the the given
   * transformations to random points.
   * 
   * @param listener A listener which is notified (with a double in the interval [0,1)) with the
   *        progress of this chaos game.
   * @return
   */
  public int[][] chaosGame(int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize) {
    int[][] histogram = new int[imageSize.width][imageSize.height];
    double scaleX = imageSize.getWidth() / area.getWidth();
    double scaleY = imageSize.getHeight() / area.getHeight();
    this.toPixel = v -> new Point((int) ((v.getX() - area.getX()) * scaleX),
        (int) ((v.getY() - area.getY()) * scaleY));

    /*
     * We ignore the first 20 iterations. This allows the sequence to move close enough to the fixed
     * set of the IFS before we allow the sequence to contribute to the histrogram.
     */
    Stream.generate(() -> samplePoint(area)).limit(points).parallel()
        .forEach(q -> Stream
            .iterate(q, p -> area.contains(p.getX(), p.getY()), p -> transformations.apply(p))
            .skip(20).limit(iterations).forEach(p -> {
              Point px = toPixel.apply(p);
              histogram[px.x][px.y]++;
            }));

    return histogram;
  }

}
