package characters;

import objects.Bullet;
import zengine.Config;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.CharacterBody;
import zengine.domain.entities.Entity;

/*
 * The most basic enemy than moves right and left
 */
public class BasicEnemy extends CharacterBody {

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
        if (other instanceof Bullet)
            this.active = false;
    }

    @Override
    protected String getSpritePath() {
        return "/assets/little_alien.png";
    }

}