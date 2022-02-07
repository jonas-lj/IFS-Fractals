package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.transformations.FractalFlameTransformation;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.ComplexMultiplication;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Disc;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Handkerchief;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Heart;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Polar;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Popcorn;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Sinusodial;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Spherical;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Spiral;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Swirl;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.midi.Transmitter;

public class RandomFractalFlame {

  public static void main(String[] arguments)
      throws IOException {

    Random seedGenerator = new Random();
    for (int j = 0; j < 10; j++) {
      long seed = seedGenerator.nextLong();
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

      int[][] histogram = ChaosGame
          .chaosGame(functions, 10000, 20000, new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0),
              new Dimension(1000, 1000), Long.toHexString(seed));
      IO.generateImage(histogram, new LogDensity(true), new File("r" + Long.toHexString(seed) + ".tif"));
    }

  }


}
