package zengine.domain.entities;

import java.awt.Image;

import zengine.GamePanel;
import zengine.domain.CollisionRect;

public abstract class CharacterBody extends Entity {
    protected int health;
    protected boolean alive = true;
    protected Image sprite;

    public CharacterBody(GamePanel gp) {
        super(gp);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public CollisionRect getCollider() {
        return collider;
    }

    public boolean isAlive() {
        return alive;
    }

}
