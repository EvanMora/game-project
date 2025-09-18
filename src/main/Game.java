package main;

import javax.swing.JFrame;

public class Game {
    /*
     * Open and set the window and add the principal
     * JPanel GamePanel
     */
    public static void main(String[] args) {

        JFrame window = new JFrame("Space Invaders");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setVisible(true);
    }
}