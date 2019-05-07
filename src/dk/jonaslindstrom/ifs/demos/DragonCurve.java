package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.Binary;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.transformations.AffineTransformation;
import dk.jonaslindstrom.ifs.transformations.LinearTransformation;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DragonCurve {
  public static void main(String[] arguments) throws IOException {

    double pi = Math.PI;
    Random random = new Random(123);
    
    List<Transformation> functions = new ArrayList<>();
    double s = 1.0 / Math.sqrt(2.0);
    functions.add(new LinearTransformation(s * Math.cos(pi / 4.0), s * Math.sin(-pi / 4.0),
        s * Math.sin(pi / 4.0), s * Math.cos(pi / 4.0)));
    functions
        .add(new AffineTransformation(s * Math.cos(3.0 * pi / 4.0), s * Math.sin(-3.0 * pi / 4.0),
            s * Math.sin(3.0 * pi / 4.0), s * Math.cos(3.0 * pi / 4.0), 1.0, 0.0));

    ChaosGame ff = new ChaosGame(functions, random);
    int[][] histogram = ff.chaosGame(5000, 1000, new Rectangle2D.Double(-0.5, -0.5, 2.0, 1.5),
        new Dimension(1000, 750));
    IO.generateImage(histogram, new Binary(), new File("dc.png"));
  }

}
