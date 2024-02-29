/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe singleton de mariadb
 * 
 * @author Elisa Bothy
 */
public final class MariadbConnection {
    
    private static Connection connection;
    
    private MariadbConnection(){
    }
    
    /**
     * 
     * @return Un singleton de connexion 
     */
    public static Connection getInstance(){
        if (connection == null){
            String url = "jdbc:mariadb://wp.ldnr.fr:3306/cda202302_g1";
            try {
                connection = DriverManager.getConnection(url, "cda202302_g1", "0m2Y8FqwXiBgvwLY");
            } catch (SQLException ex) {
                Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);
            }
        }
        return connection;
    }
    
    public static void  closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MariadbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

