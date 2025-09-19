package characters;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import main.GamePanel;
import objects.NormalBullet;
import util.Block;
import util.KeyHandler;
import util.Vector;

public class Player extends CharacterBody {

    int speed = 8;
    
    int shotDelay = 1000/3;
    boolean canShot = true;
    Timer shotDelayTimer = new Timer(shotDelay, (ActionEvent e) -> canShot = true);

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new Block(gp.tileSize, 2 * gp.tileSize);
        this.sprite = new ImageIcon("assets/ship.png").getImage();
        this.keyH = keyH;
        this.gp = gp;
    }

    @Override
    public void update() {
        // Rect direction movement
        if (keyH.leftPressed && position.getX() - speed >= 0)
            position.setX(position.getX() - speed);

        else if (keyH.rightPressed && position.getX() + speed <= (gp.width - collider.getWidth()))
            position.setX(position.getX() + speed);

        else if (keyH.upPressed && position.getY() - speed >= 0)
            position.setY(position.getY() - speed);

        else if (keyH.downPressed && position.getY() + speed <= (gp.height - collider.getHeight()))
            position.setY(position.getY() + speed);

        if (keyH.spacePressed && canShot)
            attack();
    }

    @Override
    public void draw(Graphics g) {
        // Drawing the player
        g.drawImage(
                getSprite(),
                getPosition().getX(),
                getPosition().getY(),
                collider.getWidth(),
                collider.getHeight(),
                null);
    }

    @Override
    public void attack() {
        canShot = false;
        shotDelayTimer.start();
        
        gp.addBullet(new NormalBullet(
            position.getX() + collider.getWidth() / 2, 
            position.getY()));
    }

}
