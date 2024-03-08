package graphics;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
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
    JPanel clearPanel;
    ButtonChooseColor btncc;
    JButton clearButton;
    JButton eraserButton;
    JButton changePanelButton;
    Color currentColor = Color.BLACK; // Couleur actuelle pour dessiner

    public ArdoiseFrame() {
        //initialisation
        dessin = new JPanel();
        colorPanel = new JPanel();
        btncc = new ButtonChooseColor(this);
        clearPanel = new JPanel();

        clearButton = new JButton(new ImageIcon(Game.class.getResource("/images/limpar-limpo.png")));//icone du bouton clear
        eraserButton = new JButton(new ImageIcon(Game.class.getResource("/images/eraser2.png")));
        changePanelButton = new JButton(new ImageIcon(Game.class.getResource("/images/blackWhite.png")));

        initGui();
        initEvents();
    }

    private void initGui() {
        // Config Panel de couleur
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.add(createColorButton(Color.RED));
        colorPanel.add(createColorButton(Color.GREEN));
        colorPanel.add(createColorButton(Color.BLUE));
        colorPanel.add(createColorButton(Color.YELLOW));
        colorPanel.add(createColorButton(Color.BLACK));
        colorPanel.add(createColorButton(Color.GRAY));
        btncc.setPreferredSize(new Dimension(50, 300));
        colorPanel.add(btncc);

        // Config boutton pour effacer
        changePanelButton.setPreferredSize(new Dimension(50, 175));
        eraserButton.setPreferredSize(new Dimension(50, 75));
        clearButton.setPreferredSize(new Dimension(50, 200));
        clearPanel.setLayout(new BorderLayout());
        clearPanel.add(changePanelButton, BorderLayout.NORTH);
        clearPanel.add(eraserButton, BorderLayout.WEST);
        clearPanel.add(clearButton, BorderLayout.SOUTH);

        // position des éléments
        this.setLayout(new BorderLayout());
        this.add(colorPanel, BorderLayout.EAST);
        this.add(clearPanel, BorderLayout.WEST);
        this.add(dessin, BorderLayout.CENTER);
    }

    private void initEvents() {
        // param pour dessiner
        dessin.setBackground(Color.white);

        dessiner();

        changePanelButton.addActionListener((ActionEvent e) -> {
            changeDessinBackground();
        });

        eraserButton.addActionListener((ActionEvent e) -> {
            eraseDrawing();
        });

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

    /**
     *
     * @return la couleur actuelle du dessin
     */
    public Color getCurrentColor() {
        return currentColor;
    }

    /**
     *
     * @param currentColor la couleur actuelle du dessin
     */
    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    private void eraseDrawing() {
        if (dessin.getBackground().equals(Color.WHITE)) {
            setCurrentColor(Color.WHITE);
        } else {
            setCurrentColor(Color.BLACK);
        }
    }

    private void changeDessinBackground() {
        if (dessin.getBackground().equals(Color.WHITE)) {
            dessin.setBackground(Color.BLACK);
            setCurrentColor(Color.WHITE);
        } else {
            dessin.setBackground(Color.WHITE);
            setCurrentColor(Color.BLACK);
        }
    }

    private void dessiner() {
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
                Graphics2D g2 = (Graphics2D)g;
                
                if(currentColor == dessin.getBackground()){
                    // trait épais
                    g2.setStroke(new BasicStroke(10));
                }else{
                    // trait "normal"
                    g2.setStroke(new BasicStroke(1)); 
                }   
                g2.drawLine(x, y, e.getX(), e.getY());
                x = e.getX();
                y = e.getY(); 
            }
        });
    }

}
