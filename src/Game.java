import javax.swing.JFrame;

import java.awt.Dimension;

public class Game {
    /*
     * Open and set the window and add the principal
     * JPanel GamePanel
     */
    public static void main(String[] args) {
        // Data of the window
        int tileSize = 32;
        int rows = 16;
        int columns = 16;
        int height = rows * tileSize;
        int width = columns * tileSize;

        JFrame window = new JFrame("Space Invaders");
        window.setSize(new Dimension(width, height));
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.setSize(new Dimension(width, height));

        gamePanel.requestFocus();
    }
}