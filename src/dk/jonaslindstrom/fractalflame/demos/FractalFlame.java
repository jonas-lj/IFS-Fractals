package dk.jonaslindstrom.fractalflame.demos;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dk.jonaslindstrom.fractalflame.ChaosGame;
import dk.jonaslindstrom.fractalflame.io.IO;
import dk.jonaslindstrom.fractalflame.io.LogDensity;
import dk.jonaslindstrom.fractalflame.transformations.RandomlyParametrizedFractalFlameTransformation;
import dk.jonaslindstrom.fractalflame.transformations.Transformation;
import dk.jonaslindstrom.fractalflame.transformations.variations.VariationsFactory;

public class FractalFlame {

	public static void main(String[] arguments) throws IOException {
		
		List<Transformation> functions = new ArrayList<Transformation>();
		for (int i = 0; i < 5; i++) {
			functions.add(new RandomlyParametrizedFractalFlameTransformation(new VariationsFactory()));
		}
		ChaosGame ff = new ChaosGame(functions, 1000000, 5000,
				new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0), new Dimension(1000, 1000));
		IO.generateImage(ff.chaosGame(), new LogDensity(), new File("ff.png"));
	}

}
