package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.image.LogDensityWithClass;
import dk.jonaslindstrom.ifs.transformations.FractalFlameTransformation;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Disc;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Handkerchief;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Heart;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Polar;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Popcorn;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Sinusodial;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Spherical;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Spiral;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Swirl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class FractalFlame {

  public static void main(String[] arguments)
      throws IOException {

    long seed = new BigInteger("d23c14a4138969db", 16).longValue();

    Random random = new Random(seed);
    double p = random.nextDouble();

    List<Transformation> variations = new ArrayList<>();
    variations.add(new Disc());
    variations.add(new Handkerchief());
    variations.add(new Heart());
    variations.add(new Polar());
    variations.add(new Sinusodial());
    variations.add(new Spherical());
    variations.add(new Spiral());
    variations.add(new Swirl());
    variations.add(new Popcorn(1.0, 1.0));

    List<Transformation> usedVariations = new ArrayList<>();
    for (Transformation variation : variations) {
      if (random.nextDouble() < p) {
        usedVariations.add(variation);
      }
    }

    List<Transformation> functions = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      functions.add(new FractalFlameTransformation(usedVariations, random));
    }

    List<Color> colors = List.of(
        Color.decode("#204C5B"),
        Color.decode("#867088"),
        Color.decode("#00C6A8"),
        Color.decode("#F59432"),
        Color.decode("#924755")
    );

    // ~2 mins
    //int[][][] histogram = IO.readCSV(7087, 7087, 5, new File("flame_large_02.csv"));
    int[][][] histogram = ChaosGame
        .chaosGameWithClass(functions,50000, 20000,
            new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0),
            new Dimension(7087, 7087), new Random(seed), Long.toHexString(seed));
    //IO.writeCSV(histogram, new File("flame_large_01.csv"));
    IO.generateImage(histogram, new LogDensityWithClass(colors), new File("ffc" + Long.toHexString(seed) + ".tif"));

  }


}
