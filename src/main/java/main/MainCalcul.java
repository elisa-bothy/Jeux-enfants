/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package main;

import entities.Calcul;
import graphics.CalculFrame;

/**
 *
 * @author Le J c'est le S
 */
    
public class MainCalcul {
    
        public static void main(String[] args) {
            // génère la partie graphique 
            CalculFrame frame = new CalculFrame();
            // test de la fonction aléatoire 
            Calcul calcul = new Calcul();
            System.out.println(calcul.nombreAleatoire());
            System.out.println(calcul.equationAleatoire());
}  
    }

   


