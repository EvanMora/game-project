package zengine;

import javax.swing.*;
import java.awt.*;
import screens.StarsBG; 

public class GameOverScreen extends JPanel {

    private Game mainGame;
    private int finalScore;

    //An instance of YOUR StarsBG class is created.
    private StarsBG starsBG = new StarsBG();
    
    private boolean isTextVisible = true;
    private int blinkCounter = 0;

    public GameOverScreen(Game mainGame, int score) {
        this.mainGame = mainGame;
        this.finalScore = score;
        setLayout(null);
        setFocusable(true);

        // Button RESTART 
        JButton restartButton = new JButton("RESTART");
        restartButton.setBounds(280, 350, 200, 60);
        restartButton.setFont(new Font("Monospaced", Font.BOLD, 24));
        restartButton.setBackground(new Color(0x6e5181));
        restartButton.setForeground(new Color(0x6ceded));
        restartButton.setBorder(BorderFactory.createLineBorder(new Color(0x0d001a)));
        restartButton.addActionListener(e -> mainGame.startGame());
        add(restartButton);

        // === BOTÓN MENU ===
        JButton menuButton = new JButton("MENU");
        menuButton.setBounds(280, 430, 200, 60);
        menuButton.setFont(new Font("Monospaced", Font.BOLD, 24));
        menuButton.setBackground(new Color(0x6e5181));
        menuButton.setForeground(new Color(0x6ceded));
        menuButton.setBorder(BorderFactory.createLineBorder(new Color(0x0d001a)));
        menuButton.addActionListener(e -> mainGame.showMenu());
        add(menuButton);

        // Timer for background animation and blinking
        Timer timer = new Timer(16, e -> animate());
        timer.start();
    }

    private void animate() {
        // Call the class method to move the stars.
        starsBG.updateStars();
        
        blinkCounter++;
        if (blinkCounter > 30) {
            isTextVisible = !isTextVisible;
            blinkCounter = 0;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo oscuro
        g.setColor(new Color(0x0d001a));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Call the class method to draw the stars.
        starsBG.draw(g);

        // Draw the texts on top of the stars
        drawTexts(g);
    }

    private void drawTexts(Graphics g) {
        if (isTextVisible) {
            g.setFont(new Font("Monospaced", Font.BOLD, 72));
            g.setColor(new Color(0xcc0000));
            drawCentered(g, "GAME OVER", getWidth(), 200);
        }

        g.setFont(new Font("Monospaced", Font.BOLD, 32));
        g.setColor(new Color(0x6ceded));
        drawCentered(g, "FINAL SCORE: " + finalScore, getWidth(), 280);
    }

    private void drawCentered(Graphics g, String text, int width, int y) {
        FontMetrics fm = g.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }
}
