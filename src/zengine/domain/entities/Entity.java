package zengine.domain.entities;

import zengine.domain.Vector;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics;

import zengine.domain.CollisionRect;


public abstract class Entity {
    protected Vector position;
    protected boolean visible = true;
    protected Vector velocity;
    protected CollisionRect collider;
    protected Image sprite;

    public Entity() {
        try {
            sprite = ImageIO.read(getClass().getResource(getSpritePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Vector getPosition() {
        return position;
    }

    public void draw(Graphics g) {
        if (!visible)
            return;
        
        g.drawImage(
                sprite,
                (int) position.getX(),
                (int) position.getY(),
                collider.getWidth(),
                collider.getHeight(),
                null);
    }

    public void update() {
        process();
    }


    protected abstract String getSpritePath();
    public abstract void process();
}
