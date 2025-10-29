package zengine.domain.entities;

import zengine.domain.Vector;
import zengine.GamePanel;
import zengine.domain.CollisionRect;

import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/*
 * Represents each visible and interactive object of the game
 */
public abstract class Entity {
    protected GamePanel gp;
    protected Vector position = new Vector(0, 0);
    protected Vector velocity = new Vector(0, 0);
    protected boolean visible = true;
    protected boolean active = true;
    protected CollisionRect collider = new CollisionRect(0, 0);
    protected Image sprite;

    public Entity(GamePanel gp) {
        this.gp = gp;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Vector getPosition() {
        return position;
    }
    
    public Vector getVelocity() {
        return velocity;
    }

    public GamePanel getGp() {
        return gp;
    }

    public CollisionRect getCollider() {
        return collider;
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
        position.add(velocity);
        process();
    }

    public Rectangle getBounds() {
        return new Rectangle(
            (int) position.getX(), 
            (int) position.getY(), 
            collider.getWidth(), 
            collider.getHeight());
    }

    public void onCollision(Entity other) {
    }

    protected abstract String getSpritePath();
    public abstract void process();
}
