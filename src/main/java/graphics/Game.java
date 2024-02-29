package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Elisa BOTHY
 */
public class Game extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    JPanel south;
    JMenuBar jmb;
    JMenuItem bienvenue, ardoise, math, question, administration, needHelp;
    JMenu help, file;
    JButton connexion;//pour se connecter à l'administratif
    JTabbedPane tabbedPane;
    AccueilFrame jpAccueil;
    JPanel jpCalcul;
    JPanel jpQuestion;
    JPanel jpTabGraphique;
    AdministrationFrame jpAdministration;//pas visible tant que le code donné n'est pas le bon
    
    public Game(){
        south = new JPanel();
        jpAccueil = new AccueilFrame();
        jpCalcul = new JPanel();
        jpQuestion = new JPanel();
        jpTabGraphique = new JPanel();
        jpAdministration = new AdministrationFrame();
        connexion = new JButton("Connectez vous");
        
        //ajout du menu
        jmb = new JMenuBar();
        bienvenue = new JMenuItem("Bienvenue", KeyEvent.VK_B);
        ardoise = new JMenuItem("Ardoise Magique", KeyEvent.VK_A);
        math = new JMenuItem("Math'Adore", KeyEvent.VK_M);
        question = new JMenuItem("Le Savez vous ?", KeyEvent.VK_S);
        administration = new JMenuItem("Administration", KeyEvent.VK_D);
        needHelp = new JMenuItem("Besoin D'aide?", KeyEvent.VK_B);
        file = new JMenu("Onglets");
        help = new JMenu("Aide");

        //ajout des panels aux onglets 
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Bienvenue", jpAccueil);
        tabbedPane.addTab("Ardoise magique", jpTabGraphique);
        tabbedPane.addTab("Math'Adore", jpCalcul);
        tabbedPane.addTab("Le Savez vous ?", jpQuestion);

        String password = lectureCode();
        initGui(); // Créer l'interface graphique
        initEvents(password); //Créer les évênements 

        //empêche d'agrandir la fenêtre
        this.setResizable(false);
        //agrandir la fenêtre avec ce qui la contient
        this.pack();
        //centrer fenêtre
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //rendre visible la fenêtre
        this.setVisible(true);
    }


    private void initGui() {
        //menu
        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_H);
        bienvenue.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_B, 
                        InputEvent.CTRL_DOWN_MASK
                )
        );
        ardoise.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_A, 
                        InputEvent.CTRL_DOWN_MASK
                )
        );
        math.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_M, 
                        InputEvent.CTRL_DOWN_MASK
                )
        );
        question.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_L, 
                        InputEvent.CTRL_DOWN_MASK
                )
        );
        administration.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_D, 
                        InputEvent.CTRL_DOWN_MASK
                )
        );
        
        
        //taille sur les panels des onglets
        jpAccueil.setPreferredSize(new Dimension(1300, 600));
        jpTabGraphique.setPreferredSize(new Dimension(1300, 600));
        jpCalcul.setPreferredSize(new Dimension(1300, 600));
        jpQuestion.setPreferredSize(new Dimension(1300, 600));
        jpAdministration.setPreferredSize(new Dimension(1300, 600));
    
        // Ajout d'un ChangeListener pour détecter le changement d'onglet
        tabbedPane.addChangeListener(changeEvent -> {
        // Rendre visible seulement l'onglet actuellement sélectionné
        int selectedIndex = tabbedPane.getSelectedIndex();
        jpAccueil.setVisible(selectedIndex == 0);
        jpTabGraphique.setVisible(selectedIndex == 1);
        jpCalcul.setVisible(selectedIndex == 2);
        jpQuestion.setVisible(selectedIndex == 3);
        jpAdministration.setVisible(selectedIndex == 4);
    });
    file.add(bienvenue);
    file.add(ardoise);
    file.add(math);
    file.add(question);
    file.add(administration);
    help.add(needHelp);
    jmb.add(file);
    jmb.add(help);
    this.setJMenuBar(jmb);
    // disposition pour la JFrame principale
    this.setLayout(new BorderLayout());

    // Ajout du JTabbedPane à la JFrame principale
    this.add(tabbedPane, BorderLayout.NORTH);
    south.add(connexion);
    this.add(south, BorderLayout.SOUTH);
}


   private void initEvents(String c) {
        bienvenue.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(0);
        });
        ardoise.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(1);
        });
        math.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(2);
        });
        question.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(3);
        });
        administration.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(4);
        });
        connexion.addActionListener((ActionEvent ae) -> {
            String code = JOptionPane.showInputDialog(
                    null,
                    "Entrez votre mot de passe",
                    "Connexion",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (code != null && code.equals(c)) {
                tabbedPane.addTab("Administration", jpAdministration);
                connexion.setVisible(false);
                System.out.println("Admin tab visibility set to true."); 
            }
        });
        needHelp.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Vous êtes sur une interface de jeu. Appuyer sur des onglets pour jouer. ",
                    "Aide",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        bienvenue.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(0);
        });
        ardoise.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(1);
        });
        math.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(2);
        });
        question.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(3);
        });
        administration.addActionListener((ActionEvent ae) -> {
            tabbedPane.setSelectedIndex(4);
        });
}

    
    //création du password dans un fichier => FAIT dc supprimer

    private String lectureCode() {
        String p = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            is = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("texts/code.dat");
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            p = br.readLine();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichier non trouvé : " + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return p;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {}
}
