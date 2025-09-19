package characters;

import java.awt.Graphics;
import java.awt.Image;

import util.Block;
import util.Vector;

public abstract class CharacterBody {
    protected int health;
    protected Vector position;
    protected boolean visible = true;
    protected boolean alive = true;
    // Vector velocity;
    protected Block collider;
    protected Image sprite;  

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Vector getPosition() {
        return position;
    }

    public Block getCollider() {
        return collider;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void attack();

    public void die() {
        health = 0;
        visible = false;
        alive = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isAlive() {
        return alive;
    }

}
