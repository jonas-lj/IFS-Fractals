package dk.jonaslindstrom.fractalflame.demos;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import dk.jonaslindstrom.fractalflame.ChaosGame;
import dk.jonaslindstrom.fractalflame.io.Binary;
import dk.jonaslindstrom.fractalflame.io.IO;
import dk.jonaslindstrom.fractalflame.transformations.Transformation;

public class SierpinskiTriangle {
	public static void main(String[] arguments) throws IOException {

		Vector2D v1 = new Vector2D(0.0, 0.0);
		Vector2D v2 = new Vector2D(6.0, 0.0);
		Vector2D v3 = new Vector2D(3.0, Math.sqrt(27.0));
		
		List<Transformation> functions = new ArrayList<Transformation>();
		functions.add(v -> v.add(v1).scalarMultiply(0.5));
		functions.add(v -> v.add(v2).scalarMultiply(0.5));
		functions.add(v -> v.add(v3).scalarMultiply(0.5));
		
		ChaosGame ff = new ChaosGame(functions, 10000, 500,
				new Rectangle2D.Double(-1.0, -1.0, 8.0, 8.0), new Dimension(1000, 1000));
		IO.generateImage(ff.chaosGame(), new Binary(), new File("st.png"));
	}

}
