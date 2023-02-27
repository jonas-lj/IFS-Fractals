package dk.jonaslindstrom.ifs.image;

public interface ImageBuffer {

    void update(int x, int y, int z);

    int get(int x, int y, int z);


    int[][][] getBuffer();

}
