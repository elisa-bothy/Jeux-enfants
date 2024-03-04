/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import dao.QuestionDao;
import entities.Question;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Collection;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Elisa Bothy
 */
public class AdministrationFrame extends JPanel {

    private static final long serialVersionUID = 1L;
    JLabel welcome;
    JPanel radios;
    JRadioButton jrb1;
    JRadioButton jrb2;
    ButtonGroup niveaux;
    JButton modification;
    JButton creation;
    JButton supprimer;
    JButton enregistrer;
    JButton modifier;
    JButton retour;
    JComboBox jcb;
    JTextField question;
    JTextField reponse;
    //récupérer les questions
    QuestionDao qdao;
    Collection<Question> questionList;
    int nbQuestion;
    String[] questionStatements;
    JPanel north;
    JPanel south;
    JPanel east;
    JPanel west;
    JPanel center;
    private int level;

    public AdministrationFrame() {
        welcome = new JLabel();
        // Boutons radio
        radios = new JPanel();
        north = new JPanel();
        south = new JPanel();
        east = new JPanel();
        west = new JPanel();
        center = new JPanel();
        jrb1 = new JRadioButton("niveau 1");
        jrb2 = new JRadioButton("niveau 2");
        niveaux = new ButtonGroup();
        //button de modification ou de création
        modification = new JButton("Modification");
        creation = new JButton("Création");
        //question choisi dans liste déroulante
        question = new JTextField();
        reponse = new JTextField();
        enregistrer = new JButton("Enregistrer");
        supprimer = new JButton("Supprimer");
        modifier = new JButton("Modifier");
        retour = new JButton("Retour");

        //récupération des questions
        qdao = new QuestionDao();
        questionList = qdao.list();
        nbQuestion = qdao.count();

        // Création d'un tableau pour stocker les énoncés des questions
        questionStatements = new String[nbQuestion];

        // Boucle pour récupérer les énoncés des questions
        int index = 0;
        for (Question q : questionList) {
            questionStatements[index] = q.getEnonce();
            index++;
        }

        //liste déroulante
        jcb = new JComboBox(questionStatements);

        initGui();// Créer l'interface de JPanel
        initEvents();
    }

    private void initGui() {
        this.setLayout(new BorderLayout());
        niveaux.add(jrb1);
        niveaux.add(jrb2);
        radios.add(jrb1);
        radios.add(jrb2);
        reponse.setPreferredSize(new Dimension(600, 30));
        question.setPreferredSize(new Dimension(600, 30));
        south.add(radios);
        center.add(modification);
        west.add(jcb);
        center.add(creation);
        center.add(question);
        center.add(reponse);
        north.add(enregistrer);
        north.add(supprimer);
        north.add(modifier);
        north.add(retour);
        this.add(south, BorderLayout.SOUTH);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        this.add(center, BorderLayout.CENTER);
        this.add(north, BorderLayout.NORTH);
    }

    private void initEvents() {
        jcb.setVisible(false);
        question.setVisible(false);
        enregistrer.setVisible(false);
        modifier.setVisible(false);
        reponse.setVisible(false);
        supprimer.setVisible(false);
        retour.setVisible(false);
        modification.addActionListener((ActionEvent ae) -> {
            radios.setVisible(false);
            modification.setVisible(false);
            creation.setVisible(false);
            jcb.setVisible(true);
            modifier.setVisible(true);
            question.setVisible(true);
            reponse.setVisible(true);
            retour.setVisible(true);
        });
        creation.addActionListener((ActionEvent ae) -> {
            radios.setVisible(false);
            modification.setVisible(false);
            creation.setVisible(false);
            enregistrer.setVisible(true);
            question.setVisible(true);
            reponse.setVisible(true);
            retour.setVisible(true);
        });
        retour.addActionListener((ActionEvent ae) -> {
            enregistrer.setVisible(false);
            reponse.setVisible(false);
            retour.setVisible(false);
            radios.setVisible(true);
            modification.setVisible(true);
            creation.setVisible(true);
            jcb.setVisible(false);
            modifier.setVisible(false);
            question.setVisible(false);
            reponse.setVisible(false);
            supprimer.setVisible(false);
            question.setText("");
            reponse.setText("");
        });
        supprimer.addActionListener((ActionEvent ae) -> {
            radios.setVisible(true);
            modification.setVisible(true);
            creation.setVisible(true);
            jcb.setVisible(false);
            modifier.setVisible(false);
            question.setVisible(false);
            reponse.setVisible(false);
            supprimer.setVisible(false);
            retour.setVisible(false);
            int selectedIndex = jcb.getSelectedIndex();
            qdao.delete(selectedIndex+1);
            question.setText("");
            reponse.setText("");
        });
        modifier.addActionListener((ActionEvent ae) -> {
            radios.setVisible(true);
            modification.setVisible(true);
            creation.setVisible(true);
            jcb.setVisible(false);
            modifier.setVisible(false);
            question.setVisible(false);
            reponse.setVisible(false);
            supprimer.setVisible(false);
            retour.setVisible(false);
            // Récupérer l'index de la question sélectionnée dans le JComboBox
            int selectedIndex = jcb.getSelectedIndex();
            // Récupérer la question sélectionnée dans le JComboBox
            Question selectedQuestion = questionList.toArray(new Question[0])[selectedIndex];
            String questionEnonce = question.getText();
            String questionReponse = reponse.getText();
            qdao.updateEnonce(selectedQuestion, questionEnonce, questionReponse);
            question.setText("");
            reponse.setText("");
        });
        enregistrer.addActionListener((ActionEvent ae) -> {
            radios.setVisible(true);
            modification.setVisible(true);
            creation.setVisible(true);
            enregistrer.setVisible(false);
            question.setVisible(false);
            reponse.setVisible(false);
            retour.setVisible(false);
            String questionEnonce = question.getText();
            String questionReponse = reponse.getText();
            qdao.create(questionEnonce, questionReponse);
            question.setText("");
            reponse.setText("");
        });
        jcb.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                supprimer.setVisible(true);
            }
        });
    }
}
