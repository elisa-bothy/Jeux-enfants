/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author Valentina Sarais
 */
public class Question {
    private Integer id_question;
    private String enonce;
    private String reponse;
    private ArrayList <Question> questions;

    public Question() {
    }

    public Integer getId_question() {
        return id_question;
    }

    public String getEnonce() {
        return enonce;
    }

    public String getReponse() {
        return reponse;
    }

    public ArrayList <Question> getQuestions() {
        return questions;
    }

    public void setId_question(Integer id_question) {
        this.id_question = id_question;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setQuestions(ArrayList <Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Question{");
        sb.append("id_question=").append(id_question);
        sb.append(", enonce=").append(enonce);
        sb.append(", reponse=").append(reponse);
        sb.append(", questions=").append(questions);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}