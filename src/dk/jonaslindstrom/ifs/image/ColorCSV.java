package dk.jonaslindstrom.ifs.image;

import dk.jonaslindstrom.ifs.image.IO;
import dk.jonaslindstrom.ifs.image.LogDensityWithClass;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ColorCSV {

  public static void main(String[] arguments) throws IOException {
    File file = new File("flame_huge_00_01.csv");
    int[][][] histogram = IO.readCSV(7087, 5567, 5, file);

    List<Color> colors = List.of(
        Color.decode("#204C5B"),
        Color.decode("#867088"),
        Color.decode("#00C6A8"),
        Color.decode("#F59432"),
        Color.decode("#924755")
    );

    IO.generateImage(histogram, new LogDensityWithClass(colors), new File("ff_huge_01.png"));
  }

}
