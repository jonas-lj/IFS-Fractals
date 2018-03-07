package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.transformations.FractalFlameTransformation;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Sinusodial;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Spherical;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Swirl;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FractalFlame {

	public static void main(String[] arguments) throws IOException {
		
		List<Transformation> variations = new ArrayList<>();
		variations.add(new Sinusodial());
		variations.add(new Spherical());
		variations.add(new Swirl());
		
		List<Transformation> functions = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			functions.add(new FractalFlameTransformation(variations, new Random()));
		}
		ChaosGame ff = new ChaosGame(functions);
		
		long start = System.currentTimeMillis();
		int[][] histogram = ff.chaosGame(10000, 2000,
				new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0), new Dimension(800, 800));
		IO.generateImage(histogram, new LogDensity(), new File("ff/ff.png"));
		System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
	}

}
