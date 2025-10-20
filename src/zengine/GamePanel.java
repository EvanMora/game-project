package zengine;

import characters.BasicEnemy;
import characters.BigEnemy;
import characters.Player;
import screens.StarsBG;
import zengine.controller.EntityManager;
import zengine.controller.KeyHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * Principal class of the game which define the game loop
 */
public class GamePanel extends JPanel implements ActionListener {
    Timer gameLoop;
    KeyHandler keyHandler = new KeyHandler();

    StarsBG starsBG = new StarsBG();

    Player player = new Player(this, keyHandler);
    BasicEnemy enemy = new BasicEnemy(this);
    BigEnemy bigEnemy = new BigEnemy(this);

    public EntityManager eManager = new EntityManager();

    public GamePanel() {
        setBackground(new Color(0x0d001a));
        setPreferredSize(new Dimension(Config.width, Config.height));
        setFocusable(true);
        requestFocus();
        addKeyListener(keyHandler);
        eManager.add(player);
        eManager.add(enemy);
        eManager.add(bigEnemy);

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

        eManager.update();
        eManager.checkCollisions();
        starsBG.updateStars();
        // System.out.println(eManager.getEntities().size());
    }

    /*
     * Will paint the different images or components
     * of the game
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        starsBG.draw(g);
        eManager.drawAll(g);
    }

}
