package characters.SharkBoss;

import javax.swing.Timer;

import objects.Bullet;
import objects.NormalBullet;
import zengine.Config;

public class ShotingState {
    SharkBoss owner;
    int speed = 4;

    Timer shotDelay = new Timer(1000, e -> shot());

    public ShotingState(SharkBoss owner) {
        this.owner = owner;
        shotDelay.start();
    }

    boolean movingRight = false;

    public void process() {
        if (owner.getPosition().getX() + speed >= (Config.width - owner.getCollider().getWidth()))
            movingRight = false;

        if (owner.getPosition().getX() - speed <= 0)
            movingRight = true;

        if (movingRight)
            owner.getVelocity().setX(speed);
        else
            owner.getVelocity().setX(-speed);
        
    }

    public void shot() {
        double x = owner.getPosition().getX() + owner.getCollider().getWidth() / 2;
        double y = owner.getPosition().getY() + owner.getCollider().getHeight();

        Bullet a = new NormalBullet(owner, x, y, 270);
        Bullet b = new NormalBullet(owner, x, y, 225);
        Bullet c = new NormalBullet(owner, x, y, 315);

        owner.getGp().eManager.add(a);
        owner.getGp().eManager.add(b);
        owner.getGp().eManager.add(c);
    }

    public void stop() {
        shotDelay.stop();
    }
}
