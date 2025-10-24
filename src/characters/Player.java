package characters;

import javax.swing.Timer;

import objects.Bullet;
import objects.NormalBullet;
import zengine.Config;
import zengine.GamePanel;
import zengine.controller.KeyHandler;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.CharacterBody;
import zengine.domain.entities.Entity;

public class Player extends CharacterBody {

    int speed = 8;

    int shotDelay = 1000 / 2;
    boolean canShot = true;
    Timer shotDelayTimer = new Timer(shotDelay, e -> canShot = true);

    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.health = 1;
        this.position = new Vector(5 * Config.tileSize, 14 * Config.tileSize);
        this.collider = new CollisionRect(Config.tileSize, 2 * Config.tileSize);
        this.keyH = keyH;
    }

    /*
     * Four direction movement and space to shot
     */
    @Override
    public void process() {
        if (keyH.leftPressed && position.getX() >= 0)
            velocity.set(-speed, 0);

        else if (keyH.rightPressed && position.getX() <= (Config.width - collider.getWidth()))
            velocity.set(speed, 0);

        else if (keyH.upPressed && position.getY() >= 0)
            velocity.set(0, -speed);

        else if (keyH.downPressed && position.getY() <= (Config.height - collider.getHeight()))
            velocity.set(0, speed);

        else
            velocity = new Vector(0, 0);

        if (keyH.spacePressed && canShot)
            attack();
    }

    /*
     * Shot a normal bullet
     */
    public void attack() {
        canShot = false;
        shotDelayTimer.start();

        Bullet b = new NormalBullet(
                this,
                position.getX() + collider.getWidth() / 2,
                position.getY(),
                90);

        b.getPosition().setX(b.getPosition().getX() - b.getCollider().getWidth() / 2);
        gp.eManager.add(b);
    }

    @Override
    public void onCollision(Entity other) {
        if (other instanceof Bullet || other instanceof Enemy) {
            active = false;
        }
    }

    @Override
    protected String getSpritePath() {
        return "/assets/ship.png";
    }

}
