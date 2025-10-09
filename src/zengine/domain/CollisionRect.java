package zengine.domain;

/*
 * To represent the collisions and the space
 * of the each object
 */
public class CollisionRect {
    private int height, width;

    public CollisionRect(int height, int width) {
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
