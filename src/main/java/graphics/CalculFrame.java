/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import entities.Calcul;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Le J c'est le S
 */
public class CalculFrame extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private final CalculPanel calcul;
    private final ResultatPanel res;

    // CREATION DE LA FENETRE --------------------------------------------------
    public CalculFrame() {

        // Ajouter les panels secondaires 
        calcul = new CalculPanel();
        res = new ResultatPanel();
        
        // Ajout de couleurs
        calcul.setBackground(new Color(254, 235, 201));
        res.setBackground(new Color(254, 235, 201));
        // Ajouter à la frame les Jpanel
        this.add(calcul);
        this.add(res);
        this.setBackground(new Color(254, 235, 201));

        // Ajuster les blocs entre eux 
        initGui();
        events();
        
    } // -----------------------------------------------------------------------

    // RANGER LES BOITES 
    private void initGui() {
        Container contentPane = this;
        contentPane.setLayout(new BorderLayout());
        contentPane.add(res, BorderLayout.EAST);
        contentPane.add(calcul, BorderLayout.WEST);
    }

    // GERER LES EVENTS
    private void events() {
        res.getJbautre().addActionListener((ae) -> {
            getLabel();
            res.getJtreponse().setText("");
        });
        
        res.getJbverification().addActionListener((ae) -> {
            if (comparaison() == true) {
                Popup.afficherMessage("Bravo Jeune Padawan !", "Vérification", HEIGHT);
            } else {
                Popup.afficherMessage(
                        "Ce n'est pas ça, essaie encore !", 
                        "Vérification", 
                        HEIGHT
                );
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
        if ("+".equals(operande)) {
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
