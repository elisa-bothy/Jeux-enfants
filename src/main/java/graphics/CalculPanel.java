/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Le J c'est le S
 */
public class CalculPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    
    JLabel equation;

    public CalculPanel() {
        
        this.setLayout(new GridLayout(1, 0));
        this.setPreferredSize(new Dimension(400, 500));
        
        equation = new JLabel("1 + 1",JLabel.CENTER);
        
        add(equation);

    }
    
}
