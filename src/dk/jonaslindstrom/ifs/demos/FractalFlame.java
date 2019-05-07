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

    long seed = 1234;
    Random r = new Random();
    int n = 25;
    for (int j = 0; j < n; j++) {
      seed = r.nextLong();
      
      List<Transformation> variations = new ArrayList<>();
      variations.add(new Sinusodial());
      variations.add(new Spherical());
      variations.add(new Swirl());

      Random random = new Random(seed);
      List<Transformation> functions = new ArrayList<>();
      for (int i = 0; i < 5; i++) {
        functions.add(new FractalFlameTransformation(variations, random));
      }
      ChaosGame ff = new ChaosGame(functions);

      long start = System.currentTimeMillis();

      // int[][] histogram = ff.chaosGame(10000, 2000, new Rectangle2D.Double(-1.5, -3.5, 4.0, 5.0),
      // new Dimension(2480, 3508));
      int[][] histogram = ff.chaosGame(10000, 2000, new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0),
          new Dimension(2480, 3508));
      IO.generateImage(histogram, new LogDensity(true), new File("ff" + seed + ".png"));
      System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");

    }

  }

}
