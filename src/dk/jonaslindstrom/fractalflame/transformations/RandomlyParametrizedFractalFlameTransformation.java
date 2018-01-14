package dk.jonaslindstrom.fractalflame.transformations;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.Pair;

import dk.jonaslindstrom.fractalflame.transformations.variations.VariationsFactory;

public class RandomlyParametrizedFractalFlameTransformation implements Transformation {

	private Random random;
	private Function<Vector2D, Vector2D> transformation;

	public RandomlyParametrizedFractalFlameTransformation(VariationsFactory variationsFactory) {
		this.random = new Random();
		
		double[] affineParameters = new double[6];
		for (int i = 0; i < 6; i++) {
			affineParameters[i] = random.nextDouble() - 1.0;
		}
		Transformation affineTransformation = new AffineTransformation(affineParameters);
		
		List<Transformation> variations = variationsFactory.getVariations(affineParameters);
		List<Pair<Double, Transformation>> weightedVariations = new LinkedList<Pair<Double, Transformation>>();
		for (Transformation variation : variations) {
			weightedVariations.add(new Pair<Double, Transformation>(random.nextDouble() - 1.0, variation));
		}
		
		double[] postTransformParameters = new double[6];
		for (int i = 0; i < 6; i++) {
			postTransformParameters[i] = random.nextDouble() - 1.0;
		}
		Transformation postTransform = new AffineTransformation(postTransformParameters);
		
		this.transformation = affineTransformation.andThen(new WeightedSum(weightedVariations)).andThen(postTransform);
	}
	
	@Override
	public Vector2D apply(Vector2D t) {
		return transformation.apply(t);
	}

}
