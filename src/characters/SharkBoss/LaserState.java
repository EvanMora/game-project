package characters.SharkBoss;

import javax.swing.Timer;

import characters.Player;
import objects.Bullet;
import objects.NormalBullet;
import zengine.Config;

public class LaserState {
    enum State {
        MOVING,
        LASER
    }

    State currentState = State.MOVING;
    SharkBoss owner;
    Player player;
    int speed = 5;
    Timer laserTime = new Timer(1000, e -> {
        currentState = State.MOVING;
        stop();
    });
    boolean movingRight;

    public LaserState(SharkBoss owner, Player player, boolean movingRight) {
        this.owner = owner;
        this.player = player;
        this.movingRight = movingRight;
    }

    public void process() {
        switch (currentState) {
            case State.MOVING -> {
                if (owner.getPosition().getX() + speed >= (Config.width - owner.getCollider().getWidth()))
                    movingRight = false;

                if (owner.getPosition().getX() - speed <= 0)
                    movingRight = true;

                if (movingRight)
                    owner.getVelocity().setX(speed);
                else
                    owner.getVelocity().setX(-speed);
                
                if (owner.getPosition().getX() >= player.getPosition().getX() - 5 && owner.getPosition().getX() <= player.getPosition().getX() + 5) {
                    currentState = State.LASER;
                    laserTime.start();
                }
            }

            case State.LASER -> {
                owner.getVelocity().setX(0);
                shot();
            }
        }
    }

    public void stop() {
        laserTime.stop();
    }

    private void shot() {
        Bullet b = new NormalBullet(owner, 
                owner.getPosition().getX() + owner.getCollider().getWidth() / 2,
                owner.getPosition().getY() + owner.getCollider().getHeight(), 
                8 * Config.scale,
                270);

        owner.getGp().eManager.add(b);
    }

}
