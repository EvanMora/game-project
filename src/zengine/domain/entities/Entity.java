package zengine.domain.entities;

import zengine.domain.Vector;
import zengine.domain.CollisionRect;

import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.Graphics;
import java.awt.Image;

/*
 * Represents each visible and interactive object of the game
 */
public abstract class Entity {
    protected Vector position = new Vector(0, 0);
    protected Vector velocity = new Vector(0, 0);
    protected boolean visible = true;
    protected CollisionRect collider = new CollisionRect(0, 0);
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
        position.setX(position.getX() + velocity.getX());
        position.setY(position.getY() + velocity.getY());
        process();
    }


    protected abstract String getSpritePath();
    public abstract void process();
}
