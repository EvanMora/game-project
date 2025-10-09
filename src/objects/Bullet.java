package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import zengine.domain.CollisionRect;
import zengine.domain.Vector;

public abstract class Bullet {

    protected Vector position;
    protected int speed;
    protected CollisionRect collider;
    protected boolean active = true;
    protected Color color;

    public Bullet(double x, double y) {
        this.position = new Vector(x, y);
    }

    // Every kind of bullet defines its own movement
    public abstract void update();

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(
            (int) position.getX(), 
            (int) position.getY(), 
            collider.getWidth(), 
            collider.getHeight());
    }

    public Rectangle getBounds() {
        return new Rectangle(
            (int) position.getX(), 
            (int) position.getY(), 
            collider.getWidth(), 
            collider.getHeight());
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Vector getPosition() {
        return position;
    }

    public CollisionRect getCollider() {
        return collider;
    }
}
