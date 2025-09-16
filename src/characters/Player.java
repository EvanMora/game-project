package characters;

import javax.swing.ImageIcon;

import util.Block;
import util.KeyHandler;
import util.Vector;

public class Player extends CharacterBody {

    int speed = 6;
    KeyHandler keyH;

    public Player(KeyHandler keyH) {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new Block(32, 64);
        this.sprite = new ImageIcon("assets/ship.png").getImage();
        this.keyH = keyH;
    }

    @Override
    public void handleMove() {

        if (keyH.leftPressed)
            this.position.setX(this.position.getX() - speed);
        else if (keyH.rightPressed)
            this.position.setX(this.position.getX() + speed);
        else if (keyH.upPressed)
            this.position.setY(this.position.getY() - speed);
        else if (keyH.downPressed)
            this.position.setY(this.position.getY() + speed);
    }

    @Override
    public void attack() {
        // Todo: Implement the shot of the ship
    }

}
