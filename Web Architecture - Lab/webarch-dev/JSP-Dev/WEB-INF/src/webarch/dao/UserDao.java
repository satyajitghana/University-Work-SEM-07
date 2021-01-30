package webarch.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import webarch.db.DBConnection;

public class UserDao {
    
    public static String getMd5(String input) { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
    public static boolean loginUser(String username, String password) {
        Connection conn = DBConnection.getDbConnection();
        
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, hashed_password FROM `STUDENT_LOGIN` WHERE `user_name` = ? LIMIT 1");
            stmt.setString(1, username);
            
            ResultSet rs =  stmt.executeQuery();
            
            if (rs.next()) {
                String hashedPassword = rs.getString(2);
                
                if (hashedPassword.equals(getMd5(password))) {
                    
                    System.out.println("Login Success for User: " + username);
                    
                    return true;
                }
            }
            
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static boolean registerUser(String username, String password, String fullname, String usnno, String dept, String course ) {
        Connection conn = DBConnection.getDbConnection();
        
        try {  
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO `STUDENT_LOGIN` (`user_name`, `hashed_password`) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, getMd5(password));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                Integer id = rs.getInt(1);

                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO STUDENT( id, reg_no, name, department, course, contact_no ) VALUES (?, ?, ?, ?, ?, ?)");
                stmt2.setInt(1, id);
                stmt2.setString(2, usnno);
                stmt2.setString(3, fullname);
                stmt2.setString(4, dept);
                stmt2.setString(5, course);
                stmt2.setString(6, "9999999999"); // hardcoded value for now

                int count = stmt2.executeUpdate();

                if (count > 0)
                    return true;
                else
                    return false;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return false;
        
    }
}
