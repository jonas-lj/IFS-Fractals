package dk.jonaslindstrom.fractalflame.demos;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import dk.jonaslindstrom.fractalflame.ChaosGame;
import dk.jonaslindstrom.fractalflame.io.IO;
import dk.jonaslindstrom.fractalflame.io.LogDensity;
import dk.jonaslindstrom.fractalflame.transformations.AffineTransformation;
import dk.jonaslindstrom.fractalflame.transformations.LinearTransformation;
import dk.jonaslindstrom.fractalflame.transformations.StochasticFunctionSet;
import dk.jonaslindstrom.fractalflame.transformations.Transformation;

public class BarnsleysFern {
	
	public static void main(String[] arguments) throws IOException {

		List<Pair<Transformation, Double>> functions = new ArrayList<Pair<Transformation, Double>>();
		functions.add(new Pair<Transformation, Double>(new AffineTransformation(0.85, 0.04, -0.04, 0.85, 0.0, 1.60), 0.85));
		functions.add(new Pair<Transformation, Double>(new AffineTransformation(-0.15, 0.28, 0.26, 0.24, 0.0, 0.44), 0.07));
		functions.add(new Pair<Transformation, Double>(new AffineTransformation(0.20, -0.26, 0.23, 0.22, 0.0, 1.60), 0.07));
		functions.add(new Pair<Transformation, Double>(new LinearTransformation(0.0, 0.0, 0.0, 0.16), 0.01));
		
		ChaosGame ff = new ChaosGame(new StochasticFunctionSet(functions), 100000, 5000,
				new Rectangle2D.Double(-5.0, 0.0, 12.0, 12.0), new Dimension(1000, 1000));
		IO.generateImage(ff.chaosGame(), new LogDensity(), new File("bf.png"));
	}
}
