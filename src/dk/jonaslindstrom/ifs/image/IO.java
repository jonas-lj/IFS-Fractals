package dk.jonaslindstrom.ifs.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class IO {

  public static void generateImage(int[][][] image, ColorMapWithClass colormap, File file) throws IOException {
    BufferedImage bi = generateImage(image, colormap);
    ImageIO.write(bi, "PNG", file);
  }

  public static void generateImage(int[][] image, ColorMap colormap, File file) throws IOException {
    BufferedImage bi = generateImage(image, colormap);
    ImageIO.write(bi, "PNG", file);
  }

  public static BufferedImage generateImage(int[][][] image, ColorMapWithClass colormap) throws IOException {
    return generateImage(colormap.apply(image), colormap.getBackgroundColor());
  }

  public static BufferedImage generateImage(Color[][] image, Color backgroundColor) {
    int width = image.length;
    int height = image[0].length;

    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = bi.createGraphics();
    graphics.setColor(backgroundColor);
    graphics.fillRect(0, 0, width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        bi.setRGB(x, y, image[x][y].getRGB());
      }
    }
    return bi;
  }

  public static void writeCSV(int[][][] histogram, File file) throws IOException {
      FileWriter out = new FileWriter(file);
      try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
        int width = histogram.length;
        int height = histogram[0].length;
        int classes = histogram[0][0].length;
        for (int x = 0; x < width; x++) {
          for (int y = 0; y < height; y++) {
              List<String> record = Arrays.stream(histogram[x][y]).mapToObj(Integer::toString).collect(
                  Collectors.toList());
                printer.printRecord(record);
          }
        }
    }
      out.close();
  }

  public static int[][][] readCSV(int w, int h, int c, File file) throws IOException {

    int[][][] histogram = new int[w][h][c];

    Reader in = new FileReader(file);
    Iterator<CSVRecord> records = CSVFormat.DEFAULT
        .parse(in).iterator();

    for (int x = 0; x < w; x++) {
      for (int y = 0; y < h; y++) {
        CSVRecord record = records.next();
        for (int j = 0; j < c; j++) {
          histogram[x][y][j] = Integer.parseInt(record.get(j));
        }
      }
    }
    in.close();

    return histogram;
  }

  public static BufferedImage generateImage(int[][] image, ColorMap colormap) throws IOException {
    return generateImage(colormap.apply(image), colormap.getBackgroundColor());
  }

}
