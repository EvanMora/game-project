package characters.SharkBoss;

import javax.swing.Timer;

import characters.BasicEnemy;
import characters.BigEnemy;
import zengine.Config;

public class SpawnState {
    SharkBoss owner;
    int speed = 5;
    Timer spawnTime = new Timer(5000, e -> spawn());
    boolean movingRight;

    public SpawnState(SharkBoss owner, boolean movingRight) {
        this.owner = owner;
        this.movingRight = movingRight;
    }

    public void stop() {
        spawnTime.stop();
    }

    public void start() {
        spawn();
        spawnTime.start();
    }

    public void process() {
        if (owner.getPosition().getX() + speed >= (Config.width - owner.getCollider().getWidth()))
            movingRight = false;

        if (owner.getPosition().getX() - speed <= 0)
            movingRight = true;

        if (movingRight)
            owner.getVelocity().setX(speed);
        else
            owner.getVelocity().setX(-speed);
                
    }

    private void spawn() {
        BasicEnemy e1 = new BasicEnemy(owner.getGp());
        e1.getPosition().set(owner.getPosition().getX(), owner.getPosition().getY());
        BasicEnemy e2 = new BasicEnemy(owner.getGp());
        e2.getPosition().set(owner.getPosition().getX(), owner.getPosition().getY());
        BigEnemy be1 = new BigEnemy(owner.getGp());
        be1.getPosition().set(owner.getPosition().getX(), owner.getPosition().getY());

        owner.getGp().eManager.add(be1);
        owner.getGp().eManager.add(e1);
        owner.getGp().eManager.add(e2);
    }
}
