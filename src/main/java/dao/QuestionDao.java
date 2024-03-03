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
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.first()) {
                objQ = new Question();
                objQ.setId_question(rs.getInt("id_question"));
                objQ.setEnonce(rs.getString("enonce"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la lecture question : " + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(rs, pstmt);
        }
        return objQ;
    }

    public Question readReponses(Integer id) {
        Question objR = null;
        String sql = "SELECT * FROM question WHERE id_question=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.first()) {
                objR = new Question();
                objR.setId_question(rs.getInt("id_question"));
                objR.setReponse(rs.getString("reponse"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la lecture reponse : " + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(rs, pstmt);
        }
        return objR;
    }
    
    public Question readRandomQuestion() {
        Question randomQuestion = null;
        String sql = "SELECT * FROM question";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

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
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(rs, pstmt);
        }

        return randomQuestion;
    }

    public Question getQuestionByEnonce(String enonce) {
        Question question = null;
        String sql = "SELECT * FROM question WHERE enonce = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, enonce);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                question = new Question();
                question.setId_question(rs.getInt("id_question"));
                question.setEnonce(rs.getString("enonce"));
                question.setReponse(rs.getString("reponse"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de la question par énoncé : " + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(rs, pstmt);
        }
        return question;
    }

    public boolean verifierReponseUtilisateur(int idQuestion, String reponseUtilisateur) {
        boolean reponseCorrecte = false;
        String sql = "SELECT reponse FROM question WHERE id_question = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idQuestion);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String reponseCorrecteDB = rs.getString("reponse");
                // Supprimer les espaces en début et fin de chaîne
                reponseCorrecteDB = reponseCorrecteDB.trim();
                reponseUtilisateur = reponseUtilisateur.trim();
                // Vérifier si la réponse de l'utilisateur correspond à la réponse correcte 
                //(insensible à la casse)
                reponseCorrecte = reponseCorrecteDB.equalsIgnoreCase(reponseUtilisateur);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la vérification de la réponse de l'utilisateur : " + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(rs, pstmt);
        }
        return reponseCorrecte;
    }

    public int count() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS c FROM question";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.first()) {
                count = rs.getInt("c");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors du comptage :" + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(rs, pstmt);
        }
        return count;
    }

    public Collection<Question> list() {
        ArrayList<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM question";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId_question(rs.getInt("id_question"));
                q.setEnonce(rs.getString("enonce"));
                q.setReponse(rs.getString("reponse"));
                list.add(q);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors du listage :" + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(rs, pstmt);
        }
        return list;
    }

    public void create(String enonce, String reponse) {
        String sql = "INSERT INTO question (enonce, reponse) " + "VALUES (?, ?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, enonce);
            pstmt.setString(2, reponse);
            int nbLines = pstmt.executeUpdate();
            if (nbLines == 1) {
                ResultSet autoGeneratedKeys = pstmt.getGeneratedKeys();
                autoGeneratedKeys.first();
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'insertion : " + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(null, pstmt);
        }
    }

    public Question read(Integer id) {
        Question objQ = null;
        String sql = "SELECT * FROM question WHERE id_question=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.first()) {
                objQ = new Question();
                objQ.setId_question(rs.getInt("id_question"));
                objQ.setEnonce(rs.getString("enonce"));
                objQ.setReponse(rs.getString("reponse"));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la lecture : " + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(rs, pstmt);
        }
        return objQ;
    }

    public void updateEnonce(Question objQ, String s) {
        String sql = "UPDATE question SET enonce=? WHERE id_question=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, s);
            pstmt.setInt(2, objQ.getId_question());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'update : " + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(null, pstmt);
        }
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM question WHERE id_question=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur lors du delete : " + ex.getMessage());
        } finally {
            // Fermeture des ressources et de la connexion
            closeResources(null, pstmt);
        }
    }

    /**
     * Ferme les ressources ResultSet et PreparedStatement
     *
     * @param rs ResultSet à fermer
     * @param pstmt PreparedStatement à fermer
     */
    private void closeResources(ResultSet rs, PreparedStatement pstmt) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erreur lors de la fermeture du ResultSet : " + ex.getMessage());
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                System.err.println("Erreur lors de la fermeture du PreparedStatement : " + ex.getMessage());
            }
        }
    }
}
