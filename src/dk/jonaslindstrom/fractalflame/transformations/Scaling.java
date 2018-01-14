package dk.jonaslindstrom.fractalflame.transformations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Scaling implements Transformation {

	private double s;

	public Scaling(double s) {
		this.s = s;
	}
	
	@Override
	public Vector2D apply(Vector2D t) {
		return t.scalarMultiply(s);
	}

}
