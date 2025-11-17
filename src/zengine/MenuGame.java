package zengine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.FileInputStream;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import screens.StarsBG;

public class MenuGame extends JPanel {

    private Game mainGame;

    private StarsBG starsBG = new StarsBG();
    private Font font;

    // Ship data
    private int shipX = -60;
    private int shipY = 200;
    private int shipSpeedX = 4;
    private int shipSpeedY = 2;
    private int dirY = 1;
    private Image shipImage;


    public MenuGame(Game mainGame) {
        this.mainGame = mainGame;
        setLayout(null);
        setFocusable(true);

        // Load the image and the font
        try {
            shipImage = new ImageIcon(getClass().getResource("/assets/little_alien.png")).getImage();
        } catch (Exception e) {
            System.err.println("Error cargando la imagen de la nave: " + e.getMessage());
        }
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/font/PixelOperator8-Bold.ttf"));
            font = font.deriveFont(Font.PLAIN, 40);
        } catch (Exception e) {
            System.out.println("Font didn't load correctly");
            font = new Font("Monospaced", Font.BOLD, 48);
        }

        // Start button
        JButton start = new JButton("START");
        start.setBounds(280, 350, 200, 60);
        start.setFont(font.deriveFont(Font.PLAIN, 24));
        start.setBackground(new Color(0x6e5181));
        start.setForeground(new Color(0x6ceded));
        start.setBorder(BorderFactory.createLineBorder(new Color(0x0d001a)));
        start.addActionListener(e -> mainGame.startGame());
        add(start);

        // Exit button
        JButton exit = new JButton("EXIT");
        exit.setBounds(280, 430, 200, 60);
        exit.setFont(font.deriveFont(Font.PLAIN, 24));
        exit.setBackground(new Color(0x6e5181));
        exit.setForeground(new Color(0x6ceded));
        exit.setBorder(BorderFactory.createLineBorder(new Color(0x0d001a)));
        exit.addActionListener(e -> System.exit(0));
        add(exit);

        // === Timer Animación 60 FPS ===
        new Timer(16, e -> animate()).start();
    }

    /* Animations ship and stars */
    private void animate() {

        starsBG.updateStars();

        // Ship Animation
        shipX += shipSpeedX;
        shipY += shipSpeedY * dirY;

        // Change the direction when touch the limits
        if (shipY < 200 || shipY > 600) {
            dirY *= -1;
        }

        // When the ship is out of the screen reload animation
        if (shipX > 820) {
            shipX = -60;
            shipY = 200 + new Random().nextInt(400); // reinicio aleatorio vertical (200 a 600)
            dirY = new Random().nextBoolean() ? 1 : -1; // dirección Y aleatoria
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Background
        g.setColor(new Color(0x0d001a));
        g.fillRect(0, 0, getWidth(), getHeight());

        starsBG.draw(g);
        
        drawTitle(g);

        drawShip((Graphics2D) g, shipX, shipY);
    }

    // Draw the titles of the game
    private void drawTitle(Graphics g) {

        g.setFont(font);

        g.setColor(new Color(0x6ceded));
        drawCentered(g, "GALACTIC INVADERS", getWidth(), 150);

        g.setColor(new Color(0x6f1d5c));
        drawCentered(g, "PRESS START TO PLAY", getWidth(), 210);
    }

    private void drawCentered(Graphics g, String text, int width, int y) {
        FontMetrics fm = g.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    private void drawShip(Graphics2D g2, int x, int y) {
        int shipWidth = Config.tileSize;
        int shipHeight = Config.tileSize;
            
        g2.drawImage(shipImage, x, y, shipWidth, shipHeight, this);  // Dibuja el PNG escalado
    }
}
