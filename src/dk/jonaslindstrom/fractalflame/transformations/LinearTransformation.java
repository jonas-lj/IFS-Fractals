package dk.jonaslindstrom.fractalflame.transformations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class LinearTransformation implements Transformation {
	
	private Vector2D x;
	private Vector2D y;

	public LinearTransformation(double a, double b, double c, double d) {
		this.x = new Vector2D(a, b);
		this.y = new Vector2D(c, d);
	}
	
	@Override
	public Vector2D apply(Vector2D t) {
		return new Vector2D(t.dotProduct(x), t.dotProduct(y));
	}

}
