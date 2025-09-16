package util;

/*
 * To represent the collisions and the space
 * of the each object
 */
public class Block {
    int height, width;

    public Block(int height, int width) {
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
