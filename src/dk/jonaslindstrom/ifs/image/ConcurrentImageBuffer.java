package dk.jonaslindstrom.ifs.image;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentImageBuffer {

  private final int[][][] buffer;
  private final ConcurrentLinkedQueue<Update> queue;
  private final AtomicInteger counter;
  private final int limit = 10000000;


  public ConcurrentImageBuffer(int[][][] buffer) {
    this.buffer = buffer;
    this.queue = new ConcurrentLinkedQueue<>();
    this.counter = new AtomicInteger();
  }

  public ConcurrentImageBuffer(int width, int height, int depth) {
    this(new int[width][height][depth]);
  }

  public void update(int x, int y, int z) {
    this.queue.add(new Update(x, y, z));
    if (counter.incrementAndGet() > limit) {
      flush();
    }
  }

  private synchronized void flush() {
    while (!queue.isEmpty()) {
      Update update = queue.poll();
      buffer[update.x][update.y][update.z]++;
    }
    counter.set(0);
  }

  private static class Update {
    private final int x, y, z;
    private Update(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }
  }

  public int[][][] getBuffer() {
    flush();
    return buffer;
  }

}
