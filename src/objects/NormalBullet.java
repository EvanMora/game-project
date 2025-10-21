package objects;

import java.awt.Color;

import zengine.domain.entities.Entity;
import zengine.Config;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;

public class NormalBullet extends Bullet {
    public NormalBullet(Entity owner, double x, double y, double angle) {
        super(owner, x, y);
        this.speed = 20;
        this.collider = new CollisionRect(3 * Config.scale, 2 * Config.scale);
        this.color = new Color(0x6ceded);
        this.velocity = Vector.directionTo(angle).product(speed);
    }

    @Override
    protected String getSpritePath() {
        return "/assets/ship.png";
    }

    @Override
    public void process() {
        if (position.getY() < 0 || position.getY() > Config.height)
            active = false;
    }

}