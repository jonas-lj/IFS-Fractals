package dk.jonaslindstrom.ifs.image;

import java.util.SortedMap;
import java.util.TreeMap;

public class SparseImageBuffer implements ImageBuffer {

  public SparseImageBuffer(int[][][] buffer) {
    this.dimensions = new int[] {buffer.length, buffer[0].length, buffer[0][0].length};
    for (int x = 0; x < dimensions[0]; x++) {
      for (int y = 0; y < dimensions[1]; y++) {
        for (int z = 0; z < dimensions[2]; z++) {
          if (buffer[x][y][z] > 0) {
            entries.put(new Entry(x, y, z), buffer[x][y][z]);
          }
        }
      }
    }
  }

  public record Entry(int x, int y, int z) implements Comparable<Entry> {

    @Override
    public int compareTo(Entry o) {
      if (this.x < o.x) {
        return -1;
      } else if (this.x > o.x) {
        return 1;
      }
      if (this.y < o.y) {
        return -1;
      } else if (this.y > o.y) {
        return 1;
      }
      return Integer.compare(this.z, o.z);
    }
  }

  private final int[] dimensions;

  private SortedMap<Entry, Integer> entries = new TreeMap<>();

  public SparseImageBuffer(int[] dimensions) {
    this.dimensions = dimensions;
  }

  public SparseImageBuffer(int width, int height, int depth) {
    this(new int[]{width, height, depth});
  }

  public void update(int x, int y, int z) {
    if (x < 0 || x >= dimensions[0] || y < 0 || y >= dimensions[1] || z < 0 || z >= dimensions[2]) {
      return;
    }

    entries.merge(new Entry(x, y, z), 1, Integer::sum);
  }

  @Override
  public int get(int x, int y, int z) {
    return entries.getOrDefault(new Entry(x, y, z), 0);
  }

  @Override
  public int[][][] getBuffer() {
    int[][][] buffer = new int[dimensions[0]][dimensions[1]][dimensions[2]];
    for (int x = 0; x < dimensions[0]; x++) {
      for (int y = 0; y < dimensions[1]; y++) {
        for (int z = 0; z < dimensions[2]; z++) {
          buffer[x][y][z] = get(x, y, z);
        }
      }
    }
    return buffer;
  }


}
