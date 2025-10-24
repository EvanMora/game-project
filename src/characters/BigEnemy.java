package characters;

import javax.swing.Timer;

import objects.Bullet;
import objects.NormalBullet;
import zengine.Config;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.Entity;

public class BigEnemy extends Enemy {
    int speed = 3;
    Timer shotDelay = new Timer(2000, e -> shot());

    public BigEnemy(GamePanel gp) {
        super(gp);
        this.health = 2;
        this.position = new Vector(100, 100);
        this.collider = new CollisionRect(2 * Config.tileSize, 2 * Config.tileSize);
        shotDelay.start();
    }

    boolean movingRight = true;

    @Override
    public void process() {
        // Detects when collide
        if (position.getX() + speed >= (Config.width - collider.getWidth()))
            movingRight = false;

        if (position.getX() - speed <= 0)
            movingRight = true;

        // Movement
        if (movingRight)
            velocity.setX(speed);
        else
            velocity.setX(-speed);
        
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
            shotDelay.stop();
        }
    }

    public void shot() {
        Bullet b = new NormalBullet(this, 
                position.getX() + collider.getWidth() / 2,
                position.getY() + collider.getHeight(), 
                270);

        b.getPosition().setX(b.getPosition().getX() - b.getCollider().getWidth() / 2);
        gp.eManager.add(b);
    }

    @Override
    public String getSpritePath() {
        return "/assets/alien.png";
    }
}
