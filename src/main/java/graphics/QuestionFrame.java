/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    public QuestionFrame() {
        
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
    }

    private void initGui() {
         
        this.setPreferredSize(new Dimension(600, 400));
       
        
        this.getContentPane().add(questRepPanel, BorderLayout.NORTH);
        this.getContentPane().add(repText, BorderLayout.CENTER);
        this.getContentPane().add(btnPanel, BorderLayout.SOUTH);
          
        //Centre avec le null dans les paranthèse
        this.setLocationRelativeTo(null);
        this.pack();
        // Indiquer le comportement de la fenêtre à la fermeture
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Afficher la fenêtre
        this.setVisible(true);
        
    }

}
