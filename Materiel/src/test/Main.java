/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.materiel;
import services.materielCRUD;
import tools.MyConnection;
import java.sql.SQLException;
/**
 *
 * @author asus
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        MyConnection mc = new MyConnection();
        materielCRUD mm = new materielCRUD();
        materiel m = new materiel(1,"nom", "description", 5, "image") ;
    
        // cc.addCompetition(c);
        mm.delete(1);
        /*ForumCRUD pc = new ForumCRUD();
        Forum f = new Forum(7, "testttt2", "forum");

        pc.addForum(f);
        System.out.println(pc.getForums());*/
    }
}
