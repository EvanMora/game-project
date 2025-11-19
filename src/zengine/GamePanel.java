package zengine;




import characters.Alien2;
import characters.BasicEnemy;
import characters.BigEnemy;
import characters.Crab;
import characters.Player;
import characters.SharkBoss.SharkBoss;
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
    private Game mainGame; // --> 1. AÑADIDO: Referencia a la clase Game principal.

    Timer gameLoop;
    KeyHandler keyHandler = new KeyHandler();

    StarsBG starsBG = new StarsBG();

    Player player = new Player(this, keyHandler);
    SharkBoss sharkBoss = new SharkBoss(this, player);
    //BasicEnemy enemy = new BasicEnemy(this);
    //BigEnemy bigEnemy = new BigEnemy(this);
    // Alien2 alien2 = new Alien2(this);
    //Crab crab = new Crab(this);

    public EntityManager eManager = new EntityManager();

    // --> 2. MODIFICADO: El constructor ahora recibe la instancia de Game.
    public GamePanel(Game mainGame) {
        this.mainGame = mainGame; // --> Se guarda la referencia.

        setBackground(new Color(0x0d001a));
        setPreferredSize(new Dimension(Config.width, Config.height));
        setFocusable(true);
        requestFocus();
        addKeyListener(keyHandler);
        eManager.add(player);
        // eManager.add(alien2);
        //eManager.add(enemy);
        eManager.add(sharkBoss);
        //eManager.add(crab);
        //eManager.add(bigEnemy);

        gameLoop = new Timer(1000 / 60, this);

        gameLoop.start();
    }

    /*
     * Game loop of the game than will be executed 60
     * times per second
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //The logic for checking if the game is over.
        // The Player class has a getLives() and getScore() method.
        if (player.getLives() <= 0) {
            gameLoop.stop(); // Detiene el bucle de este panel.
            mainGame.showGameOver(player.getScore()); // Llama al método de Game para cambiar de pantalla.
            return; // Sale del método para no ejecutar más lógica del juego.
        }

        repaint();

        eManager.update();
        eManager.checkCollisions();
        starsBG.updateStars();
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
