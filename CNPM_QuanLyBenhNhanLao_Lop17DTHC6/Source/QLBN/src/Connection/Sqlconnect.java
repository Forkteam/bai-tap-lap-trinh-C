/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;
import DAO.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sqlconnect {
     public  Connection getSQLServerConnection() throws ClassNotFoundException, SQLException {
     
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QLBN;user=duonglt;password=123456";
        Connection conn = DriverManager.getConnection(dbURL);
        return conn;
    }
      public List<User> dsUser(String username, String password){
        List<User> rs = null;
        Connection conn;
         try {
             conn = this.getSQLServerConnection();
             
             rs = new ArrayList();
            java.sql.Statement statement = conn.createStatement();
            String sql = "SELECT * FROM USER_LOGIN WHERE name like '" + username + "' and pass like '" + password + "'";
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                User user = new User();
                user.setUsername(resultSet.getString("name"));
                user.setPassward(resultSet.getString("pass"));
                
                rs.add(user);             
            }
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(Sqlconnect.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(Sqlconnect.class.getName()).log(Level.SEVERE, null, ex);
         }
        return rs;
    }
}
