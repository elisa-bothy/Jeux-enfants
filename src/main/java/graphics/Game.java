package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Elisa BOTHY
 */
public class Game extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    static ImageIcon herbert = new ImageIcon(Game.class.getResource("/images/herbert.png"));
    JPanel south;
    JMenuBar jmb;
    JMenuItem bienvenue, ardoise, math, question, administration, needHelp;
    JMenu help, file;
    JButton connexion;//pour se connecter à l'administratif
    JTabbedPane tabbedPane;
    AccueilFrame jpAccueil;
    CalculFrame jpCalcul;
    QuestionFrame jpQuestion;
    ArdoiseFrame jpTabGraphique;
    AdministrationFrame jpAdministration;//pas visible tant que le code donné n'est pas le bon

    public Game() {
        super("Jouons avec Herbie !");
        this.setIconImage(herbert.getImage()); 
        south = new JPanel();
        jpAccueil = new AccueilFrame();
        jpCalcul = new CalculFrame();
        jpQuestion = new QuestionFrame();
        jpTabGraphique = new ArdoiseFrame();
        jpAdministration = new AdministrationFrame();
        connexion = new JButton("Connectez vous");

        //ajout du menu
        jmb = new JMenuBar();
        bienvenue = new JMenuItem("Bienvenue", KeyEvent.VK_B);
        ardoise = new JMenuItem("Ardoise Magique", KeyEvent.VK_A);
        math = new JMenuItem("Math'Adore", KeyEvent.VK_M);
        question = new JMenuItem("Le Savez vous ?", KeyEvent.VK_S);
        administration = new JMenuItem("Administration", KeyEvent.VK_D);
        needHelp = new JMenuItem("Besoin D'aide?", KeyEvent.VK_N);
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
                        KeyEvent.VK_S,
                        InputEvent.CTRL_DOWN_MASK
                )
        );
        administration.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_D,
                        InputEvent.CTRL_DOWN_MASK
                )
        );
        needHelp.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_N,
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
            tabbedPaneColor();
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
            String passwordString = null;
            JPasswordField pf = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                    null,
                    pf,
                    "Entrez votre mot de passe",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            // Vérifie si l'utilisateur a appuyé sur le bouton OK
            if (option == JOptionPane.OK_OPTION) {
                // Récupère le mot de passe entré par l'utilisateur
                char[] password = pf.getPassword();
                // Convertit le tableau de caractères en une chaîne
                passwordString = new String(password);
            } else {
                System.out.println("Annulé");
            }
            if (passwordString != null && passwordString.equals(c)) {
                tabbedPane.addTab("Administration", jpAdministration);
                connexion.setVisible(false);
            }
        });
        needHelp.addActionListener((ActionEvent ae) -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            JLabel jl = new JLabel();
            jl.setForeground(new Color(117, 137, 191));
            jl.setFont(new Font("Bold", Font.BOLD, 20));
            switch (selectedIndex) {
                case 0:
                    JOptionPane.showMessageDialog(
                            null,
                            "Vous êtes sur une interface de jeu. Appuyer sur des onglets pour jouer. ",
                            "Aide",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    break;
                case 1:
                    jl.setText("<html>Pour jouer, petit enfant, tu peux placer le curseur au centre.<br>"
                            + "Tu peux ensuite choisir la couleur de ton dessin avec les boutons sur la droite, <br>"
                            + "Et tu peux effacer tout ton dessin avec le  bouton sur la gauche <br>"
                            + "Amuse-toi bien !</html>");
                    JOptionPane.showMessageDialog(
                            null,
                            jl,
                            "Aide",
                            JOptionPane.PLAIN_MESSAGE,
                            herbert
                    );
                    break;
                case 2:
                    jl.setText("<html>Pour jouer, petit enfant, tu dois répondre au calcul <br>"
                            + "Réfléchi bien, puis écrit ta réponse <br>"
                            + "et appuie sur le bouton Vérification au bas de la page <br>"
                            + "Si la question est trop dur n'hésite pas à appuyer sur le bouton Solution<br>"
                            + "Tu peux changer de question à tout moment avec le bouton Nouveau Calcul</html>");
                    JOptionPane.showMessageDialog(
                            null,
                            jl,
                            "Aide",
                            JOptionPane.PLAIN_MESSAGE,
                            herbert
                    );
                    break;
                case 3:
                    jl.setText("<html>Pour jouer, petit enfant, tu dois répondre à la question de culture <br>"
                            + "Réfléchi bien, puis écrit ta réponse <br>"
                            + "et appuie sur le bouton Vérifier au bas de la page <br>"
                            + "Si la question est trop dur n'hésite pas à appuyer sur le bouton Solution<br>"
                            + "Tu peux changer de question à tout moment avec le bouton Autre question</html>");
                    JOptionPane.showMessageDialog(
                            null,
                            jl,
                            "Aide",
                            JOptionPane.PLAIN_MESSAGE,
                            herbert
                    );
                    break;
                case 4:
                    JOptionPane.showMessageDialog(
                            null,
                            "Onglet Administration, \n"
                            + "Si vous n'êtes pas un administrateur, \n"
                            + "Veuillez quitter cet onglet",
                            "Aide",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    break;
            }
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
        } finally {
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
    
    private void tabbedPaneColor() {
    int selectedIndex = tabbedPane.getSelectedIndex();
    Color selectedTabColor = null;

    switch (selectedIndex) {
        case 0:
            selectedTabColor = new Color(255, 255, 176);
            tabbedPane.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 176)));
            break;
        case 1:
            selectedTabColor = new Color(204, 236, 239);
            tabbedPane.setBorder(BorderFactory.createLineBorder(new Color(204, 236, 239)));
            break;
        case 2:
            selectedTabColor = new Color(254, 235, 201);
            tabbedPane.setBorder(BorderFactory.createLineBorder(new Color(254, 235, 201)));
            break;
        case 3:
            selectedTabColor = new Color(181, 225, 174);
            tabbedPane.setBorder(BorderFactory.createLineBorder(new Color(181, 225, 174)));
            break;
    }

    if (selectedTabColor != null) {
        //pour définir la couleur de fond de l'onglet sélectionné seulement
        tabbedPane.setBackgroundAt(selectedIndex, selectedTabColor);
    }
}

    @Override
    public void actionPerformed(ActionEvent ae) {
    }
}
