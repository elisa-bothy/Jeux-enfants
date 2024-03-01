package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ArdoiseFrame extends JFrame {

    int x = 0, y = 0;
    private final JPanel container;
    private final JLabel label;
    private final JPanel dessin;
    private final JPanel colorPanel;
    private final JButton clearButton;
    private Color currentColor = Color.BLACK; // Couleur actuelle pour dessiner

    public ArdoiseFrame() {

        this.label = new JLabel();
        this.dessin = new JPanel();
        this.container = new JPanel();
        this.colorPanel = new JPanel();
        this.clearButton = new JButton("");
        //icone du bouton clear
        clearButton.setIcon(new ImageIcon("limpar-limpo.png"));

        
        // param pour dessiner
        dessin.setBackground(Color.white);
        dessin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        dessin.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = dessin.getGraphics();
                g.setColor(currentColor); // Définit la couleur actuelle
                g.drawLine(x, y, e.getX(), e.getY());
                x = e.getX();
                y = e.getY();
            }
        });
        // position des éléments
        container.setLayout(new BorderLayout());
        container.add(label, BorderLayout.SOUTH);
        container.add(colorPanel, BorderLayout.EAST);
        container.add(clearButton, BorderLayout.WEST);
        container.add(dessin, BorderLayout.CENTER);

        // Config Panel de couleur
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.add(createColorButton(Color.RED));
        colorPanel.add(createColorButton(Color.GREEN));
        colorPanel.add(createColorButton(Color.BLUE));
        colorPanel.add(createColorButton(Color.YELLOW));
        colorPanel.add(createColorButton(Color.BLACK));
        colorPanel.add(createColorButton(Color.GRAY));


        // Ajoute un ActionListener au bouton Effacer
        clearButton.addActionListener((ActionEvent e) -> {
            clearDrawing();
        });
        // paramètre de la fenêtre
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(container);
        this.setVisible(true);
    }
    // Pour effacer le dessin
    private void clearDrawing() {
        Graphics g = dessin.getGraphics();
        g.setColor(dessin.getBackground());
        g.fillRect(0, 0, dessin.getWidth(), dessin.getHeight());
    }

    // Boutons de couleur
    private JButton createColorButton(final Color color) {
        JButton button = new JButton();
        button.setBackground(color);
        button.setPreferredSize(new Dimension(50, 300));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentColor = color; // Change couleur crayon
            }
        });
        return button;
    }

    public static void main(String[] args) {
        new ArdoiseFrame();
    }

}
