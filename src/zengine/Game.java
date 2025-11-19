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

        showMenu(); // Llamamos a showMenu para crear el panel inicial

        window.setVisible(true);
    }

    // Llamado cuando presionan START en el menú
    public void startGame() {
        // --- LÍNEA CORREGIDA ---
        // Aquí le pasamos "this" al constructor de GamePanel.
        // Esto le da a GamePanel una forma de comunicarse de vuelta con la clase Game.
        gamePanel = new GamePanel(this);

        // Cambiar panel
        window.setContentPane(gamePanel);

        // Refrescar ventana
        window.revalidate();
        window.repaint();

        // Asegurar que GamePanel reciba input
        gamePanel.requestFocusInWindow();
    }

    // Método para mostrar el menú principal
    public void showMenu() {
        menuPanel = new MenuGame(this);
        window.setContentPane(menuPanel);
        window.revalidate();
        window.repaint();
        menuPanel.requestFocusInWindow();
    }

    // Método para mostrar la pantalla de Game Over
    public void showGameOver(int finalScore) {
        GameOverScreen gameOverPanel = new GameOverScreen(this, finalScore);
        window.setContentPane(gameOverPanel);
        window.revalidate();
        window.repaint();
        gameOverPanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        new Game();
    }
}