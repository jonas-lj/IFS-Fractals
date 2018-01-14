package dk.jonaslindstrom.fractalflame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

import dk.jonaslindstrom.fractalflame.transformations.StochasticFunctionSet;
import dk.jonaslindstrom.fractalflame.transformations.Transformation;

public class ChaosGame {

	private Random random;
	private Rectangle2D.Double area;
	private Dimension imageSize;
	private StochasticFunctionSet transformations;
	private int points, iterations;
	private Function<Vector2D, Point> toPixel;

	public ChaosGame(StochasticFunctionSet transformations, int points, int iterations,
			Rectangle2D.Double plotArea, Dimension imageSize) {
		this.random = new Random();
		
		this.transformations = transformations;
		this.points = points;
		this.iterations = iterations;
		this.area = plotArea;
		this.imageSize = imageSize;
		
		double scaleX = imageSize.getWidth() / plotArea.getWidth();
		double scaleY = imageSize.getHeight() / plotArea.getHeight();
		this.toPixel = v -> new Point(
				(int) ((v.getX() - plotArea.getX()) * scaleX),
				(int) ((v.getY() - plotArea.getY()) * scaleY));
	}

	public ChaosGame(List<Transformation> transformations, int points, int iterations,
			Rectangle2D.Double plotArea, Dimension imageSize) {
		this(buildUniformlyDistributedStochasticFunctionSet(transformations), points, iterations, plotArea, imageSize);
	}
	
	private static StochasticFunctionSet buildUniformlyDistributedStochasticFunctionSet(List<Transformation> transformations) {
		List<Pair<Transformation, Double>> uniformlyDistributedTransformation = new ArrayList<Pair<Transformation, Double>>();
		for (Transformation transformation : transformations) {
			uniformlyDistributedTransformation.add(new Pair<Transformation, Double>(transformation, 1.0));
		}
		return new StochasticFunctionSet(uniformlyDistributedTransformation);
	}
	
	private Vector2D samplePoint() {
		double x = area.getX() + random.nextDouble() * area.getWidth();
		double y = area.getY() + random.nextDouble() * area.getHeight();
		return new Vector2D(x, y);
	}
	
	public int[][] chaosGame() {
		int[][] histogram = new int[imageSize.width][imageSize.height];
		
		for (int j = 0; j < points; j++) {
			Vector2D p = samplePoint();
			
			for (int i = 0; i < 20; i++) {
				p = transformations.apply(p);
			}
			
			for (int i = 0; i < iterations; i++) {
				p = transformations.apply(p);
				if (area.contains(p.getX(), p.getY())) {
					Point px = toPixel.apply(p);
					histogram[px.x][px.y]++;
				} else {
					break;
				}
				
			}
		}
		return histogram;
	}
	
}
