package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.curves.LissajousCurve;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.transformations.FractalFlameTransformation;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Popcorn;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Sinusodial;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Spherical;
import dk.jonaslindstrom.ifs.transformations.fractalflamevariations.Swirl;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleFunction;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.jcodec.api.awt.AWTSequenceEncoder;

public class FractalFlameMovie {

  public static void main(String[] arguments) throws IOException {
    long seed = -5775821812006337474l; //6610345538332962255l;
    int seconds = 32;
    
    List<Transformation> variations = new ArrayList<>();
    variations.add(new Sinusodial());
    variations.add(new Spherical());
    variations.add(new Swirl());
    
    Popcorn v = new Popcorn(0.0, 1.0);
    variations.add(v);

    Random random = new Random(seed);
    List<Transformation> functions = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      functions.add(new FractalFlameTransformation(variations, random));
    }

    long start = System.currentTimeMillis();
    
    AWTSequenceEncoder enc = AWTSequenceEncoder.create25Fps(new File("out.mp4"));    
    BufferedImage bi;
    Vector2D x;     
    DoubleFunction<Vector2D> curve = t -> new LissajousCurve(1.0, 0.5, 0.0).apply(t).scalarMultiply(2.0);    
    int[][] histogram;
    int n = 25 * seconds;
    for (int i = 0; i < n; i++) {
      x = curve.apply((double) i / n);  
      v.c = x.getX();
      v.f = x.getY();
      
      histogram = ChaosGame.chaosGame(functions, 10000, 1000, new Rectangle2D.Double(-5.0, -5.0, 10.0, 10.0),
          new Dimension(800, 800));
      System.out.println(i + "/" + n);
      bi = IO.generateImage(histogram, new LogDensity());
      
      enc.encodeImage(bi);      
    }
    enc.finish();
    System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
  }

}
