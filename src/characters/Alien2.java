package characters;

import objects.Bullet;
import objects.NormalBullet;
import zengine.Config;
import zengine.GamePanel;
import zengine.domain.CollisionRect;
import zengine.domain.Vector;
import zengine.domain.entities.Entity;

public class Alien2 extends Enemy {

    private int speed = 6;

    // Control del disparo y movimiento
    private long lastShotTime = 0;
    private boolean isShooting = false;
    private long stopStartTime = 0;
    private long creationTime = 0;
    private boolean hasSecondShot = false;

    // Parámetros 
    private final long START_DELAY = 2000;   // tiempo antes de que empiece a disparar (ms)
    private final long STOP_DURATION = 500; // cuánto tiempo se queda quieto al disparar (ms)
    private final long BETWEEN_SHOTS = 100;  // tiempo entre los dos disparos (ms)
    private final long TIME_BETWEEN_ATTACKS = 3000; // tiempo entre pausas/disparos (ms)
    private final long ATTACK_RANDOM_EXTRA = 3000;  // variación aleatoria (ms)

    public Alien2(GamePanel gp) {
        super(gp);
        this.health = 3;
        this.position = new Vector(0, 0);
        this.collider = new CollisionRect(3 * Config.tileSize, 3 * Config.tileSize);
        this.creationTime = System.currentTimeMillis();
    }

    //Disparo doble con separación visible
    public void shot() {
        double x = position.getX() + collider.getWidth() / 2;
        double y = position.getY() + collider.getHeight();
        double offset = 15; // separación horizontal entre balas

        Bullet a = new NormalBullet(this, x - offset, y, 270);
        Bullet b = new NormalBullet(this, x + offset, y, 270);

        gp.eManager.add(a);
        gp.eManager.add(b);
    }

    @Override
    public void process() {
        long currentTime = System.currentTimeMillis();

        //Al inicio solo se mueve, no dispara
        if (currentTime - creationTime < START_DELAY) {
            moveOscillating(speed, 50, 20, 0.002);
            return;
        }

        //Movimiento normal si no está disparando
        if (!isShooting) {
            moveOscillating(speed, 50, 20, 0.002);
        } else {
            // Quieto mientras dispara
            velocity.setX(0);
            velocity.setY(0);
        }

        //Control del disparo doble y pausas
        if (!isShooting && currentTime - lastShotTime > TIME_BETWEEN_ATTACKS + Math.random() * ATTACK_RANDOM_EXTRA) {
            isShooting = true;
            stopStartTime = currentTime;
            hasSecondShot = false; // reiniciamos el segundo disparo
            shot(); // primer disparo
            lastShotTime = currentTime;
        }

        // Segundo disparo una sola vez después de un tiempo
        if (isShooting && !hasSecondShot && currentTime - stopStartTime > BETWEEN_SHOTS) {
            shot();
            hasSecondShot = true;
        }

        // Luego de quedarse quieto un tiempo, reanuda el movimiento
        if (isShooting && currentTime - stopStartTime > STOP_DURATION) {
            isShooting = false;
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
        return "/assets/alien2.png";
    }
}
