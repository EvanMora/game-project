package characters.SharkBoss;

import characters.Player;
import zengine.Config;

public class LaserState {
    enum State {
        MOVING,
        LASER
    }

    State currentState;
    SharkBoss owner;
    Player player;
    int speed = 3;
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
                
                if (owner.getPosition().getX() == player.getPosition().getX()) {
                    currentState = State.LASER;
                }
            }

            case State.LASER -> {

            }
        }

    }

}
