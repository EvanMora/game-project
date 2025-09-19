package main;

import characters.BasicEnemy;
import characters.Player;
import objects.Bullet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import util.KeyHandler;

/*
 * Principal class of the game which define the game loop
 */
public class GamePanel extends JPanel implements ActionListener {
    // Data of the window
    public final int originalTileSize = 16;
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int rows = 16;
    public final int columns = 16;
    public final int height = rows * tileSize;
    public final int width = columns * tileSize;

    Timer gameLoop;
    KeyHandler keyHandler = new KeyHandler();

    Player player = new Player(this, keyHandler);
    BasicEnemy enemy = new BasicEnemy(this);

    ArrayList<Bullet> bullets = new ArrayList<>();

    public GamePanel() {
        setBackground(new Color(0x0d001a));
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        addKeyListener(keyHandler);

        gameLoop = new Timer(1000 / 60, this);

        gameLoop.start();
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    /*
     * Game loop of the game than will be executed 60
     * times per second
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        player.update();
        enemy.update();

        // Bullets refresh rate
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);

            if (!b.isActive()) {
                
                bullets.remove(i);
                i--;
                continue;
            }

            b.update();

            
            if (b.getPosition().getY() < 0) {
                bullets.remove(i);
                i--;
                continue;
            }

            // Enemy colission (ONLY WORKS FOR ONE ENEMY)
            if (enemy.isAlive() && b.getBounds().intersects(enemy.getBounds())) {
                enemy.die();
                b.setActive(false);
                bullets.remove(i);
                i--;
            }
        }

    }

    /*
     * Will paint the different images or components
     * of the game
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);
        enemy.draw(g);

        for (Bullet b : bullets)
            if (b.isActive())
                b.draw(g);
    }

}
