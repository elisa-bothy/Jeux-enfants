/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package graphics;


import entities.Calcul;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Le J c'est le S
 */
public class CalculFrame extends JFrame{
    
    private CalculPanel calcul;
    private ResultatPanel res;

    // CREATION DE LA FENETRE --------------------------------------------------
    public CalculFrame() {
        super("CALCULONS");

        // Ajouter les panels secondaires 
        calcul = new CalculPanel();
        res = new ResultatPanel();
        
        // Ajouter à la frame les Jpanel
        
        add(calcul);
        add(res);
        
        // Ajuster les blocs entre eux 

        initGui();
        events();
        
        // Laisser la fenêttre s'auto-dimensionner par rapport à son contenu
        this.pack();
        this.setLocationRelativeTo(null);
        // Indiquer le comportement de la fenêtre à la fermeture
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Afficher la fenêtre
        this.setVisible(true);
    } // -----------------------------------------------------------------------
    
        // RANGER LES BOITES 
        private void initGui() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        contentPane.add(res, BorderLayout.CENTER);
        contentPane.add(calcul, BorderLayout.WEST);
        }
        
        // GERER LES EVENTS
        private void events (){
            res.getJbautre().addActionListener((ae) -> {
                getLabel();
                //res.getJlsolution().setVisible(false);
            });
                
            res.getJbverification().addActionListener((ae) -> {
                if (comparaison() == true){
                    Popup.afficherMessage("Bravo Jeune Padawan !", "Vérification", HEIGHT);}

                else {Popup.afficherMessage("Ce n'est pas ça essaie encore", "Vérification", HEIGHT);
                   }
            });

            res.getJbsolution().addActionListener((ae) -> {
                String text = "La solution de " + calcul.equation.getText() + " est " + res.getJlsolution().getText();
                Popup.afficherMessage(text, "La solution", JOptionPane.INFORMATION_MESSAGE);
            });

        }
        
        
        // FONCTION SUPPORT ----------------------------------------------------        
            
        // Fonction générant les labels jlsolution et equation 
        
        private void getLabel() {
        // EQUATION     
            Calcul alea = new Calcul();
            // genère 2 nombre aléatoire 
            int a = alea.nombreAleatoire();
            int b = alea.nombreAleatoire();
            
            //genérer l'opérande aléatoire 
           String operande = alea.operandeAleatoire();
          
           // genérer une équation aléatoire 
           int d;
           String equa;
           if (operande == "+"){
               d = a + b;
              
            } else {
                do {
                    b = alea.nombreAleatoire();
                } while (a < b);
                d = a - b;
            }
            String calculation = a + " " + operande + " " + b;
           
           // Affectation Label equation (panel result label equation)
           calcul.equation.setText(calculation);
           // Affectation Label jlsolution dans le panel result 
           res.getJlsolution().setText(String.valueOf(d));
           }
            
        // Fonction de comparaison 
        
        private boolean comparaison() {
            int solution;
            int proposition;

            solution = Integer.parseInt(res.getJlsolution().getText().trim());
            proposition = Integer.parseInt(res.getJtreponse().getText().trim());

        return solution == proposition;
        }
        
}
       