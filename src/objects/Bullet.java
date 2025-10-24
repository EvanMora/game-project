package objects;

import java.awt.Color;
import java.awt.Graphics;

import characters.Enemy;
import zengine.domain.Vector;
import zengine.domain.entities.Entity;

public abstract class Bullet extends Entity {
    public Entity owner;
    protected int speed;
    protected Color color;

    public Bullet(Entity owner, double x, double y) {
        super(null);
        this.owner = owner;
        this.position = new Vector(x, y);
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) 
            return;

        g.setColor(color);
        g.fillRect(
                (int) position.getX(),
                (int) position.getY(),
                collider.getWidth(),
                collider.getHeight());
    }

    @Override
    public void onCollision(Entity other) {
        if (other == owner || (owner instanceof Enemy && other instanceof Enemy)) 
            return;
        this.active = false;
    }
}
