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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Valentina Sarais
 */
public class QuestionFrame extends JFrame {

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

        this.getContentPane().add(questRepPanel, BorderLayout.NORTH);
        this.getContentPane().add(repText, BorderLayout.CENTER);
        this.getContentPane().add(btnPanel, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    private void initEvents() {
        // Ajout de l'écouteur pour le bouton de vérification
        verif.addActionListener((ActionEvent e) -> {
            verifierReponse();
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
}


