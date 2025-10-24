package characters;

import objects.Bullet;
import zengine.Config;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.Entity;

/*
 * The most basic enemy than moves right and left
 */
public class BasicEnemy extends Enemy {

    int speed = 3;

    public BasicEnemy(GamePanel gp) {
        super(gp);
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new CollisionRect(1 * Config.tileSize, 1 * Config.tileSize);
    }

    boolean movingRight = true;

    /*
     * Moves from left to right of the screen
     */
    @Override
    public void process() {
        // Detects when collide
        if (position.getX() + speed >= 14 * Config.tileSize)
            movingRight = false;

        if (position.getX() - speed <= 2 * Config.tileSize)
            movingRight = true;

        // Movement
        if (movingRight)
            velocity.setX(speed);
        else
            velocity.setX(-speed);

        // position.setY((-Math.pow(position.getX() - (Config.width/2), 2))/150 + Config.height - 2 * Config.tileSize);
        position.setY(150 * Math.sin(position.getX()/40) + 550);
    }

    @Override
    public void onCollision(Entity other) {
        if (other instanceof Bullet) {
            Bullet b = (Bullet) other;
            if (b.owner instanceof Enemy) return;
            active = false;
        }
    }

    @Override
    protected String getSpritePath() {
        return "/assets/little_alien.png";
    }

}