/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Le J c'est le S
 */
public class CalculPanel extends JPanel{
    
    JLabel nombre1, nombre2, operande;

    public CalculPanel() {
        
        this.setLayout(new GridLayout(1, 0));
        this.setPreferredSize(new Dimension(400, 500));
        
        nombre1 = new JLabel("0",JLabel.CENTER);
        operande = new JLabel("0", JLabel.CENTER);
        nombre2 = new JLabel("0",JLabel.CENTER);
        
        add(nombre1);
        add(operande);
        add(nombre2);

    }
    
}
