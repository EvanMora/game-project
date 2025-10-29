package characters.SharkBoss;

import zengine.Config;
import zengine.domain.entities.Entity;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;

import characters.Enemy;
import characters.Player;
import objects.Bullet;

enum State {
    LASER,
    SHOT,
    SPAWN 
}

public class SharkBoss extends Enemy {
    Player player;
    State currenState = State.SHOT;
    ShotingState shotingState = new ShotingState(this);
    LaserState laserState;

    int speed = 4;

    public SharkBoss(GamePanel gp, Player player) {
        super(gp);
        this.health = 20;
        this.position = new Vector(100, 100);
        this.collider = new CollisionRect(3 * Config.tileSize, 2 * Config.tileSize);
        this.player = player;
        this.laserState = new LaserState(this, player, false);
    }

    boolean movingRight = false;
    @Override
    public void process() {
        switch (currenState) {
            case State.SHOT -> {
                shotingState.process();

                if (health < 15) {
                    currenState = State.LASER;
                    laserState.movingRight = movingRight;
                    shotingState.stop();
                }
            }

            case State.LASER -> {
                laserState.process();
            }

            case State.SPAWN -> {

            }
        }
        
        position.setY(50 * Math.sin(position.getX() / 100) + 100);
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
        return "/assets/shark_boss.png";
    }
   
}
