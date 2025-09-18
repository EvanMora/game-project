package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import util.Vector;

public abstract class Bullet {
    
    protected Vector position;
    protected int speed;
    protected int width;
    protected int height;
    protected boolean active = true;
    protected Color color;

    public Bullet(int x, int y) {
        this.position = new Vector(x, y);
    }

    // Every kind of bullet defines its own movement
    public abstract void update();

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(position.getX(), position.getY(), width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public Vector getPosition() { return position; }
}
