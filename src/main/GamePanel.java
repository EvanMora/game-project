package main;

import javax.swing.JPanel;
import javax.swing.Timer;

import characters.Player;
import util.KeyHandler;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Principal class of the game which define the game loop
 */
public class GamePanel extends JPanel implements ActionListener {
    // Data of the window
    public final int tileSize = 32;
    public final int rows = 16;
    public final int columns = 16;
    public final int height = rows * tileSize;
    public final int width = columns * tileSize;

    Timer gameLoop;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);

    public GamePanel() {
        setBackground(new Color(0x0d001a));
        setVisible(true);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        addKeyListener(keyHandler);

        gameLoop = new Timer(1000 / 60, this);

        gameLoop.start();
    }

    /*
     * Game loop of the game than will be executed 60
     * times per second
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        player.update();
    }

    /*
     * Will paint the different images or components
     * of the game
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);
    }

}
