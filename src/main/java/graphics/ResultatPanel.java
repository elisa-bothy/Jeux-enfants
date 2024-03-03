/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Le J c'est le S
 */
public class ResultatPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel jlsolution;
    private JButton jbsolution;
    private JButton jbverification;
    private JButton jbautre;
    private JTextField jtreponse;

    public JLabel getJlsolution() {
        return jlsolution;
    }

    public void setJlsolution(JLabel jlsolution) {
        this.jlsolution = jlsolution;
    }

    public JButton getJbsolution() {
        return jbsolution;
    }

    public void setJbsolution(JButton jbsolution) {
        this.jbsolution = jbsolution;
    }

    public JButton getJbverification() {
        return jbverification;
    }

    public void setJbverification(JButton jbverification) {
        this.jbverification = jbverification;
    }

    public JButton getJbautre() {
        return jbautre;
    }

    public void setJbautre(JButton jbautre) {
        this.jbautre = jbautre;
    }

    public JTextField getJtreponse() {
        return jtreponse;
    }

    public void setJtreponse(JTextField jtreponse) {
        this.jtreponse = jtreponse;
    }

    public ResultatPanel() {

        jlsolution = new JLabel(" 2 ");
        jbsolution = new JButton("Solution");
        jbverification = new JButton("Verification");
        jbautre = new JButton("Nouveau Calcul");
        jtreponse = new JTextField();

        //rendu plus jolie du texte
        jtreponse.setPreferredSize(new Dimension(100, 400));
        jlsolution.setVisible(false);
        jtreponse.setFont(new Font("Bold", Font.BOLD, 200));
        jtreponse.setForeground(new Color(117, 137, 191));

        //rendu plus jolie des boutons
        jbverification.setPreferredSize(new Dimension(250, 100));
        jbautre.setPreferredSize(new Dimension(250, 100));
        jbsolution.setPreferredSize(new Dimension(250, 100));
        jbverification.setBackground(new Color(255, 255, 176));
        jbautre.setBackground(new Color(204, 236, 239));
        jbsolution.setBackground(new Color(224, 243, 176));

        setLayout(new BorderLayout());
        add(jlsolution, BorderLayout.SOUTH);
        add(jbsolution, BorderLayout.CENTER);
        add(jbverification, BorderLayout.WEST);
        add(jbautre, BorderLayout.EAST);
        add(jtreponse, BorderLayout.NORTH);
    }

}
