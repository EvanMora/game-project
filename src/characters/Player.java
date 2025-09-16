package characters;

import javax.swing.ImageIcon;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import util.Block;
import util.Vector;

public class Player extends CharacterBody implements KeyListener {

    enum State {
        IDLE,
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }

    State currentState = State.IDLE;
    int speed = 32;

    public Player() {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new Block(32, 64);
        this.sprite = new ImageIcon("assets/ship.png").getImage();
    }

    @Override
    public void handleMove() {
        // Todo: Implement the movement of the player 
    }

    @Override
    public void attack() {
        // Todo: Implement the shot of the ship
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // currentState = State.LEFT;
            this.position.setX(this.position.getX() - speed);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // currentState = State.RIGHT;
            this.position.setX(this.position.getX() + speed);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // currentState = State.UP;
            this.position.setY(this.position.getY() - speed);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // currentState = State.DOWN;
            this.position.setY(this.position.getY() + speed);
        }
    }
    
}
