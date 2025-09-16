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
    int speed = 8;

    public Player() {
        this.health = 1;
        this.position = new Vector(32, 32);
        this.collider = new Block(32, 64);
        this.sprite = new ImageIcon("assets/ship.png").getImage();
    }

    @Override
    public void handleMove() {
        switch (currentState) {
            case State.LEFT:
                this.position.setX(this.position.getX() - speed);
                break;

            case State.RIGHT:
                this.position.setX(this.position.getX() + speed);
                break;

            case State.UP:
                this.position.setY(this.position.getY() - speed);
                break;
 
            case State.DOWN:
                this.position.setY(this.position.getY() + speed);
                break;
                
            default:
                break;
        }
    }

    @Override
    public void attack() {
        // Todo: Implement the shot of the ship
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
            currentState = State.LEFT;
        } else if (e.getKeyChar() == 'd') {
            currentState = State.RIGHT;
        } else if (e.getKeyChar() == 'w') {
            currentState = State.UP;
        } else if (e.getKeyChar() == 's') {
            currentState = State.DOWN;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        currentState = State.IDLE;
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    
}
