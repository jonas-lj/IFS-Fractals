package dk.jonaslindstrom.ifs;

import dk.jonaslindstrom.ifs.image.ConcurrentImageBuffer;
import dk.jonaslindstrom.ifs.transformations.ClassifiedPoint;
import dk.jonaslindstrom.ifs.transformations.StochasticFunctionSet;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import dk.jonaslindstrom.ifs.transformations.TransformationWithClassification;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;
import me.tongfei.progressbar.ProgressBar;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

public class ChaosGame {

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
   */
  private static Vector2D samplePoint(Rectangle2D.Double area, Random random) {
    double x = area.getX() + random.nextDouble() * area.getWidth();
    double y = area.getY() + random.nextDouble() * area.getHeight();
    return new Vector2D(x, y);
  }

  public static int[][][] chaosGameWithClass(TransformationWithClassification transformations, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize, Random random, String name) {
    int[][][] histogram = new int[imageSize.width][imageSize.height][transformations
        .classCount()];
    return chaosGameWithClass(transformations, histogram, points, iterations, area, imageSize, random, name);
  }

  public static int[][][] chaosGameWithClass(TransformationWithClassification transformations, int[][][] histogram, int points, int iterations,
      Rectangle2D.Double area, Dimension imageSize, Random random, String name) {

    double scaleX = imageSize.getWidth() / area.getWidth();
    double scaleY = imageSize.getHeight() / area.getHeight();
    Function<Vector2D, Point> toPixel = v -> new Point((int) ((v.getX() - area.getX()) * scaleX),
        (int) ((v.getY() - area.getY()) * scaleY));

    ConcurrentImageBuffer buffer = new ConcurrentImageBuffer(histogram);

    ProgressBar pb = new ProgressBar(name, points);
    pb.start();

    /*
     * We ignore the first 20 iterations. This allows the sequence to move close enough to the fixed
     * set of the IFS before we allow the sequence to contribute to the histogram.
     */
    Stream.generate(() -> samplePoint(area, random)).peek(i -> pb.step()).parallel().limit(points)
        .map(ClassifiedPoint::new).forEach(q -> Stream
        .iterate(q, p -> area.contains(p.getX(), p.getY()),
            p -> transformations.applyWithClass(p, null))
        .skip(20).limit(iterations).forEach(p -> {
          Point px = toPixel.apply(p);
          buffer.update(px.x, px.y, p.getClassification());
        }));

    pb.stop();

    return buffer.getBuffer();
  }

  /**
   * Return a histogram of where the sequence we get by repeatedly applying the the given
   * transformations to random points.
   */
  public static int[][] chaosGame(Transformation transformation, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize, Random random, String name, int[][] histogram) {
    double scaleX = imageSize.getWidth() / area.getWidth();
    double scaleY = imageSize.getHeight() / area.getHeight();
    Function<Vector2D, Point> toPixel = v -> new Point((int) ((v.getX() - area.getX()) * scaleX),
        (int) ((v.getY() - area.getY()) * scaleY));

    ProgressBar pb = new ProgressBar(name, points);
    pb.start();

    /*
     * We ignore the first 20 iterations. This allows the sequence to move close enough to the fixed
     * set of the IFS before we allow the sequence to contribute to the histogram.
     */
    Stream.generate(() -> samplePoint(area, random)).peek(i -> pb.step()).parallel().limit(points)
        .forEach(q -> Stream
        .iterate(q, p -> area.contains(p.getX(), p.getY()),
            p -> transformation.apply(p, null))
        .skip(20).limit(iterations).forEach(p -> {
          Point px = toPixel.apply(p);
          histogram[px.x][px.y]++;
        }));

    pb.stop();

    return histogram;
  }

  public static int[][] chaosGame(Transformation transformation, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize, Random random, String name) {
    return chaosGame(transformation, points, iterations, area, imageSize, random, name,
        new int[imageSize.width][imageSize.height]);
  }

  public static int[][][] chaosGameWithClass(TransformationWithClassification transformations, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize, String name) {
    return chaosGameWithClass(transformations, points, iterations, area, imageSize, new Random(), name);
  }

  public static int[][][] chaosGameWithClass(List<Transformation> transformations, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize) {
    return chaosGameWithClass(buildUniformlyDistributedStochasticFunctionSet(transformations), points, iterations, area, imageSize, "N/A");
  }

  public static int[][] chaosGame(List<Transformation> transformations, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize, String name) {
    return chaosGame(buildUniformlyDistributedStochasticFunctionSet(transformations), points, iterations, area, imageSize, name);
  }

  public static int[][] chaosGame(List<Transformation> transformations, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize) {
    return chaosGame(buildUniformlyDistributedStochasticFunctionSet(transformations), points, iterations, area, imageSize, "N/A");
  }

  public static int[][][] chaosGameWithClass(List<Transformation> transformations, int[][][] histogram, int points, int iterations,
      Rectangle2D.Double area, Dimension imageSize, Random random, String name) {
    return chaosGameWithClass(buildUniformlyDistributedStochasticFunctionSet(transformations), histogram, points, iterations, area, imageSize, random, name);
  }

    public static int[][][] chaosGameWithClass(List<Transformation> transformations, int points, int iterations,
      Rectangle2D.Double area, Dimension imageSize, String name) {
    return chaosGameWithClass(buildUniformlyDistributedStochasticFunctionSet(transformations), points, iterations, area, imageSize, new Random(), name);
  }

  public static  int[][] chaosGame(Transformation transformation, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize) {
    return chaosGame(transformation, points, iterations, area, imageSize, new Random());
  }

  public static int[][] chaosGame(Transformation transformation, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize, Random random) {
    return chaosGame(transformation, points, iterations, area, imageSize, random, "N/A");
  }

  public static int[][] chaosGame(Transformation transformation, int points, int iterations, Rectangle2D.Double area,
      Dimension imageSize, String name) {
    return chaosGame(transformation, points, iterations, area, imageSize, new Random(), name);
  }

}