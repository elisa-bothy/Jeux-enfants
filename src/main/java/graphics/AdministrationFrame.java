/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
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
    JComboBox jcb;
    JTextField question;
    //à modifier avec les questions
    String[] choices= {
            "Toulouse"," Paris", "Marseille", "Lyon", "Bordeaux", "Strasbourg"
        };

    public AdministrationFrame() {
        welcome = new JLabel();
        // Boutons radio
        radios = new JPanel();
        jrb1 = new JRadioButton("niveau 1");
        jrb2 = new JRadioButton("niveau 2");
        niveaux = new ButtonGroup();
        //button de modification ou de création
        modification = new JButton("Modification");
        creation = new JButton("Création");
        //liste déroulante
        jcb = new JComboBox(choices);
        //question choisi dans liste déroulante
        question = new JTextField();
        enregistrer = new JButton("Enregistrer");
        supprimer = new JButton("Supprimer");
        modifier = new JButton("Modifier");
        
        initGui();// Créer l'interface de JPanel
        initEvents();
        
    }

    private void initGui() {
        niveaux.add(jrb1);
        niveaux.add(jrb2);
        radios.add(jrb1);
        radios.add(jrb2);
        this.add(radios);
        this.add(supprimer);
        this.add(modification);
        this.add(creation);
        this.add(jcb);
        this.add(question);
        this.add(enregistrer);
        this.add(modifier);       
    }

    private void initEvents() {
        jcb.setVisible(false);
        question.setVisible(false);
        enregistrer.setVisible(false);
        modifier.setVisible(false);
        modification.addActionListener((ActionEvent ae) -> {
            supprimer.setVisible(false);
            radios.setVisible(false);
            modification.setVisible(false);
            creation.setVisible(false);
            jcb.setVisible(true);
            modifier.setVisible(true);
            question.setVisible(true);
        });
        creation.addActionListener((ActionEvent ae) -> {
            supprimer.setVisible(false);
            radios.setVisible(false);
            modification.setVisible(false);
            creation.setVisible(false);
            enregistrer.setVisible(true);
            question.setVisible(true);
        });
        supprimer.addActionListener((ActionEvent ae) -> {
            
        });
        modifier.addActionListener((ActionEvent ae) -> {
            supprimer.setVisible(true);
            radios.setVisible(true);
            modification.setVisible(true);
            creation.setVisible(true);
            jcb.setVisible(false);
            modifier.setVisible(false);
            question.setVisible(false);
        });
        enregistrer.addActionListener((ActionEvent ae) -> {
            supprimer.setVisible(true);
            radios.setVisible(true);
            modification.setVisible(true);
            creation.setVisible(true);
            enregistrer.setVisible(false);
            question.setVisible(false);
        });
        jcb.addItemListener((e)->{
            if(e.getStateChange() == ItemEvent.DESELECTED){
                System.out.println("déselection");
             
            }else{
                System.out.println("sélection");
                System.out.println(((JComboBox)(e.getSource()))
                        .getSelectedItem());
            }
        });
    }
    
}
