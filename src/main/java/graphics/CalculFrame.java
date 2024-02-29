/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package graphics;


import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author Le J c'est le S
 */
public class CalculFrame extends JFrame{
    
    private CalculPanel calcul;
    private ResultatPanel resultat;

    // CREATION DE LA FENETRE 
    public CalculFrame() {
        super("CALCULONS");

        // Ajouter les panels secondaires 
        calcul = new CalculPanel();
        resultat = new ResultatPanel();
        //resultat = new ResultatPanel();
        
        // Ajouter à la frame les Jpanel
        
        add(calcul);
        add(resultat);
        
        // Ajuster les blocs entre eux 
        
        initGui();
        
        // Laisser la fenêttre s'auto-dimensionner par rapport à son contenu
        this.pack();
        this.setLocationRelativeTo(null);
        // Indiquer le comportement de la fenêtre à la fermeture
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Afficher la fenêtre
        this.setVisible(true);
    }
        private void initGui() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        contentPane.add(resultat, BorderLayout.CENTER);
        contentPane.add(calcul, BorderLayout.WEST);
    }

}
