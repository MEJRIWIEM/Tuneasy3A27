/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.compcrud;
import javax.swing.*;
import java.text.*;
import javafx.scene.control.TableView;
/**
 *
 * @author Maya
 */
public class Divers {
    public static void imprimerJtable(JTable jt , String titre) {
        MessageFormat tete =new MessageFormat("liste des competitions");
        MessageFormat pied =new MessageFormat("Page{0,number,integer}");
        try{
                    jt.print(JTable.PrintMode.NORMAL,tete,pied);

        }catch( Exception e)
        {
            JOptionPane.showMessageDialog(null, "erreur"+e,"impression",JOptionPane.ERROR_MESSAGE);
        }
        
    }

    static void imprimerJtable(TableView<competition> tvcompetition, String titre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
