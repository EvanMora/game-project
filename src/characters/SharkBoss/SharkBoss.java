package characters.SharkBoss;

import zengine.Config;
import zengine.domain.entities.Entity;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;

import javax.swing.Timer;

import characters.Enemy;
import characters.Player;
import objects.Bullet;
import objects.NormalBullet;

enum State {
    LASER,
    SHOT    
}

public class SharkBoss extends Enemy {
    Player player;
    State currenState = State.SHOT;
    Timer shotDelay = new Timer(1000, e -> shot());

    int speed = 4;

    public SharkBoss(GamePanel gp, Player player) {
        super(gp);
        this.health = 20;
        this.position = new Vector(100, 100);
        this.collider = new CollisionRect(3 * Config.tileSize, 2 * Config.tileSize);
        this.player = player;
        shotDelay.start();
    }

    boolean movingRight = false;
    @Override
    public void process() {
        switch (currenState) {
            case State.SHOT -> {
                if (position.getX() + speed >= (Config.width - collider.getWidth()))
                    movingRight = false;

                if (position.getX() - speed <= 0)
                    movingRight = true;

                if (movingRight)
                    velocity.setX(speed);
                else
                    velocity.setX(-speed);
                
            }
        }
        
        // position.setX(player.getPosition().getX());
        position.setY(50 * Math.sin(position.getX() / 100) + 100);
    }

    public void shot() {
        double x = position.getX() + collider.getWidth() / 2;
        double y = position.getY() + collider.getHeight();

        Bullet a = new NormalBullet(this, x, y, 270);
        Bullet b = new NormalBullet(this, x, y, 250);
        Bullet c = new NormalBullet(this, x, y, 290);

        gp.eManager.add(a);
        gp.eManager.add(b);
        gp.eManager.add(c);
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
