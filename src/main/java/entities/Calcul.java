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

    public Calcul() {
    }
    
    // Méthode pour générer un nombre aléatoire
    public int nombreAleatoire() {
        Random random = new Random();
        int nbAlea = random.nextInt(10) + 1;
        return nbAlea;
    }
 
    // Méthode pour générer une opération aléatoire (+ ou -)
    public String operandeAleatoire() {
        // Choix aléatoire entre 0 et 1
        Random random = new Random();
        int choix = random.nextInt(2);
        if (choix == 0) {
            return "+"; // Addition
        } else {
            return "-"; // Soustraction
        }
    }
    
    // Méthode pour retourner une équation aléatoire avec une string 
    public String equationAleatoire() {
        String equation;
        int nombre1 = this.nombreAleatoire();
        int nombre2 = this.nombreAleatoire();
        String operande = this.operandeAleatoire();
        
        equation = String.valueOf(nombre1)+operande+String.valueOf(nombre2);
        
        return equation;
    }
    
    // Méthode pour retrouver le résultat à partir d'une string de nombre 
    public int resultat(String equation){
        int resultat = 0;
        
        return resultat;
    }

}
