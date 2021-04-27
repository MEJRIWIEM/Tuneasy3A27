/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author asus
 */
public class MyConnection {
    public String url = "jdbc:mysql://localhost:3306/tuneasy";
    public String user = "root";
    public String pwd = "root";
    public static MyConnection instance;
 
    
    Connection cnx;

    public MyConnection() {

        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   

    public Connection getCnx() {
        return cnx;
    }
    public static MyConnection getInstance() {
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
}
