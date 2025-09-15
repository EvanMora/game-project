package util;

/*
 * To represent the collisions and the space
 * of the each object
 */
public class Block {
    Vector position;
    int height, width;

    public Block(int x, int y, int height, int width) {
        this.position = new Vector(x, y);
        this.height = height;
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

}
