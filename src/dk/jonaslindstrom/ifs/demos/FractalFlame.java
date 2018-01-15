package dk.jonaslindstrom.ifs.demos;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.transformations.RandomlyParametrizedFractalFlameTransformation;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Disc;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Handkerchief;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Horseshoe;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Polar;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Sinusodial;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Spherical;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Spiral;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Swirl;

public class FractalFlame {

	public static void main(String[] arguments) throws IOException {
		
		List<Transformation> variations = new ArrayList<Transformation>();
		variations.add(new Sinusodial());
		variations.add(new Spherical());
		variations.add(new Swirl());
		variations.add(new Horseshoe());
		variations.add(new Polar());		
		variations.add(new Handkerchief());
		variations.add(new Spiral());
		variations.add(new Disc());
		
		List<Transformation> functions = new ArrayList<Transformation>();
		for (int i = 0; i < 5; i++) {
			functions.add(new RandomlyParametrizedFractalFlameTransformation(variations));
		}
		ChaosGame ff = new ChaosGame(functions);
		int[][] histogram = ff.chaosGame(new SysoutProgressListener(), 1000000, 5000,
				new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0), new Dimension(1000, 1000));
		IO.generateImage(histogram, new LogDensity(), new File("ff.png"));
	}

}
