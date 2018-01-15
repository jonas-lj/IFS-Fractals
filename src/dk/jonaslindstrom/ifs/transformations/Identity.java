package dk.jonaslindstrom.ifs.transformations;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Identity implements Transformation {

	@Override
	public Vector2D apply(Vector2D p) {
		return new Vector2D(p.getX(), p.getY());
	}

}
