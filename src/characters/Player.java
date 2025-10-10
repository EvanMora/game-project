package characters;

import javax.swing.Timer;

import objects.Bullet;
import objects.NormalBullet;
import zengine.GamePanel;
import zengine.controller.KeyHandler;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.CharacterBody;

public class Player extends CharacterBody {

    int speed = 8;

    int shotDelay = 1000 / 2;
    boolean canShot = true;
    Timer shotDelayTimer = new Timer(shotDelay, e -> canShot = true);

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new CollisionRect(gp.tileSize, 2 * gp.tileSize);
        this.keyH = keyH;
        this.gp = gp;
    }

    @Override
    public void process() {
        // Rect direction movement
        if (keyH.leftPressed && position.getX() >= 0)
            velocity.set(-speed, 0);

        else if (keyH.rightPressed && position.getX() <= (gp.width - collider.getWidth()))
            velocity.set(speed, 0);

        else if (keyH.upPressed && position.getY()  >= 0)
            velocity.set(0, -speed);

        else if (keyH.downPressed && position.getY() <= (gp.height - collider.getHeight()))
            velocity.set(0, speed);

        else
            velocity = new Vector(0, 0);
        // velocity = getAxis().product(speed);
        // System.out.println(velocity.toString());

        if (keyH.spacePressed && canShot)
            attack();
    }

    public void attack() {
        canShot = false;
        shotDelayTimer.start();

        Bullet b = new NormalBullet(
                position.getX() + collider.getWidth() / 2,
                position.getY());

        b.getPosition().setX(b.getPosition().getX() - b.getCollider().getWidth() / 2);
        gp.addBullet(b);
    }

    @Override
    protected String getSpritePath() {
        return "/assets/ship.png";
    }

}
