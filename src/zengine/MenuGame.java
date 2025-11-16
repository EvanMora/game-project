package zengine;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import screens.StarsBG;

public class MenuGame extends JPanel {

    private Game mainGame;

    // Fondo animado de estrellas (tu clase)
    private StarsBG starsBG = new StarsBG();

    // ---- NAVE ANIMADA EN ZIG-ZAG DIAGONAL ----
    private int shipX = -60;
    private int shipY = 200;       // posición inicial Y
    private int shipSpeedX = 4;    // velocidad horizontal
    private int shipSpeedY = 2;    // velocidad vertical
    private int dirY = 1;          // dirección Y (+1 abajo, -1 arriba)

    // Imagen de la nave (reemplaza el dibujo pixel art)
    private Image shipImage;

    public MenuGame(Game mainGame) {
        this.mainGame = mainGame;
        setLayout(null);
        setFocusable(true);

        // Cargar la imagen del PNG (ajusta la ruta si es necesario)
        try {
            shipImage = new ImageIcon(getClass().getResource("/assets/crab.png")).getImage();
        } catch (Exception e) {
            System.err.println("Error cargando la imagen de la nave: " + e.getMessage());
            // Opcional: puedes usar una imagen por defecto o manejar el error de otra forma
        }

        // === BOTÓN START (pixel-art) ===
        JButton start = new JButton("START");
        start.setBounds(280, 350, 200, 60);
        start.setFont(new Font("Monospaced", Font.BOLD, 24));
        start.setBackground(new Color(0x6e5181));
        start.setForeground(new Color(0x6ceded));
        start.setBorder(BorderFactory.createLineBorder(new Color(0x0d001a)));
        start.addActionListener(e -> mainGame.startGame());
        add(start);

        // === BOTÓN EXIT ===
        JButton exit = new JButton("EXIT");
        exit.setBounds(280, 430, 200, 60);
        exit.setFont(new Font("Monospaced", Font.BOLD, 24));
        exit.setBackground(new Color(0x6e5181));
        exit.setForeground(new Color(0x6ceded));
        exit.setBorder(BorderFactory.createLineBorder(new Color(0x0d001a)));
        exit.addActionListener(e -> System.exit(0));
        add(exit);

        // === Timer Animación 60 FPS ===
        new Timer(16, e -> animate()).start();
    }

    /* ============================================================
                           ANIMACIÓN COMPLETA
       ============================================================ */
    private void animate() {

        // Estrellas usando tu clase
        starsBG.updateStars();

        // ---- NAVE EN ZIG-ZAG DIAGONAL ----
        shipX += shipSpeedX;
        shipY += shipSpeedY * dirY;

        // Cambia dirección al tocar límites Y
        if (shipY < 200 || shipY > 600) {
            dirY *= -1;
        }

        // Cuando salga a la derecha, reinicia
        if (shipX > 820) {
            shipX = -60;
            shipY = 200 + new Random().nextInt(400); // reinicio aleatorio vertical (200 a 600)
            dirY = new Random().nextBoolean() ? 1 : -1; // dirección Y aleatoria
        }

        repaint();
    }

    /* ============================================================
                                DIBUJO
       ============================================================ */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        // Fondo
        g.setColor(new Color(0x0d001a));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Estrellas animadas (tu clase)
        starsBG.draw(g);
        

        // TÍTULO ESTILO 16 BITS
        drawTitle(g);

        // NAVE CON PNG (zig-zag diagonal)
        drawShip((Graphics2D) g, shipX, shipY);
    }

    /* ============================================================
                        TÍTULO ESTILO RETRO
       ============================================================ */
    private void drawTitle(Graphics g) {
        g.setFont(new Font("Monospaced", Font.BOLD, 48));

        // Glow
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

    /* ============================================================
                 NAVE CON PNG – ANIMACIÓN ZIG-ZAG DIAGONAL
       ============================================================ */
    private void drawShip(Graphics2D g2, int x, int y) {
        // Si la imagen se cargó correctamente, dibujarla
        if (shipImage != null) {
            // Cambia estos valores para ajustar el tamaño de la nave
            int shipWidth = 60;  // Ancho deseado en píxeles
            int shipHeight = 45; // Alto deseado en píxeles
            
            g2.drawImage(shipImage, x, y, shipWidth, shipHeight, this);  // Dibuja el PNG escalado
        } else {
            // Fallback: si no hay imagen, dibuja la nave original (opcional, para evitar errores)
            Color body = new Color(0, 240, 255);
            Color cockpit = new Color(255, 80, 200);

            g2.setColor(body);

            // --- CUERPO PRINCIPAL ---
            g2.fillRect(x + 10, y, 20, 6);
            g2.fillRect(x + 6, y + 6, 28, 6);
            g2.fillRect(x + 2, y + 12, 36, 6);
            g2.fillRect(x, y + 18, 40, 8);

            // --- ALAS ---
            g2.fillRect(x - 10, y + 18, 10, 4);
            g2.fillRect(x + 40, y + 18, 10, 4);

            // --- PUNTA ---
            g2.fillRect(x + 14, y - 6, 12, 6);

            // --- CABINA ---
            g2.setColor(cockpit);
            g2.fillRect(x + 14, y + 6, 12, 8);

            // --- LUCES ---
            g2.setColor(Color.WHITE);
            g2.fillRect(x + 8, y + 24, 4, 4);
            g2.fillRect(x + 28, y + 24, 4, 4);
        }
    }
}
