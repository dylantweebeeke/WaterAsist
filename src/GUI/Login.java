package GUI;


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
        String nameuser = "";
        String wordpass = "";
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
            //checking if the input and DB data are the same and then returning true
            if (rs.next()){
                nameuser = rs.getString("username");
                wordpass = rs.getString("wachtwoord");
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
        if (nameuser.equals(username) && wordpass.equals(password)){
            return true;
        } else return false;
    }

//    public static boolean authenticate(String username, String password){
//        if(this.username.equals(username) && this.password.equals(password)){
//            return true;
//        } else return false;
//    }
}
