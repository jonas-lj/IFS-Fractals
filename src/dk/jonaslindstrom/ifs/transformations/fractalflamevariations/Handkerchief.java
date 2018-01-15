package dk.jonaslindstrom.ifs.transformations.fractalflamevariations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import dk.jonaslindstrom.ifs.transformations.Transformation;

public class Handkerchief implements Transformation {

	@Override
	public Vector2D apply(Vector2D t) {
		double omega = FastMath.atan(t.getX() / t.getY());
		double r = t.getNorm();
		return new Vector2D(FastMath.sin(omega + r), FastMath.cos(omega - r)).scalarMultiply(r);
	}

}
