package objects;

import java.awt.Color;

import util.Block;

public class NormalBullet extends Bullet {
    public NormalBullet(double x, double y) {
        super(x, y);
        this.speed = 20;
        this.collider = new Block(6, 4);
        this.color = new Color(0x6ceded);
    }

    @Override
    public void update() {
        position.setY(position.getY() - speed);

        if (position.getY() < 0)
            active = false;
    }

}