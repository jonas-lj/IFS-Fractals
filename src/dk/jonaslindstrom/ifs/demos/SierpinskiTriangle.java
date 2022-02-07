package dk.jonaslindstrom.ifs.demos;

import dk.jonaslindstrom.ifs.ChaosGame;
import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensity;
import dk.jonaslindstrom.ifs.transformations.Transformation;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class SierpinskiTriangle {
  public static void main(String[] arguments) throws IOException {

    Vector2D v1 = new Vector2D(0.0, 0.0);
    Vector2D v2 = new Vector2D(6.0, 0.0);
    Vector2D v3 = new Vector2D(3.0, Math.sqrt(27.0));

    List<Transformation> functions = new ArrayList<>();
    functions.add((v,k) -> v.add(v1).scalarMultiply(0.5));
    functions.add((v,k) -> v.add(v2).scalarMultiply(0.5));
    functions.add((v,k) -> v.add(v3).scalarMultiply(0.5));

    int[][] histogram = ChaosGame.chaosGame(functions, 10000, 500, new Rectangle2D.Double(0.0, 0.0, 6.0, Math.sqrt(27)),
        new Dimension(600, 600));
    IO.generateImage(histogram, new LogDensity(), new File("st.png"));
  }

}
