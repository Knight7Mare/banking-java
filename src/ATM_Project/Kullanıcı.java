/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ATM_Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static ATM_Project.Kullanıcı.password;
import static ATM_Project.Kullanıcı.user;

/**
 *
 * @author omerc
 */



public class Kullanıcı {
    
    DBConnection db = new DBConnection();
    
    
int yetki;
public static String user;
public static String password;

public int yetki_(){
    return yetki;
}
public String user_(){
    return user;
}
public String password_(){
    return password;
}

    
    
    
     public boolean userLogin(String username, String password){
        try{
            db.DBBaglanti();
            Statement st = db.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT PASSWORD FROM KULLANICI WHERE ID = '"+username+"'");
            if(rs.next()){
                return password.equals(rs.getString(1));
                }
            }
        catch(SQLException s){
            JOptionPane.showMessageDialog(null,"Hata:"+s.toString());
        }
        return false;
    }

    boolean userLogin(JTextField jTextField1UserName, JPasswordField jPasswordField1Pw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public int kontrolyetki(String ID){
        int yetki = 0;
        try{
            db.DBBaglanti();
            Statement st = db.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT YETKI FROM KULLANICI WHERE ID = '"+ID+"' ");
          if(rs.next()){
              yetki = rs.getInt(1);
              
          }  
        }
        
        catch(SQLException s){
            JOptionPane.showMessageDialog(null,"Hata:"+s.toString());
        }
        return yetki;
    }
     
     
      public void registerEtme(String statu, String kullaniciadi, String sifre, String adi, String soyadi){
        
        try{
            db.DBBaglanti();
            String girdi = "INSERT INTO KULLANICI VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = db.conn.prepareStatement(girdi);
            Statement st = db.conn.createStatement();
            pstmt.setString(8, statu);
            pstmt.setString(1, kullaniciadi);
            pstmt.setString(2, sifre);
            pstmt.setString(3, adi);
            pstmt.setString(4, soyadi);
            pstmt.setString(5, null);
            pstmt.setString(6, null);
            pstmt.setString(7, null);
            
            st = db.conn.createStatement();
            pstmt.executeUpdate();
        } catch (SQLException ex){
           Logger.getLogger(DBConnection.class.getName()).log(java.util.logging.Level.SEVERE, null,ex);
        
        }
        
    }
      
      
      public void sifreDegistirme(String eskisifre, String yenisifre) throws SQLException{
           db.DBBaglanti();
           String a = user_();
           String sifredegistir = "UPDATE KULLANICI SET PASSWORD = '"+yenisifre+"' WHERE ID = '"+a+"' ";
           Statement st = db.conn.createStatement();
           PreparedStatement pstmt = db.conn.prepareStatement(sifredegistir);
           
           int num =  st.executeUpdate(sifredegistir);
            }
      
      public void ParaCekme(double cekilecekmiktar) throws SQLException{
           db.DBBaglanti();
           String a = user_();
           double bakiye = 0;
           Statement st = db.conn.createStatement();
           ResultSet rs = st.executeQuery("SELECT BAKIYE FROM KULLANICI WHERE ID = '"+a+"' ");
           if(rs.next()){
           bakiye = rs.getDouble(1);
           }
           bakiye = bakiye - cekilecekmiktar;
           
           String sonbakiye = "UPDATE KULLANICI SET BAKIYE ="+bakiye+" WHERE ID = '"+a+"' ";
           
           PreparedStatement pstmt = db.conn.prepareStatement(sonbakiye);
           
           int b = st.executeUpdate(sonbakiye);
           
           
      }
      
      public void ParaYatirma(double yatirilanmiktar) throws SQLException{
           db.DBBaglanti();
           String a = user_();
           double bakiye = 0;
           Statement st = db.conn.createStatement();
           ResultSet rs = st.executeQuery("SELECT BAKIYE FROM KULLANICI WHERE ID = '"+a+"' ");
           if(rs.next()){
           bakiye = rs.getDouble(1);
           }
           bakiye = bakiye + yatirilanmiktar;
           
           String sonbakiye = "UPDATE KULLANICI SET BAKIYE ="+bakiye+" WHERE ID = '"+a+"' ";
           
           PreparedStatement pstmt = db.conn.prepareStatement(sonbakiye);
           
           int b = st.executeUpdate(sonbakiye);
           
           
      }
      
     public void ParaTransferi(String aliciKadi, double miktar) throws SQLException{
           db.DBBaglanti();
           String a = user_();
           String c = aliciKadi;
           double gonderenbakiye = 0;
           double alicibakiye = 0;
           Statement st = db.conn.createStatement();
           ResultSet rs = st.executeQuery("SELECT BAKIYE FROM KULLANICI WHERE ID = '"+a+"' ");
           if(rs.next()){
           gonderenbakiye = rs.getDouble(1);
           }
           gonderenbakiye = gonderenbakiye - miktar;
           
           String sonbakiyegonderici = "UPDATE KULLANICI SET BAKIYE ="+gonderenbakiye+" WHERE ID = '"+a+"' ";
           
           PreparedStatement pstmt = db.conn.prepareStatement(sonbakiyegonderici);
           
           int b = st.executeUpdate(sonbakiyegonderici);
           
           ResultSet rs2 = st.executeQuery("SELECT BAKIYE FROM KULLANICI WHERE ID = '"+c+"' ");
           if(rs2.next()){
               alicibakiye = rs2.getDouble(1);
           }
           alicibakiye = alicibakiye + miktar;
           
           String sonbakiyealici = "UPDATE KULLANICI SET BAKIYE ="+alicibakiye+" WHERE ID = '"+c+"' ";
           
           int d = st.executeUpdate(sonbakiyealici);
           
     }
     
     
     public void FaturaOdeme(double odenecekmiktar) throws SQLException {
           db.DBBaglanti();
           String a = user_();
           double bakiye = 0;
           Statement st = db.conn.createStatement();
           ResultSet rs = st.executeQuery("SELECT BAKIYE FROM KULLANICI WHERE ID = '"+a+"' ");
           if(rs.next()){
           bakiye = rs.getDouble(1);
           }
           bakiye = bakiye - odenecekmiktar;
           
           String sonbakiye = "UPDATE KULLANICI SET BAKIYE ="+bakiye+" WHERE ID = '"+a+"' ";
           
           PreparedStatement pstmt = db.conn.prepareStatement(sonbakiye);
           
           int b = st.executeUpdate(sonbakiye);
     }
     
     public void KrediKartiBasvurusu(double gelirtoplami, boolean evkiraveyakendinin, double kklimit){
            try{
            String a = user_();
            db.DBBaglanti();
            String girdi = "INSERT INTO KREDIKARTITALEPLERI VALUES(?,?,?,?)";
            PreparedStatement pstmt = db.conn.prepareStatement(girdi);
            Statement st = db.conn.createStatement();
            
            pstmt.setString(1, a);
            pstmt.setDouble(2, kklimit);
            pstmt.setBoolean(3, evkiraveyakendinin);
            pstmt.setDouble(4, gelirtoplami);
           
            
            st = db.conn.createStatement();
            pstmt.executeUpdate();
        } catch (SQLException ex){
           Logger.getLogger(DBConnection.class.getName()).log(java.util.logging.Level.SEVERE, null,ex);
        
        }
         
         
     }
     
     
     public void KrediBasvurusu(double gelirtoplami, boolean evkiraveyakendinin, double kredimiktari){
         try{
            String a = user_();
            db.DBBaglanti();
            String girdi = "INSERT INTO KREDITALEPLERI VALUES(?,?,?,?)";
            PreparedStatement pstmt = db.conn.prepareStatement(girdi);
            Statement st = db.conn.createStatement();
            
            pstmt.setDouble(1, kredimiktari);
            pstmt.setBoolean(2, evkiraveyakendinin);
            pstmt.setDouble(3, gelirtoplami);
            pstmt.setString(4, a);
           
            
            st = db.conn.createStatement();
            pstmt.executeUpdate();
        } catch (SQLException ex){
           Logger.getLogger(DBConnection.class.getName()).log(java.util.logging.Level.SEVERE, null,ex);
        
        }
     }
     
     public void KKLimitBasvurusu(double gelirtoplami, boolean evkiraveyakendinin, double limit){
         try{
            String a = user_();
            db.DBBaglanti();
            String girdi = "INSERT INTO KKLIMITTALEPLERI VALUES(?,?,?,?)";
            PreparedStatement pstmt = db.conn.prepareStatement(girdi);
            Statement st = db.conn.createStatement();
            
            pstmt.setString(1, a);
            pstmt.setDouble(2, limit);
            pstmt.setBoolean(3, evkiraveyakendinin);
            pstmt.setDouble(4, gelirtoplami);
           
            
            st = db.conn.createStatement();
            pstmt.executeUpdate();
        } catch (SQLException ex){
           Logger.getLogger(DBConnection.class.getName()).log(java.util.logging.Level.SEVERE, null,ex);
        
        }
      
}
     
     public void KKLimitReddetme(String id) throws SQLException {
           db.DBBaglanti();
           
           String datasil = "DELETE FROM OMER.KKLIMITTALEPLERI WHERE HESAPID = '"+id+"' ";
           
           Statement st = db.conn.createStatement();
           PreparedStatement pstmt = db.conn.prepareStatement(datasil);
           
           int num =  st.executeUpdate(datasil);
     }
     
     public void KKBasvuruReddetme(String id) throws SQLException  {
         db.DBBaglanti();
         
         String datasil = "DELETE FROM OMER.KREDIKARTITALEPLERI WHERE HESAPID = '"+id+"' ";
         Statement st = db.conn.createStatement();
           PreparedStatement pstmt = db.conn.prepareStatement(datasil);
           
           int num =  st.executeUpdate(datasil);
     }
    
     
   public void KrediBasvurusuReddetme(String id) throws SQLException {
       db.DBBaglanti();
       String datasil = "DELETE FROM OMER.KREDITALEPLERI WHERE HESAPID = '"+id+"' ";
         Statement st = db.conn.createStatement();
           PreparedStatement pstmt = db.conn.prepareStatement(datasil);
           
           int num =  st.executeUpdate(datasil);
   }
   
   public void KrediBasvurusuOnay(String ID) throws SQLException {
           db.DBBaglanti();
           double bakiye = 0;
           double kredimiktari = 0;
           double sonbakiye = 0;
           Statement st = db.conn.createStatement();
           ResultSet rs = st.executeQuery("SELECT KREDIMIKTARI FROM KREDITALEPLERI WHERE HESAPID = '"+ID+"' ");
           if(rs.next()){
           kredimiktari = rs.getDouble(1);
           }
           ResultSet rs2 = st.executeQuery("SELECT BAKIYE FROM KULLANICI WHERE ID = '"+ID+"' ");
           if(rs2.next()){
           bakiye = rs2.getDouble(1);
           }
           sonbakiye = kredimiktari + bakiye;
           
           String atama = "UPDATE KULLANICI SET BAKIYE ="+sonbakiye+" WHERE ID = '"+ID+"' ";
           
           PreparedStatement pstmt = db.conn.prepareStatement(atama);
           
           int b = st.executeUpdate(atama);
           
           String datasil = "DELETE FROM OMER.KREDITALEPLERI WHERE HESAPID = '"+ID+"' ";
           
           PreparedStatement pstmt2 = db.conn.prepareStatement(datasil);
           
           int num =  st.executeUpdate(datasil);
   }
   
   public void KKLimitOnay(String ID) throws SQLException {
       db.DBBaglanti();
       double limit = 0;
       Statement st = db.conn.createStatement();
           ResultSet rs = st.executeQuery("SELECT ISTENENLIMIT FROM KKLIMITTALEPLERI WHERE HESAPID = '"+ID+"' ");
           if(rs.next()){
           limit = rs.getDouble(1);
           }
           String atama = "UPDATE KULLANICI SET KREDIKARTILIMIT="+limit+" WHERE ID= '"+ID+"' ";
           PreparedStatement pstmt = db.conn.prepareStatement(atama);
           int b = st.executeUpdate(atama);
           
           String datasil = "DELETE FROM OMER.KKLIMITTALEPLERI WHERE HESAPID = '"+ID+"' ";
           
           PreparedStatement pstmt2 = db.conn.prepareStatement(datasil);
           
           int num =  st.executeUpdate(datasil);
           
   }
   
   public void KKBasvuruOnaylama(String ID) throws SQLException{
           db.DBBaglanti();
           double limit = 0;
           boolean kart = true;
           Statement st = db.conn.createStatement();
           ResultSet rs = st.executeQuery("SELECT TALEPEDILENLIMIT FROM KREDIKARTITALEPLERI WHERE HESAPID = '"+ID+"' ");
           if(rs.next()){
           limit = rs.getDouble(1);
           }
           String atama = "UPDATE KULLANICI SET KREDIKARTILIMIT="+limit+" WHERE ID= '"+ID+"' ";
           PreparedStatement pstmt = db.conn.prepareStatement(atama);
           int b = st.executeUpdate(atama);
           
           String datasil = "DELETE FROM OMER.KREDIKARTITALEPLERI WHERE HESAPID = '"+ID+"' ";
           
           PreparedStatement pstmt2 = db.conn.prepareStatement(datasil);
           
           int num =  st.executeUpdate(datasil);
           
           String kartvar = "UPDATE KULLANICI SET KREDIKARTI ="+kart+" WHERE ID='"+ID+"' ";
           
           PreparedStatement pstmt3 = db.conn.prepareStatement(kartvar);
           int num1 = st.executeUpdate(kartvar);
           
           
   }
   
}