/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package graphics;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Le J c'est le S
 */
public class ResultatPanel extends JPanel{
    private JLabel jlsolution;
    private JButton jbsolution,jbverification, jbautre;
    private JTextField jtreponse;

    public ResultatPanel() {
        
        jlsolution = new JLabel("6");
        jbsolution = new JButton("Solution");
        jbverification = new JButton("Verification");
        jbautre = new JButton("Autre calcule");
        jtreponse = new JTextField();
        
        setLayout(new BorderLayout());
        
        add(jlsolution, BorderLayout.SOUTH);
        add(jbsolution, BorderLayout.CENTER);
        add(jbverification, BorderLayout.WEST);
        add(jbautre, BorderLayout.EAST);
        add(jtreponse, BorderLayout.NORTH);
    }
    
    
}
