package characters;

import objects.Bullet;
import zengine.Config;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.Entity;

public class Crab extends Enemy {
    enum State {
        IDLE,
        RUN
    }

    int speed = 10;
    State currentState = State.IDLE;

    public Crab(GamePanel gp, double y) {
        super(gp);
        this.health = 3;
        this.collider = new CollisionRect(2 * Config.tileSize, 2 * Config.tileSize);
        this.position = new Vector(-collider.getWidth(), y);
    }

    boolean movingRight = true;

    @Override
    public void process() {
        switch (currentState) {
            case State.IDLE -> {
                if (movingRight)
                    velocity.setX(1);
                else 
                    velocity.setX(-1);
                    
                if (position.getX() >= 0 && movingRight)
                    currentState = State.RUN;
                
                if (position.getX() <= Config.width - collider.getWidth() && !movingRight) 
                    currentState = State.RUN;
                
            }

            case State.RUN -> {
                if (movingRight)
                    velocity.setX(speed);
                else
                    velocity.setX(-speed);

                if ((position.getX() <= -collider.getWidth()) && !movingRight) {
                    movingRight = true;
                    currentState = State.IDLE;
                }

                if ((position.getX() >= Config.width) && movingRight) {
                    movingRight = false;
                    currentState = State.IDLE;
                }
            }
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
        return "/assets/crab.png";
    }

}
