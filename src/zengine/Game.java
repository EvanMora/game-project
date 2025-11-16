package zengine;

import javax.swing.JFrame;

public class Game {

    private JFrame window;
    private MenuGame menuPanel;
    private GamePanel gamePanel;

    public Game() {

        window = new JFrame("Space Invaders");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(768, 768);
        window.setLocationRelativeTo(null);

        // Crear menú y colocarlo como panel inicial
        menuPanel = new MenuGame(this);
        window.setContentPane(menuPanel);

        window.setVisible(true);
    }

    // Llamado cuando presionan START en el menú
    public void startGame() {

        gamePanel = new GamePanel();

        // Cambiar panel
        window.setContentPane(gamePanel);

        // Refrescar ventana
        window.revalidate();
        window.repaint();

        // Asegurar que GamePanel reciba input
        gamePanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        new Game();
    }
}