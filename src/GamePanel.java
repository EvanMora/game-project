import javax.swing.JPanel;
import javax.swing.Timer;

import characters.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Principal class of the game which define the game loop
 */
public class GamePanel extends JPanel implements ActionListener {

    Timer gameLoop;
    Player player = new Player();

    public GamePanel() {
        setBackground(new Color(0x0d001a));
        setVisible(true);
        setFocusable(true);
        addKeyListener(player);

        gameLoop = new Timer(1000/60, this);

        gameLoop.start();
    }

    /* 
     * Game loop of the game than will be executed 60
     * times per second
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        player.handleMove();
    }

    /*
     * Will paint the different images or components
     * of the game
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing the player
        g.drawImage(
            player.getSprite(), 
            player.getPosition().getX(), 
            player.getPosition().getY(),
            null
            );
    }

}
