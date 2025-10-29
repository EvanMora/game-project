package characters;

import objects.Bullet;
import objects.NormalBullet;
import zengine.Config;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.Entity;

public class Alien2 extends Enemy {

    private int speed = 6;

    // Shot control and movement//
    private long lastShotTime = 0;
    private boolean isShooting = false;
    private long stopStartTime = 0;
    private long creationTime = 0;
    private boolean hasSecondShot = false;

    // Parámetros 
    private final long START_DELAY = 2000;   // Time before it starts to shoot (ms)
    private final long STOP_DURATION = 500; // How much time it stays still before shooting (ms)
    private final long BETWEEN_SHOTS = 100;  // Time between shots (ms)
    private final long TIME_BETWEEN_ATTACKS = 3000; // time between pauses/shots (ms)
    private final long ATTACK_RANDOM_EXTRA = 3000;  // random variation (ms)

    public Alien2(GamePanel gp) {
        super(gp);
        this.health = 3;
        this.position = new Vector(0, 0);
        this.collider = new CollisionRect(3 * Config.tileSize, 3 * Config.tileSize);
        this.creationTime = System.currentTimeMillis();
    }

    //Double shooting with visible separation
    public void shot() {
        double x = position.getX() + collider.getWidth() / 2;
        double y = position.getY() + collider.getHeight();
        double offset = 15; // horizontal separation between bullets

        Bullet a = new NormalBullet(this, x - offset, y, 270);
        Bullet b = new NormalBullet(this, x + offset, y, 270);

        gp.eManager.add(a);
        gp.eManager.add(b);
    }

    @Override
    public void process() {
        long currentTime = System.currentTimeMillis();

        //In the beggining it just moves, doesn't shoot
        if (currentTime - creationTime < START_DELAY) {
            moveOscillating(speed, 50, 20, 0.002);
            return;
        }

        //Normal movement if it's not shooting
        if (!isShooting) {
            moveOscillating(speed, 50, 20, 0.002);
        } else {
            // Still while it shoots
            velocity.setX(0);
            velocity.setY(0);
        }

        //Double shooting and pauses control
        if (!isShooting && currentTime - lastShotTime > TIME_BETWEEN_ATTACKS + Math.random() * ATTACK_RANDOM_EXTRA) {
            isShooting = true;
            stopStartTime = currentTime;
            hasSecondShot = false; // the second shot restarts
            shot(); // first shot
            lastShotTime = currentTime;
        }

        // Second shot one time after the first shot
        if (isShooting && !hasSecondShot && currentTime - stopStartTime > BETWEEN_SHOTS) {
            shot();
            hasSecondShot = true;
        }

        // After being still for a while, continues moving
        if (isShooting && currentTime - stopStartTime > STOP_DURATION) {
            isShooting = false;
        }
    }

    @Override
    public void onCollision(Entity other) {
        if (other instanceof Bullet) {
            Bullet b = (Bullet) other;
            if (b.owner instanceof Enemy) return;
            hurt(1);
        }
    }

    public void hurt(int damage) {
        health -= damage;
        if (health <= 0) {
            this.active = false;
        }
    }

    @Override
    protected String getSpritePath() {
        return "/assets/alien2.png";
    }
}
