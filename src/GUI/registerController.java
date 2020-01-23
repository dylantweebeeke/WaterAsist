package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class registerController {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";

    public static void registerNewUser(String username, String password, String email){
        Connection conn = null;
        Statement stmt = null;
        try {
            //setting driver
            Class.forName(JDBC_DRIVER);
            //create connection
            System.out.println("Connecting to a database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database succesfully...");
            //creating sql statement specific for this function
            System.out.println("creating statement...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO gebruiker(email,wachtwoord,username) VALUES (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,username);

            statement.executeUpdate();
            System.out.println("Inserted records into the table...");
            //handling all the exception and throws
        } catch (SQLException es) {
            System.out.println("Problem: ");
            es.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                //DO NOTHING
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            System.out.println("Goodbye!");
        }
    }

    public static boolean checkIfEmailExsist(String email){
        Connection conn = null;
        Statement stmt = null;
        int exsist = 0;
        try {
            //setting driver
            Class.forName(JDBC_DRIVER);
            //create connection
            System.out.println("Connecting to a database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database succesfully...");
            //creating sql statement specific for this function
            System.out.println("creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(email) AS emailTotal FROM gebruiker WHERE email = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                exsist = rs.getInt("emailTotal");
            }
            System.out.println("Inserted records into the table...");
            //handling all the exception and throws
        } catch (SQLException es) {
            System.out.println("Problem: ");
            es.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                //DO NOTHING
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            System.out.println("Goodbye!");
        }
        if (exsist >= 1) return true;
        else return false;
    }
}
