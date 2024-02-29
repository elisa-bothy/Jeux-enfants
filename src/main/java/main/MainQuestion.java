/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dao.QuestionDao;
import entities.Question;
import graphics.QuestionFrame;

/**
 *
 * @author Valentina Sarais
 */
public class MainQuestion {
    
    public static void main(String[] args) {
        
        //QuestionFrame questJeu = new QuestionFrame();
       
        QuestionDao QDao = new QuestionDao();
        Question q = QDao.readEnonce(1);
        System.out.println(q);
        
        QuestionDao RDao = new QuestionDao();
        Question r = QDao.readReponse(1);
        System.out.println(r);
       
    }
    
    
}
