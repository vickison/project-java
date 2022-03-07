package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class connexion {
    
    /*static {
            try{
                Class.forName("com.mysql.jdbc.Driver");
            } catch(ClassNotFoundException ex){   
            }
    }*/
    
    public static Connection getConnexion() throws SQLException{
        try{
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException ex){   
        }
        //return DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma", "root", "laumavilo");
        return DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\Documents\\NetBeansProjects\\rhema\\database.sqlite");
        //return DriverManager.getConnection("jdbc:sqlite:pharma.sqlite");
    }
    
    public static void closeConnexion(Connection connection) {
        if(connection == null){
            return;
        }try{
            connection.close();
        }catch(SQLException ex){
            
        }
    }
    public static boolean rechercher(String name, String pwd){
        boolean rq = false;
        String sql = "SELECT * FROM users WHERE username =? AND pwd =?";
        Connection conn = null;
        try{
            conn = getConnexion();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, pwd);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                rq = true;
            }else{
                rq =false;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            closeConnexion(conn);
        }
        return rq;
    }
}

