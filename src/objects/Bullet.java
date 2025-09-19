package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import util.Block;
import util.Vector;

public abstract class Bullet {
    
    protected Vector position;
    protected int speed;
    protected Block collider;
    protected boolean active = true;
    protected Color color;

    public Bullet(int x, int y) {
        this.position = new Vector(x, y);
    }

    // Every kind of bullet defines its own movement
    public abstract void update();

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.getX(), position.getY(), collider.getWidth() , collider.getHeight());
    }

    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), collider.getWidth(), collider.getHeight());
    }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public Vector getPosition() { return position; }

    public Block getCollider() { return collider; }
}
