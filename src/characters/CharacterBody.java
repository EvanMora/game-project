package characters;

import java.awt.Image;

import util.Block;
import util.Vector;

public abstract class CharacterBody {
    private int health;
    private Vector position;
    // Vector velocity;
    private Block collider;
    private Image sprite;  

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

    public abstract void handleMove();
    public abstract void attack();

    protected void die() {
        health = 0;
    }
}
