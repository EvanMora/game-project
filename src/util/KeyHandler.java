package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_W -> upPressed = true;
        }

        /*
        switch (key) {
            case KeyEvent.VK_KP_LEFT -> leftPressed = true;
            case KeyEvent.VK_KP_RIGHT -> rightPressed = true;
            case KeyEvent.VK_KP_DOWN -> downPressed = true;
            case KeyEvent.VK_KP_UP -> upPressed = true;
        }
         */

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_W -> upPressed = false;
        }
        
        /*
        switch (key) {
            case KeyEvent.VK_KP_LEFT -> leftPressed = true;
            case KeyEvent.VK_KP_RIGHT -> rightPressed = true;
            case KeyEvent.VK_KP_DOWN -> downPressed = true;
            case KeyEvent.VK_KP_UP -> upPressed = true;
        }
         */

    }

}
