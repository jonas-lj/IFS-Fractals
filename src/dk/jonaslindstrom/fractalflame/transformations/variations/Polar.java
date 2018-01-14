package dk.jonaslindstrom.fractalflame.transformations.variations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import dk.jonaslindstrom.fractalflame.transformations.Transformation;

public class Polar implements Transformation {

	@Override
	public Vector2D apply(Vector2D t) {
		double omega = FastMath.atan(t.getX() / t.getY());
		double r = t.getNorm();
		return new Vector2D(omega / FastMath.PI, r - 1);
	}

}
