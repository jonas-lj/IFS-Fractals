package dk.jonaslindstrom.fractalflame.transformations.variations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import dk.jonaslindstrom.fractalflame.transformations.Transformation;

public class Swirl implements Transformation {

	@Override
	public Vector2D apply(Vector2D t) {
		double rSquared = t.getNormSq();
		return new Vector2D(t.getX() * FastMath.sin(rSquared) - t.getY() * FastMath.cos(rSquared),
				t.getX() * FastMath.sin(rSquared) + t.getY() * FastMath.cos(rSquared));
	}

}
