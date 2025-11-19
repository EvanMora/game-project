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

    private int lives;
    private int score;

    int speed = 8;
    int shotDelay = 1000 / 2;
    boolean canShot = true;
    Timer shotDelayTimer = new Timer(shotDelay, e -> canShot = true);

    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.position = new Vector(5 * Config.tileSize, 12 * Config.tileSize);
        this.collider = new CollisionRect(Config.tileSize, 2 * Config.tileSize);
        this.keyH = keyH;
        
        setDefaultValues();
    }
    
    public void setDefaultValues() {
        
        this.lives = 1;  // The player now starts with only 1 life.
        this.score = 0;
        this.active = true; // Make sure the player is active when starting/restarting.
        this.position.set(5 * Config.tileSize, 12 * Config.tileSize);
    }

    @Override
    public void process() {
        if (!active) return; // Si no está activo, no procesar movimiento.

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
            takeDamage();
        }
    }

    public void takeDamage() {
        this.lives--; // Subtract the only life the player has.

        if (this.lives <= 0) {
            this.active = false; // The player becomes inactive.
        }
    }
    
    public void addScore(int points) {
        this.score += points;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    @Override
    protected String getSpritePath() {
        return "/assets/ship.png";
    }
}