/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Elisa Bothy
 */
public class ButtonChooseColor extends JButton {

    private static final long serialVersionUID = 1L;
    private ArdoiseFrame ardoiseFrame;
    ImageIcon gradient;
    private boolean imageVisible = true;

    public ButtonChooseColor(ArdoiseFrame ardoiseFrame) {
        this.gradient = new ImageIcon(Game.class.getResource("/images/gradient.png"));

        this.ardoiseFrame = ardoiseFrame;
        this.setPreferredSize(new Dimension(50, 300));
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Color init = Color.WHITE;
                Color couleur = JColorChooser.showDialog(null, "Choisissez une couleur", init);
                if (couleur != null) {
                    setBackground(couleur);
                    ardoiseFrame.setCurrentColor(couleur); // Change couleur crayon
                    hideImage();
                } else {
                    showImage();
                }
            }
        });
    }

    public void hideImage() {
        imageVisible = false;
        repaint(); // pour que l'image soit invisible et qu'on puisse voir la couleur choisie
    }

    public void showImage() {
        imageVisible = true;
        repaint(); // pour que l'image soit invisible et qu'on puisse voir la couleur choisie
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageVisible) {
            Graphics2D g2 = (Graphics2D) g.create();
            // Dessiner l'image à la taille du bouton
            Image scaledImage = gradient.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            scaledIcon.paintIcon(this, g2, 0, 0);
            g2.dispose();
        }
    }
}
