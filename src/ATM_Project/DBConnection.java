/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ATM_Project;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static ATM_Project.Kullanıcı.password;
import static ATM_Project.Kullanıcı.user;
import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;





/**
 *
 * @author omerc
 */
public class DBConnection {
    
   Connection conn;
    public void DBBaglanti() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/bank","omer","omer");
        
    }
    
    
    
   
    
   
    
    
    
    
   
    
   public void Goruntuleme() throws SQLException {
       DBBaglanti();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM KKISTEME");
    
}
        
        
    
        
}
    

