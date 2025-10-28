package zengine.domain.entities;

import java.awt.Image;

import zengine.GamePanel;
import zengine.domain.CollisionRect;

public abstract class CharacterBody extends Entity {
    protected int health;
    protected boolean alive = true;
    protected Image sprite;
    protected boolean movingRight = true;

    public CharacterBody(GamePanel gp) {
        super(gp);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public CollisionRect getCollider() {
        return collider;
    }

    public boolean isAlive() {
        return alive;
    }
    
 
    protected void moveOscillating(double speed, double yCenter, double amplitude, double frequency) {
     if (position.getX() + collider.getWidth() >= zengine.Config.width)
        movingRight = false;
     if (position.getX() <= 0)
        movingRight = true;

     double vx = movingRight ? speed : -speed;

     double desiredY = yCenter + Math.sin(System.currentTimeMillis() * frequency) * amplitude;

     double smoothFactor = 0.12;
     double vy = (desiredY - position.getY()) * smoothFactor;

     double maxVy = 2.0;
     if (vy > maxVy) vy = maxVy;
     if (vy < -maxVy) vy = -maxVy;

     velocity.setX(vx);
     velocity.setY(vy);
    }

    

}
