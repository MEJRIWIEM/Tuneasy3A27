/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BabyViper
 */
public class database {
    
     String url = "jdbc:mysql://localhost:3306/tun";
     String login = "root";
     String pwd = "";
    
    public  static database db;
    public Connection con;
    
    private database() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    public static database getInstance()
    {if(db==null)
        db=new database();
    return db;
    }     
}
