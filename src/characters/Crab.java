package characters;

import javax.swing.Timer;

import objects.Bullet;
import objects.NormalBullet;
import zengine.Config;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;

public class Crab extends Enemy {
    int speed = 3;
    Timer shotDelay = new Timer(1000, e -> shot());

    public Crab(GamePanel gp) {
        super(gp);
        this.health = 3;
        this.position = new Vector(0, 0);
        this.collider = new CollisionRect(2 * Config.tileSize, 2 * Config.tileSize);
        shotDelay.start();
    }

    public void shot() {
        double x = position.getX() + collider.getWidth() / 2;
        double y = position.getY() + collider.getHeight();

        Bullet a = new NormalBullet(this, x, y, 270);
        Bullet b = new NormalBullet(this, x, y, 255);
        Bullet c = new NormalBullet(this, x, y, 285);

        gp.eManager.add(a);
        gp.eManager.add(b);
        gp.eManager.add(c);
    }

    boolean movingRight = true;
    @Override
    public void process() {
         // Detects when collide
        if (position.getX() + speed >= (Config.width - collider.getWidth()))
            movingRight = false;

        if (position.getX() - speed <= 0)
            movingRight = true;

        // Movement
        if (movingRight)
            velocity.setX(speed);
        else
            velocity.setX(-speed);
        
    }

    @Override
    protected String getSpritePath() {
        return "/assets/crab.png";
    }

}
