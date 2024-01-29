import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class BattleWindow extends JFrame {
    private Piece attacker;
    private Piece defender;

    public BattleWindow(Piece attacker, Piece defender) {
        this.attacker = attacker;
        this.defender = defender;

        // Configura la interfaz gráfica de la ventana de batalla
        setLayout(new GridLayout(3, 1));

        // Crea paneles para cada pieza en la batalla
        JPanel attackerPanel = new JPanel();
        JPanel battlePanel = new JPanel();
        JPanel defenderPanel = new JPanel();

        // Agrega las imágenes de las piezas a los paneles respectivos
        ImageIcon attackerIcon = (ImageIcon) attacker.getIcon();
        ImageIcon defenderIcon = (ImageIcon) defender.getIcon();

        JLabel attackerLabel = new JLabel(new ImageIcon(attackerIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        JLabel defenderLabel = new JLabel(new ImageIcon(defenderIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));


        // Agrega las imágenes a los paneles
        attackerPanel.add(attackerLabel);
        defenderPanel.add(defenderLabel);

        // Agrega el panel de batalla al centro con la barra horizontal
        battlePanel.setLayout(new BorderLayout());
        battlePanel.add(new BattleBar(), BorderLayout.CENTER);

        // Agrega los paneles a la ventana
        add(attackerPanel);
        add(battlePanel);
        add(defenderPanel);

        // Configura la ventana
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class BattleBar extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int battleResult = calculateBattleResult(); // Método que debes implementar

            int width = getWidth();
            int height = getHeight();

            // Dibuja la barra horizontal
            g.setColor(Color.BLACK);
            g.fillRect(0, height / 2 - 5, width, 10);

            // Dibuja la representación gráfica de la batalla
            int attackerEnd = width * battleResult / 100;
            int defenderStart = width * battleResult / 100;
            g.setColor(Color.RED);
            g.fillRect(0, height / 2 - 5, attackerEnd, 10);
            g.setColor(Color.BLUE);
            g.fillRect(defenderStart, height / 2 - 5, width - defenderStart, 10);
        }

        private int calculateBattleResult() {
            // Método que debes implementar para calcular el resultado de la batalla
            // Debes retornar un valor entre 0 y 100 representando el porcentaje de éxito del atacante
            return 50; // Ejemplo: Empate
        }
    }
}
