package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Herbert Caffarel
 */
public class Game extends JFrame {
    JPanel south;
    JTabbedPane tabGraphique;
    JTabbedPane calcul;
    JTabbedPane question;
    JTabbedPane administration;//pas visible tant que le code donné n'est pas le bon
    JButton connexion;//pour se connecter à l'administratif
    JPanel jpCalcul;
    JPanel jpQuestion;
    JPanel jpTabGraphique;
    JPanel jpAdministration;
    public Game(){
        south = new JPanel();
        jpCalcul = new JPanel();
        jpQuestion = new JPanel();
        jpTabGraphique = new JPanel();
        jpAdministration = new JPanel();
        tabGraphique = new JTabbedPane();
        calcul = new JTabbedPane();
        question = new JTabbedPane();
        administration = new JTabbedPane();
        connexion = new JButton("Connectez vous");
        
        //ajout des panels aux onglets 
        tabGraphique.addTab("Ardoise magique", jpTabGraphique); 
        calcul.addTab("Math'Adore", jpCalcul); 
        question.addTab("Le Savez vous ?", jpQuestion);  
        administration.addTab("Administration", jpAdministration); 
        
        creationCode();
        initGui();
        initEvents();
        
        //empêche d'agrandir la fenêtre
        this.setResizable(false);
        //agrandir la fenêtre avec ce qui la contient
        this.pack();
        //centrer fenêtre
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void initGui() {
        jpTabGraphique.setPreferredSize(new Dimension(1300, 300));
        jpCalcul.setPreferredSize(new Dimension(650, 200));
        jpQuestion.setPreferredSize(new Dimension(650, 200));
        jpAdministration.setPreferredSize(new Dimension(550, 100));
         // disposition pour la JFrame principale
        this.setLayout(new BorderLayout());

        // Ajout des onglets à la JFrame principale
        this.add(tabGraphique, BorderLayout.NORTH);
        this.add(calcul, BorderLayout.WEST);
        this.add(question, BorderLayout.EAST);
        south.add(administration);
        south.add(connexion);
        this.add(south, BorderLayout.SOUTH);
    }

    private void initEvents() {
        connexion.addActionListener((ActionEvent ae) -> {
                String code = JOptionPane.showInputDialog(
                null, 
                "Entrez votre mot de passe", 
                "Connexion", 
                JOptionPane.QUESTION_MESSAGE                
                );
            });
    }
    //création du password dans un fichier
    private void creationCode() {
        DataOutputStream out;
 
        try {
            out = new DataOutputStream(
                    new BufferedOutputStream(
                        new FileOutputStream("code.dat")));
            out.writeUTF("password");
            out.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
    }
    
}
