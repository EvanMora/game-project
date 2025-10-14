package zengine.view;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import zengine.domain.Vector;

public class Sprite {
    private int width, height, zIndex;
    Image image;
    Vector position = new Vector(0, 0);

    public Sprite(String path) {
        try {
                image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }
}
