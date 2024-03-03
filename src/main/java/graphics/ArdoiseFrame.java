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
import javax.swing.JPanel;

public class ArdoiseFrame extends JPanel {

    private static final long serialVersionUID = 1L;

    int x = 0, y = 0;

    JPanel dessin;
    JPanel colorPanel;
    JButton clearButton;
    Color currentColor = Color.BLACK; // Couleur actuelle pour dessiner

    public ArdoiseFrame() {

        dessin = new JPanel();
        colorPanel = new JPanel();
        clearButton = new JButton(new ImageIcon("limpar-limpo.png"));//icone du bouton clear

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

        // Config Panel de couleur
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.add(createColorButton(Color.RED));
        colorPanel.add(createColorButton(Color.GREEN));
        colorPanel.add(createColorButton(Color.BLUE));
        colorPanel.add(createColorButton(Color.YELLOW));
        colorPanel.add(createColorButton(Color.BLACK));
        colorPanel.add(createColorButton(Color.GRAY));

        // position des éléments
        this.setLayout(new BorderLayout());
        this.add(colorPanel, BorderLayout.EAST);
        this.add(clearButton, BorderLayout.WEST);
        this.add(dessin, BorderLayout.CENTER);

        // Ajoute un ActionListener au bouton Effacer
        clearButton.addActionListener((ActionEvent e) -> {
            clearDrawing();
        });
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
}
