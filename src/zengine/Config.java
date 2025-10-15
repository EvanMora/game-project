package zengine;

public class Config {
    // Data of the window
    public static final int originalTileSize = 16;
    public static final int scale = 3;

    public static final int tileSize = originalTileSize * scale;
    public static final int rows = 16;
    public static final int columns = 16;
    public static final int height = rows * tileSize;
    public static final int width = columns * tileSize;

}
