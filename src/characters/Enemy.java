package characters;

import zengine.GamePanel;
import zengine.domain.entities.CharacterBody;

public abstract class Enemy extends CharacterBody {

    public Enemy(GamePanel gp) {
        super(gp);
    }

    protected void moveOscillating(double speed, double yCenter, double amplitude, double frequency) {
        //Limits of horizontal movement
        if (position.getX() + collider.getWidth() >= zengine.Config.width)
            movingRight = false;
        if (position.getX() <= 0)
            movingRight = true;

        double vx = movingRight ? speed : -speed;

        /*Time of the system * frequency(How much speed it has to go up and down)
         * amplitude(How much it goes up and down) and it uses the function sin*/
        double desiredY = yCenter + Math.sin(System.currentTimeMillis() * frequency) * amplitude;

        // Smooth factor for movement
        double smoothFactor = 0.12;

        // It calculates the difference between the actual position and the desired one
        double vy = (desiredY - position.getY()) * smoothFactor;
        
        // Speed limitation
        double maxVy = 2.0;
        if (vy > maxVy) vy = maxVy;
        if (vy < -maxVy) vy = -maxVy;

        velocity.setX(vx);
        velocity.setY(vy);
    }

}