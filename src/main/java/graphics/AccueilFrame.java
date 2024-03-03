/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Elisa Bothy
 */
public class AccueilFrame extends JPanel {

    private static final long serialVersionUID = 1L;
    JLabel jl;
    String text;
    JLabel icon;
    static ImageIcon herbert = new ImageIcon(Game.class.getResource("/images/herbert.png"));

    public AccueilFrame() {
        jl = new JLabel();
        icon = new JLabel();
        initGui();
    }

    private void initGui() {
        text = "<html>Bienvenue cher enfant ! <br>"
                + "Prépare toi à vivre de grandes aventures ! <br>"
                + "Clique en haut sur l'onglet qui t'intéresses <br>"
                + "et surtout AMUSE-TOI !</html>";
        jl.setText(text);
        jl.setForeground(new Color(72, 181, 163));
        jl.setFont(new Font("Bold", Font.BOLD, 40));
        icon.setIcon(herbert);
        icon.setBackground(new Color(255, 255, 176));
        icon.setPreferredSize(new Dimension(200, 20));
        this.setBackground(new Color(255, 255, 176));
        this.setLayout(new BorderLayout());
        this.add(jl, BorderLayout.EAST);
        this.add(icon, BorderLayout.WEST);
    }

}
