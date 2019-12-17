package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Login {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";

    public static boolean authenticate(String username, String password){
        Connection conn = null;
        Statement stmt = null;
        try {
            //setting driver
            Class.forName(JDBC_DRIVER);
            //create connection
            System.out.println("Connecting to a database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connected database succesfully...");
            //creating sql statement specific for this function
            System.out.println("creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM gebruiker WHERE username=? AND wachtwoord=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setString(1, username);
            statement.setString(2, password);
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then linking to new screen
            if (rs.next()){
                return true;
            } else {  //if value and DB data are not the same then give an error msg to the user
                JOptionPane.showMessageDialog(null, "Username is not correct!");
                return false;
            }
            //handling all the exception and throws
        } catch (SQLException es) {
            System.out.println("Problem: ");
            es.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try{
                if(stmt != null){
                    conn.close();
                }
            } catch (SQLException se){
                //DO NOTHING
            }
            try{
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException se){
                se.printStackTrace();
            }
            System.out.println("Goodbye!");
        }

        return false;
    }
}
