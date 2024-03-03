/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package graphics;


/**
 *
 * @author Le J c'est le S
 */
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Popup {
    private static final ImageIcon herbert = new ImageIcon(Game.class.getResource("/images/herbert.png"));

    public static void afficherMessage(String message, String title, int messageType) {
       JOptionPane.showMessageDialog(null, message, title, messageType, herbert);
    }
    
}
