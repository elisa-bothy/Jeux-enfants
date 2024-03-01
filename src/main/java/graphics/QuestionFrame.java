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
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        repText = new JTextField(255);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        repText.setBorder(border);
        verif = new JButton("VERIFIER");
        solut = new JButton("SOLUTION");
        autreQuest = new JButton("AUTRE QUESTION");

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
        // Récupérer la réponse saisie par l'utilisateur
        String reponseUtilisateur = repText.getText();

        // Récupérer la question actuelle affichée dans le libellé
        String enonceQuestion = questLabel.getText();

        // Récupérer la question correspondant à l'énoncé depuis la base de données
        Question question = questRandom.getQuestionByEnonce(enonceQuestion);

        // Vérifier si la réponse de l'utilisateur est correcte
        boolean reponseCorrecte = questRandom.verifierReponseUtilisateur(question.getId_question(), reponseUtilisateur);

        // Afficher un message à l'utilisateur en fonction du résultat de la vérification
        if (reponseCorrecte) {
            JOptionPane.showMessageDialog(this, "Bonne réponse !");
        } else {
            JOptionPane.showMessageDialog(this, "Mauvaise réponse !");
        }
    }

    private void afficherSolution() {
        // Récupérer la question aléatoire en cours
        String enonceQuestion = questLabel.getText();

        // Récupérer la question complète (y compris la réponse) associée à l'énoncé de la question aléatoire
        Question questionAvecReponse = questRandom.getQuestionByEnonce(enonceQuestion);

        // Afficher la réponse correcte dans une boîte de dialogue
        if (questionAvecReponse != null) {
            String reponseCorrecte = questionAvecReponse.getReponse();
            JOptionPane.showMessageDialog(this, "La réponse correcte est : " + reponseCorrecte, "Solution", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Impossible de récupérer la réponse correcte.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
