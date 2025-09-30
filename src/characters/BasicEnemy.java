package characters;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import main.GamePanel;
import util.Block;
import util.Vector;

/*
 * The most basic enemy than moves right and left
 */
public class BasicEnemy extends CharacterBody {

    int speed = 3;

    GamePanel gp;

    public BasicEnemy(GamePanel gp) {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new Block(2 * gp.tileSize, 2 * gp.tileSize);
        this.sprite = new ImageIcon("assets/alien.png").getImage();
        this.gp = gp;
    }

    boolean movingRight = true;

    @Override
    public void update() {
        // Detects when collide
        if (position.getX() + speed >= (gp.width - collider.getWidth()))
            movingRight = false;

        if (position.getX() - speed <= 0)
            movingRight = true;

        // Movement
        if (movingRight)
            position.setX(position.getX() + speed);
        else
            position.setX(position.getX() - speed);

    }

    public Rectangle getBounds() {
        return new Rectangle(
                (int) position.getX(),
                (int) position.getY(),
                collider.getWidth(),
                collider.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        if (!visible)
            return;

        g.drawImage(
                getSprite(),
                (int) getPosition().getX(),
                (int) getPosition().getY(),
                collider.getHeight(),
                collider.getWidth(),
                null);
    }

    @Override
    public void attack() {
    }
}