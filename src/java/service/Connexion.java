/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tall
 */
public class Connexion {

    private static String url = "jdbc:mysql://localhost:3306/navette_logement";
    private static String user = "root";
    private static String password = "";
    private static Connection cnx;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            cnx = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
        }

        return cnx;
    }
}
