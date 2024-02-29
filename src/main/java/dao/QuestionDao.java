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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public Question readReponse(Integer id) {
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
    

}
