package guia6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Hashtable;

public class EnemyAdminUI extends JFrame {

    // ==== Color palette ====
    private final Color COLOR_BG_MAIN = new Color(12, 6, 20);      // #0C0614
    private final Color COLOR_BG_PANEL = new Color(44, 14, 45);    // #2C0E2D
    private final Color COLOR_BG_TABLE = new Color(90, 20, 64);    // #5A1440
    private final Color COLOR_BTN_MAIN = new Color(116, 244, 238); // #74F4EE
    private final Color COLOR_BTN_CRUD = new Color(140, 42, 102);  // #8C2A66
    private final Color COLOR_TEXT = Color.WHITE;

    // ==== Class that represents an enemy ====
    static class EnemyData {
        private int id;
        private String tipo;
        private int vida;
        private double velocidad;

        public EnemyData(int id, String tipo, int vida, double velocidad) {
            this.id = id;
            this.tipo = tipo;
            this.vida = vida;
            this.velocidad = velocidad;
        }

        public int getId() { return id; }
        public String getTipo() { return tipo; }
        public int getVida() { return vida; }
        public double getVelocidad() { return velocidad; }

        public void setTipo(String tipo) { this.tipo = tipo; }
        public void setVida(int vida) { this.vida = vida; }
        public void setVelocidad(double velocidad) { this.velocidad = velocidad; }
    }

    // ==== Principal Hashtable (CRUD) ====
    private Hashtable<Integer, EnemyData> enemies = new Hashtable<>();

    private JDesktopPane desktop;

    public EnemyAdminUI() {

        super("Administrador de Enemigos - Space Invaders");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        desktop.setBackground(COLOR_BG_MAIN);
        add(desktop, BorderLayout.CENTER);

        // ==== Central Button ====
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(COLOR_BG_MAIN);

        JButton openBtn = new JButton("Gestionar Enemigos");
        openBtn.setFont(new Font("Arial", Font.BOLD, 22));
        openBtn.setFocusPainted(false);
        openBtn.setBackground(COLOR_BTN_MAIN);
        openBtn.setForeground(Color.BLACK);
        openBtn.setPreferredSize(new Dimension(300, 80));

        centerPanel.add(openBtn);

        desktop.add(centerPanel);
        centerPanel.setBounds((800 - 300) / 2, (600 - 80) / 2, 300, 80);

        openBtn.addActionListener(e -> openCRUDWindow());

        preloadDemoData();
    }

    private void preloadDemoData() {
        addEnemy(new EnemyData(1, "Alien 1", 100, 2.5));
        addEnemy(new EnemyData(2, "Alien 2", 120, 2.0));
        addEnemy(new EnemyData(3, "Crab", 90, 3.0));
        addEnemy(new EnemyData(4, "UFO", 200, 4.5));
    }

    // ==== CRUD =====
    private void addEnemy(EnemyData e) { enemies.put(e.getId(), e); }

    private EnemyData getEnemy(int id) { return enemies.get(id); }

    private void updateEnemy(EnemyData e) { enemies.put(e.getId(), e); }

    private void removeEnemy(int id) { enemies.remove(id); }

    private void openCRUDWindow() {
        EnemyTableFrame frame = new EnemyTableFrame();
        desktop.add(frame);
        frame.setVisible(true);

        try { frame.setSelected(true); } catch (Exception ignored) {}
    }

    // ==== Inner windows CRUD ====
    private class EnemyTableFrame extends JInternalFrame {

        private DefaultTableModel tableModel;
        private JTable table;

        public EnemyTableFrame() {

            super("Gestión de Enemigos", true, true, true, true);
            setSize(550, 350);
            setLayout(new BorderLayout());

            // === Table ===
            tableModel = new DefaultTableModel(new Object[]{"ID", "Tipo", "Vida", "Velocidad"}, 0);
            table = new JTable(tableModel);

            table.setBackground(COLOR_BG_TABLE);
            table.setForeground(Color.WHITE);
            table.setSelectionBackground(new Color(123, 95, 142)); // tono púrpura suave
            table.setSelectionForeground(Color.WHITE);

            refreshTable();

            JScrollPane scroll = new JScrollPane(table);
            scroll.getViewport().setBackground(COLOR_BG_TABLE);
            add(scroll, BorderLayout.CENTER);

            // === Buttons ===
            JPanel buttons = new JPanel();
            buttons.setBackground(COLOR_BG_PANEL);

            JButton addBtn = createCrudButton("Agregar");
            JButton editBtn = createCrudButton("Actualizar");
            JButton delBtn = createCrudButton("Eliminar");

            buttons.add(addBtn);
            buttons.add(editBtn);
            buttons.add(delBtn);

            add(buttons, BorderLayout.SOUTH);

            addBtn.addActionListener(e -> addEnemyDialog());
            editBtn.addActionListener(e -> updateEnemyDialog());
            delBtn.addActionListener(e -> deleteSelectedEnemy());
        }

        private JButton createCrudButton(String txt) {
            JButton btn = new JButton(txt);
            btn.setBackground(COLOR_BTN_CRUD);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            return btn;
        }

        private void refreshTable() {
            tableModel.setRowCount(0);

            for (EnemyData e : enemies.values()) {
                tableModel.addRow(new Object[]{
                        e.getId(),
                        e.getTipo(),
                        e.getVida(),
                        e.getVelocidad()
                });
            }
        }

        private void addEnemyDialog() {
            JTextField idField = new JTextField();
            JTextField tipoField = new JTextField();
            JTextField vidaField = new JTextField();
            JTextField velField = new JTextField();

            Object[] fields = {
                    "ID:", idField,
                    "Tipo:", tipoField,
                    "Vida:", vidaField,
                    "Velocidad:", velField
            };

            int opt = JOptionPane.showConfirmDialog(this, fields,
                    "Agregar Enemigo", JOptionPane.OK_CANCEL_OPTION);

            if (opt == JOptionPane.OK_OPTION) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String tipo = tipoField.getText();
                    int vida = Integer.parseInt(vidaField.getText());
                    double vel = Double.parseDouble(velField.getText());

                    addEnemy(new EnemyData(id, tipo, vida, vel));
                    refreshTable();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: valores no válidos");
                }
            }
        }

        private void updateEnemyDialog() {
            int row = table.getSelectedRow();
            if (row == -1) return;

            int id = (int) table.getValueAt(row, 0);
            EnemyData e = getEnemy(id);

            JTextField tipoField = new JTextField(e.getTipo());
            JTextField vidaField = new JTextField(String.valueOf(e.getVida()));
            JTextField velField = new JTextField(String.valueOf(e.getVelocidad()));

            Object[] fields = {
                    "Tipo:", tipoField,
                    "Vida:", vidaField,
                    "Velocidad:", velField
            };

            int opt = JOptionPane.showConfirmDialog(this, fields,
                    "Actualizar Enemigo", JOptionPane.OK_CANCEL_OPTION);

            if (opt == JOptionPane.OK_OPTION) {
                try {
                    e.setTipo(tipoField.getText());
                    e.setVida(Integer.parseInt(vidaField.getText()));
                    e.setVelocidad(Double.parseDouble(velField.getText()));
                    updateEnemy(e);
                    refreshTable();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Valores no válidos");
                }
            }
        }

        private void deleteSelectedEnemy() {
            int row = table.getSelectedRow();
            if (row == -1) return;
            int id = (int) table.getValueAt(row, 0);
            removeEnemy(id);
            refreshTable();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EnemyAdminUI().setVisible(true));
    }
}
