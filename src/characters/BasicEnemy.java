package characters;

import java.awt.Rectangle;

import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.CharacterBody;

/*
 * The most basic enemy than moves right and left
 */
public class BasicEnemy extends CharacterBody {

    int speed = 3;

    GamePanel gp;

    public BasicEnemy(GamePanel gp) {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new CollisionRect(2 * gp.tileSize, 2 * gp.tileSize);
        this.gp = gp;
    }

    boolean movingRight = true;

    /*
     * Moves from left to right of the screen
     */
    @Override
    public void process() {
        // Detects when collide
        if (position.getX() + speed >= (gp.width - collider.getWidth()))
            movingRight = false;

        if (position.getX() - speed <= 0)
            movingRight = true;

        // Movement
        if (movingRight)
            velocity.setX(speed);
        else
            velocity.setX(-speed);
            // position.setX(position.getX() - speed);

    }

    public Rectangle getBounds() {
        return new Rectangle(
                (int) position.getX(),
                (int) position.getY(),
                collider.getWidth(),
                collider.getHeight());
    }

    @Override
    protected String getSpritePath() {
        return "/assets/alien.png";
    }

}