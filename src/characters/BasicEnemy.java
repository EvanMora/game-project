package characters;

import javax.swing.Timer;

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

    enum State {
        MOVE,
        ATTACK
    }

    State currentState = State.ATTACK;
    int speed = 3;
    int func = 1;

    Timer moveChange = new Timer(10_000, e -> {
        if (func == 2) {
            func = 1;
            position.setY(550);
        } else {
            func++;
            position.setY(0);
        }
    });

    public BasicEnemy(GamePanel gp) {
        super(gp);
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new CollisionRect(1 * Config.tileSize, 1 * Config.tileSize);
        position.setY(550);
        moveChange.start();
    }

    boolean movingRight = true;

    /*
     * Moves from left to right of the screen
     */
    @Override
    public void process() {
        switch (currentState) {
            case State.MOVE -> {

            }

            case State.ATTACK -> {
                // velocity.set(1, moveFunction(position.getX()));
                // velocity.normalize(); 
                // velocity.product(speed);

                // Detects when collide
                if (position.getX() >= 14 * Config.tileSize)
                    movingRight = false;

                if (position.getX() <= 2 * Config.tileSize)
                    movingRight = true;

                // Movement
                if (movingRight)
                    // velocity.setX(velocity.getX());
                    velocity.setX(speed);
                else
                    // velocity.setX(-velocity.getX());
                    velocity.setX(-speed);

                position.setY(moveFunction(position.getX()));
            }
        }
    }

    private double moveFunction(double x) {
        if (func == 1)
            return 150 * Math.sin(x/40) + 550;
            // return (150 * Math.cos(x/40)) / 40;
        
        return -Math.pow(x - 384, 2)/170 + 690;
        // return -(x - 389)/85;
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