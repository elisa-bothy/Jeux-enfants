/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import dao.QuestionDao;
import entities.Question;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.Normalizer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Valentina Sarais
 */
public class QuestionFrame extends JPanel {

    private static final long serialVersionUID = 1L;
    JPanel questRepPanel, btnPanel;
    JLabel questLabel;
    JTextField repText;
    JButton verif, solut, autreQuest;
    QuestionDao questRandom;
    Question reponseUSer;

    public QuestionFrame() {

        questRandom = new QuestionDao();
        //creation des composants
        questRepPanel = new JPanel();
        btnPanel = new JPanel();
        questLabel = new JLabel("Comment s'appelle la fée de Peter Pan ?");
        //je set la taille de mon questLabel
        questLabel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
        questLabel.setForeground(new Color(72, 181, 163));
        //changement police
        // Création d'une nouvelle police 
        Font nouvellePoliceQuestion = new Font(questLabel.getFont().getName(), Font.BOLD, 25);
        // Définition de la nouvelle police pour le JLabel
        questLabel.setFont(nouvellePoliceQuestion);

        repText = new JTextField(255);
        // je change le font de mon text
        Font nouvellePolice = new Font(repText.getFont().getName(), Font.PLAIN, 46);
        // Définition de la nouvelle police pour le JTextField
        repText.setFont(nouvellePolice);
        repText.setForeground(new Color(117, 137, 191));

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        repText.setBorder(border);
        verif = new JButton("VERIFIER");
        solut = new JButton("SOLUTION");
        autreQuest = new JButton("AUTRE QUESTION");

        //rendu des boutons plus jolie
        verif.setBackground(new Color(255, 255, 176));
        autreQuest.setBackground(new Color(204, 236, 239));
        solut.setBackground(new Color(224, 243, 176));
        verif.setPreferredSize(new Dimension(250, 200));
        autreQuest.setPreferredSize(new Dimension(250, 200));
        solut.setPreferredSize(new Dimension(250, 200));

        // ajout des composants au panneau questRepPanel avec un FlowLayout
        questRepPanel.setLayout(new FlowLayout());
        questRepPanel.add(questLabel);
        questRepPanel.add(repText);

        // ajout des composants au panneau btnPanel
        btnPanel.add(verif);
        btnPanel.add(solut);
        btnPanel.add(autreQuest);

        // positionner les composants
        setLayout(new BorderLayout());
        
        //ajout de couleurs
        questRepPanel.setBackground(new Color(181, 225, 174));
        btnPanel.setBackground(new Color(181, 225, 174));
        this.setBackground(new Color(181, 225, 174));
        
        // Ajout des panneaux à la frame
        add(questRepPanel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);
        
        this.initGui();
        this.afficherQuestRandom();
        this.initEvents();
    }

    private void initGui() {

        this.setPreferredSize(new Dimension(600, 400));

        this.add(questRepPanel, BorderLayout.NORTH);
        this.add(repText, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
    }

    private void initEvents() {
        // Ajout de l'écouteur pour le bouton de vérification
        verif.addActionListener((ActionEvent e) -> {
            verifierReponse();
        });

        solut.addActionListener((ActionEvent e) -> {
            afficherSolution();
        });

        autreQuest.addActionListener((ActionEvent e) -> {
            repText.setText("");
            afficherQuestRandom();
        });

    }

    private void afficherQuestRandom() {
        // Appel à la méthode pour récupérer une question aléatoire depuis la base de données
        Question question = questRandom.readRandomQuestion();

        // Mise à jour du libellé avec la question aléatoire obtenue
        questLabel.setText(question.getEnonce());
    }

    private void verifierReponse() {
        //recup la réponse saisie par l'utilisateur
        String reponseUtilisateur = removeAccents(repText.getText());

        // Récup question actuelle affichée dans le label
        String enonceQuestion = removeAccents(questLabel.getText());

        // Récupérer la question correspondant à l'énoncé depuis la base de données
        Question question = questRandom.getQuestionByEnonce(enonceQuestion);

        // Vérifier si la réponse de l'utilisateur est correcte
        boolean reponseCorrecte = questRandom.verifierReponseUtilisateur(question.getId_question(), reponseUtilisateur);

        // Création du pop-up         
        // Afficher un message à l'utilisateur en fonction du résultat de la vérification
        if (reponseCorrecte) {
            Popup.afficherMessage("Bonne réponse !", "Vérification", HEIGHT);
        } else {
            Popup.afficherMessage("Mauvaise réponse !", "Vérification", HEIGHT);
        }
    }
    
    private String removeAccents(String input) {
        // Remplace les caractères accentués par leurs équivalents non accentués
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private void afficherSolution() {
        // Récupérer la question aléatoire en cours
        String enonceQuestion = questLabel.getText();

        // Récupérer la question complète (y compris la réponse) associée à l'énoncé de la question aléatoire
        Question questionAvecReponse = questRandom.getQuestionByEnonce(enonceQuestion);
        // Afficher la réponse correcte dans une boîte de dialogue
        if (questionAvecReponse != null) {

            String reponseCorrecte = questionAvecReponse.getReponse();
            Popup.afficherMessage("La réponse correcte est : " + reponseCorrecte, "Solution", HEIGHT);
        } else {
            Popup.afficherMessage("Impossible de récupérer la réponse correcte.", "Erreur", HEIGHT);
        }
    }
}
