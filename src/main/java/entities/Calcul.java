/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entities;

import java.util.Random;

/**
 *
 * @author Le J c'est le S
 */
public class Calcul extends Random {

    private static final long serialVersionUID = 1L;

    public Calcul() {
    }
    
    // Méthode pour générer un nombre aléatoire
    public int nombreAleatoire(int level) {
        if (level==1){
            Random random = new Random();
            int nbAlea = random.nextInt(10) + 1;
            return nbAlea;
        }else{
            Random random = new Random();
            int nbAlea = random.nextInt(1_000) + 1;
            return nbAlea;
        }
    }
 
    // Méthode pour générer une opération aléatoire (+ ou -)
    public String operandeAleatoire(int level) {
        if (level==1){
            // Choix aléatoire entre 0 et 1
            Random random = new Random();
            int choix = random.nextInt(2);
            if (choix == 0) {
                return "+"; // Addition
            } else {
                return "-"; // Soustraction
            }
        }else{
            Random random = new Random();
            int choix = random.nextInt(3);
            switch(choix){
                case 0: 
                    return "+";
                case 1: 
                    return "-";
                case 2:
                    return "x";
                case 3 :
                    return "/";
                default:
                    return "+";
            }
        }
    }
}
