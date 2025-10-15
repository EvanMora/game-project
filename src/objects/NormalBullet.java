package objects;

import java.awt.Color;

import zengine.domain.entities.Entity;
import zengine.domain.CollisionRect;

public class NormalBullet extends Bullet {
    public NormalBullet(Entity owner, double x, double y) {
        super(owner, x, y);
        this.speed = 20;
        this.collider = new CollisionRect(6, 4);
        this.color = new Color(0x6ceded);
    }

    @Override
    protected String getSpritePath() {
        return "/assets/ship.png";
    }

    @Override
    public void process() {
        velocity.set(0, -speed);

        if (position.getY() < 0)
            active = false;
    }

}