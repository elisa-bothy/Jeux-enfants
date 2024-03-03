/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

/**
 *
 * @author Le J c'est le S
 */
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Popup {

    private static final ImageIcon herbert = new ImageIcon(Game.class.getResource("/images/herbert.png"));

    public static void afficherMessage(String message, String title, int messageType) {
        JLabel jl = new JLabel(message);
        jl.setForeground(new Color(117, 137, 191));
        jl.setFont(new Font("Bold", Font.BOLD, 30));
        JOptionPane.showMessageDialog(null, jl, title, messageType, herbert);
    }

}
