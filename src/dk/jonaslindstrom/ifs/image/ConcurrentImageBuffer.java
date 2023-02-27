package dk.jonaslindstrom.ifs.image;

import java.awt.Dimension;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentImageBuffer implements ImageBuffer {

  private final int[][][] buffer;
  private final int[] dimensions;

  public ConcurrentImageBuffer(int[][][] buffer) {
    this.buffer = buffer;
    this.dimensions = new int[] {buffer.length, buffer[0].length, buffer[0][0].length};
  }

  public ConcurrentImageBuffer(int width, int height, int depth) {
    this(new int[width][height][depth]);
  }

  public void update(int x, int y, int z) {
    if (x < 0 || x >= dimensions[0] || y < 0 || y >= dimensions[1] || z < 0 || z >= dimensions[2]) {
      return;
    }

    // There's a small possibility that two threads will write to the same entry at the same time but we ignore that.
    buffer[x][y][z]++;
  }

  public int[][][] getBuffer() {
    return buffer;
  }

  public int get(int x, int y, int z) {
    return buffer[x][y][z];
  }

}
