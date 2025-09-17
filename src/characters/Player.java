package characters;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import main.GamePanel;
import util.Block;
import util.KeyHandler;
import util.Vector;

public class Player extends CharacterBody {

    int speed = 4;

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new Block(32, 64);
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
    }

    @Override
    public void draw(Graphics g) {
        // Drawing the player
        g.drawImage(
                getSprite(),
                getPosition().getX(),
                getPosition().getY(),
                null);
    }

    @Override
    public void attack() {
        // Todo: Implement the shot of the ship
    }

}
