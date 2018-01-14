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
import dk.jonaslindstrom.fractalflame.transformations.LinearTransformation;
import dk.jonaslindstrom.fractalflame.transformations.Transformation;

public class DragonCurve {
	public static void main(String[] arguments) throws IOException {

		double pi = Math.PI;
		
		List<Transformation> functions = new ArrayList<Transformation>();
		functions.add(v -> new LinearTransformation(Math.cos(pi / 4.0), Math.sin(-pi / 4.0),
				Math.sin(pi / 4.0), Math.cos(pi / 4.0)).apply(v)
						.scalarMultiply(1.0 / Math.sqrt(2.0)));
		functions.add(v -> new LinearTransformation(Math.cos(3.0 * pi / 4.0),
				Math.sin(-3.0 * pi / 4.0), Math.sin(3.0 * pi / 4.0), Math.cos(3.0 * pi / 4.0))
						.apply(v).scalarMultiply(1.0 / Math.sqrt(2.0)).add(new Vector2D(1.0, 0.0)));
		
		ChaosGame ff = new ChaosGame(functions, 5000, 1000,
				new Rectangle2D.Double(-0.5, -0.5, 2.0, 1.5), new Dimension(1000, 750));
		IO.generateImage(ff.chaosGame(), new Binary(), new File("dc.png"));
	}

}
