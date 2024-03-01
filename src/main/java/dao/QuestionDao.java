/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entities.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Valentina Sarais
 */
public class QuestionDao {

    Connection connection;

    public QuestionDao() {
        connection = MariadbConnection.getInstance();
    }

    public Question readEnonce(Integer id) {
        Question objQ = null;
        String sql = "SELECT * FROM question WHERE id_question=?";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                objQ = new Question();
                objQ.setId_question(rs.getInt("id_question"));
                objQ.setEnonce(rs.getString("enonce"));

            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la lecture question : " + ex.getMessage());
        }
        return objQ;
    }
    
    public Question readReponses(Integer id) {
        Question objR = null;
        String sql = "SELECT * FROM question WHERE id_question=?";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                objR = new Question();
                objR.setId_question(rs.getInt("id_question"));
                objR.setReponse(rs.getString("reponse"));

            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la lecture reponse : " + ex.getMessage());
        }
        return objR;
    }
    
    public Question readRandomQuestion() {
        Question randomQuestion = null;
        String sql = "SELECT * FROM question";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Question> questionList = new ArrayList<>();

            // Récupérer toutes les questions de la base de données
            while (rs.next()) {
                Question question = new Question();
                question.setId_question(rs.getInt("id_question"));
                question.setEnonce(rs.getString("enonce"));
                question.setReponse(rs.getString("reponse"));
                questionList.add(question);
            }

            // Choisir une question aléatoire
            if (!questionList.isEmpty()) {
                Random random = new Random();
                int index = random.nextInt(questionList.size());
                randomQuestion = questionList.get(index);
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la lecture d'une question aléatoire : " + ex.getMessage());
        }
        
        return randomQuestion;
    }
    
    public Question getQuestionByEnonce(String enonce) {
    Question question = null;
    String sql = "SELECT * FROM question WHERE enonce = ?";
    try {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, enonce);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            question = new Question();
            question.setId_question(rs.getInt("id_question"));
            question.setEnonce(rs.getString("enonce"));
            question.setReponse(rs.getString("reponse"));
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de la question par énoncé : " + ex.getMessage());
    }
    return question;
}
 public boolean verifierReponseUtilisateur(int idQuestion, String reponseUtilisateur) {
    boolean reponseCorrecte = false;
    String sql = "SELECT reponse FROM question WHERE id_question = ?";
    try {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, idQuestion);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String reponseCorrecteDB = rs.getString("reponse");
            reponseCorrecteDB = reponseCorrecteDB.trim(); // Supprimer les espaces en début et fin de chaîne
            reponseUtilisateur = reponseUtilisateur.trim();
            // Vérifier si la réponse de l'utilisateur correspond à la réponse correcte (insensible à la casse)
            reponseCorrecte = reponseCorrecteDB.equalsIgnoreCase(reponseUtilisateur);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la vérification de la réponse de l'utilisateur : " + ex.getMessage());
    }
    return reponseCorrecte;
}

/* public String trouverSolution(String enonce) {
    String solution = null;
    String sql = "SELECT reponse FROM question WHERE enonce = ?";
    try {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, enonce);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            solution = rs.getString("reponse");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'affichage de la solution : " + ex.getMessage());
    }
    return solution;
}*/
  public int count(){
        int count = 0;
        String sql = "SELECT COUNT(*) AS c FROM person";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.first()){
                count = rs.getInt("c");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors du comptage :" + ex.getMessage());
        }
        return count;
    }
    
    public Collection<Question> list(){
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Person p = new Person();
                p.setId_person(rs.getInt("id_person"));
                p.setLogin(rs.getString("login"));
                p.setPwd(rs.getString("pwd"));
                p.setId_role(rs.getLong("id_role"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors du listage :" + ex.getMessage());
        }
        return list;
    }


    
}


