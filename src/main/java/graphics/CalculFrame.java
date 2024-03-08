/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import entities.Calcul;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Le J c'est le S
 */
public final class CalculFrame extends JPanel {

    private static final long serialVersionUID = 1L;

    private final CalculPanel calcul;
    private final ResultatPanel res;

    // CREATION DE LA FENETRE --------------------------------------------------
    public CalculFrame(Game game) {

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
        initEvents(game);
    } // -----------------------------------------------------------------------

    // RANGER LES BOITES 
    private void initGui() {
        Container contentPane = this;
        contentPane.setLayout(new BorderLayout());
        contentPane.add(res, BorderLayout.EAST);
        contentPane.add(calcul, BorderLayout.WEST);
    }

    // GERER LES EVENTS
    private void initEvents(Game game) {
        res.getJbautre().addActionListener((ae) -> {
            getLabel(game);
            res.getJtreponse().setText("");
        });
        res.getJtreponse().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                String text = res.getJtreponse().getText();
                boolean containsNonDigit = false;

                // Parcourt chaque caractère du texte
                for (char ch : text.toCharArray()) {
                    // Vérifie si le caractère n'est pas un chiffre
                    if (!Character.isDigit(ch)) {
                        containsNonDigit = true;
                        break;
                    }
                }

                // Si la chaîne contient au moins un caractère non numérique, effacez le texte
                if (containsNonDigit) {
                    res.getJtreponse().setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }
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
    private void getLabel(Game game) {
        System.out.println("level = " + game.getLevel());
        // EQUATION     
        Calcul alea = new Calcul();
        // genère 2 nombre aléatoire 
        int a = alea.nombreAleatoire(game.getLevel());
        int b = alea.nombreAleatoire(game.getLevel());
        int d = 0;

        //genérer l'opérande aléatoire 
        String operande = alea.operandeAleatoire(game.getLevel());

        // genérer une équation aléatoire 
        if (game.getLevel() == 1) {
            if (operande.equals("+")) {
                d = a + b;

            } else {
                do {
                    b = alea.nombreAleatoire(game.getLevel());
                } while (a < b);
                d = a - b;
            }
        } else {
            switch (operande) {
                case "+":
                    d = a + b;
                    break;
                case "-":
                    d = a - b;
                    break;
                case "x":
                    d = a * b;
                    break;
                case "/":
                    d = a / b;
                    break;
                default:
                    d = a + b;
                    break;
            }
        }
        String calculation = a + " " + operande + " " + b;

        // Affectation Label equation (panel result label equation)
        calcul.equation.setText(calculation);
        // Affectation Label jlsolution dans le panel result 
        res.getJlsolution().setText(String.valueOf(d));
    }

    // Fonction de comparaison 
    private boolean comparaison() {
        try {
            String solutionText = res.getJlsolution().getText().trim();
            String propositionText = res.getJtreponse().getText().trim();

            // Vérifier si la solution est vide
            if (solutionText.isEmpty()) {
                return false;
            }

            int solution = Integer.parseInt(solutionText);
            int proposition = Integer.parseInt(propositionText);

            return solution == proposition;
        } catch (NumberFormatException e) {
            // Gérer l'exception si les chaînes ne peuvent pas être converties en entiers
            return false;
        }
    }
}
