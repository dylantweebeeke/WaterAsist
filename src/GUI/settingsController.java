package GUI;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class settingsController {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";

    /**
     * checks if the filled in password is the correct password
     * @param loggedInUser, password
     * @returns true or false
     */
    public static boolean checkIfCorrectPass(User loggedInUser, String password){
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
            statement.setString(1, loggedInUser.getUsername());
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
        if (nameuser.equals(loggedInUser.getUsername()) && wordpass.equals(password)){
            return true;
        } else return false;
    }
    //------------------------------------------------------------------------------ Methods for adjusting user info ----------------------------------------------------------------------------------------

    /**
     * Updates the password to the password the user filled in
     * @param loggedInUser
     * @param password
     */
    public static void updatePassword(User loggedInUser, String password){
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
            String sql = "UPDATE gebruiker SET wachtwoord=? WHERE username=? AND gebruikersnr=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting the credentials the logged in user gave
            statement.setString(1,password);
            statement.setString(2, loggedInUser.getUsername());
            statement.setInt(3, loggedInUser.getGebruikersnr());
            statement.executeUpdate();
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
    }


    /**
     * updates the username to the given username and uploads it the the DB
     * @param loggedInUser
     * @param newUsername
     */
    public static void updateUsername(User loggedInUser, String newUsername){
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
            String sql = "UPDATE gebruiker SET username=? WHERE gebruikersnr=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting the credentials the logged in user gave
            statement.setString(1,newUsername);
            statement.setInt(2, loggedInUser.getGebruikersnr());
            statement.executeUpdate();
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
    }

    /**
     * Updates the email of the user to the given Email and sets uploads it the the DB
     * @param loggedInUser
     * @param newEmail
     */
    public static void updateEmail(User loggedInUser, String newEmail){
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
            String sql = "UPDATE gebruiker SET email=? WHERE gebruikersnr=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement

            statement.setString(1,newEmail);
            statement.setInt(2, loggedInUser.getGebruikersnr());
            statement.executeUpdate();
            //checking if the input and DB data are the same and then returning true
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
    }

    //----------------------------------------------------------------------------------------Methods for updating limits----------------------------------------------------------

    /**
     * Updates the daily limit to a given number and uploads it to the DB
     * @param loggedInUser
     * @param limit
     */
    public static void updateDailyLimit(User loggedInUser, double limit){
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
            String sql = "UPDATE daglimiet SET daglimiet=? WHERE datum=? AND gebruikersnr=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setDouble(1, limit);
            statement.setString(2,getDateInRightFormat());
            statement.setInt(3,loggedInUser.getGebruikersnr());
            statement.executeUpdate();
            //checking if the input and DB data are the same and then returning true
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
    }

    /**
     * Updates the weekly limit to a given number and uploads it to the DB
     * @param loggedInUser
     * @param limit
     */
    public static void updateWeeklyLimit(User loggedInUser, double limit){
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
            String sql = "UPDATE weeklimiet SET weeklimiet=? WHERE weeknr=? AND gebruikersnr=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setDouble(1,limit);
            statement.setInt(2,getWeekNumber());
            statement.setInt(3,loggedInUser.getGebruikersnr());

            statement.executeUpdate();
            //checking if the input and DB data are the same and then returning true
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
    }

    /**
     * Updates the monthly limit to a given number and updates it to the DB
     * @param loggedInUser
     * @param limit
     */
    public static void updateMonthlyLimit(User loggedInUser, double limit){
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
            String sql = "UPDATE maandlimiet SET maandlimiet=? WHERE maandnr=? AND gebruikersnr=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setDouble(1,limit);
            statement.setInt(2,getMonthNumber());
            statement.setInt(3,loggedInUser.getGebruikersnr());

            statement.executeUpdate();
            //checking if the input and DB data are the same and then returning true
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
    }


    //----------------------------------------------------------------------------------------Methods for setting limits-----------------------------------------------------------------------

    /**
     * Sets a new daily limit and uploads it to the DB
     * @param loggedInUser
     * @param limit
     */
    public static void setDailyLimit(User loggedInUser, double limit){
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
            String sql = "INSERT INTO daglimiet (datum,daglimiet,gebruikersnr) VALUES(?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setString(1,getDateInRightFormat());
            statement.setDouble(2,limit);
            statement.setInt(3,loggedInUser.getGebruikersnr());

            statement.executeUpdate();
            //checking if the input and DB data are the same and then returning true
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
    }

    /**
     * Sets a new weekly limit and uploads it to the DB
     * @param loggedInUser
     * @param limit
     */
    public static void setWeeklyLimit(User loggedInUser, double limit){
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
            String sql = "INSERT INTO weeklimiet (datum,weeklimiet,gebruikersnr,weeknr) VALUES(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setString(1,getDateInRightFormat());
            statement.setDouble(2,limit);
            statement.setInt(3,loggedInUser.getGebruikersnr());
            statement.setInt(4, getWeekNumber());

            statement.executeUpdate();
            //checking if the input and DB data are the same and then returning true
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
    }

    /**
     * Sets a new monthly limit and uploads it to the DB
     * @param loggedInUser
     * @param limit
     */
    public static void setMonthlyLimit(User loggedInUser, double limit){
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
            String sql = "INSERT INTO maandlimiet (datum,maandlimiet,gebruikersnr, maandnr) VALUES(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setString(1,getDateInRightFormat());
            statement.setDouble(2,limit);
            statement.setInt(3,loggedInUser.getGebruikersnr());
            statement.setInt(4, getMonthNumber());

            statement.executeUpdate();
            //checking if the input and DB data are the same and then returning true
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
    }

    //---------------------------------------------------------------- Method for checking if a record existst in the DB -----------------------------------------------------------

    /**
     * Check if there is already a limit for the given month
     * @return
     */
    public static boolean checkMonthlyExsists(){
        Connection conn = null;
        Statement stmt = null;
        int exists = 0;
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
            String sql = "SELECT COUNT(maandnr) AS aantal FROM maandlimiet WHERE maandnr = ? GROUP BY maandnr;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setInt(1, getMonthNumber());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                exists = rs.getInt("aantal");
            }
            //checking if the input and DB data are the same and then returning true
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

        if(exists >= 1) return true;
        else return false;

    }

    /**
     * checks if there is a limit for the current week
     * @return
     */
    public static boolean checkWeeklyExists(){
        Connection conn = null;
        Statement stmt = null;
        int exists = 0;
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
            String sql = "SELECT COUNT(weeknr) AS aantal FROM weeklimiet WHERE weeknr=? GROUP BY weeknr;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setInt(1, getWeekNumber());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                exists = rs.getInt("aantal");
            }
            //checking if the input and DB data are the same and then returning true
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

        if(exists >= 1) return true;
        else return false;

    }

    /**
     * checks there is a limit for the current day
     * @return
     */
    public static boolean checkDailyExists(){
        Connection conn = null;
        Statement stmt = null;
        int exists = 0;
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
            String sql = "SELECT COUNT(datum) AS aantal FROM daglimiet WHERE datum = ? GROUP BY datum;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setString(1, getDateInRightFormat());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                exists = rs.getInt("aantal");
            }
            //checking if the input and DB data are the same and then returning true
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

        if(exists >= 1) return true;
        else return false;

    }

    /**
     * gets the current date and puts it in the right format
     * @return date in the format yyyy MM dd
     */
    public static String getDateInRightFormat()  {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    /**
     * get the number of the current week based on the current date
     * @return current week number
     */
    public static int getWeekNumber(){
        Calendar cal = new GregorianCalendar();
        Date date = new Date();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * get the number of the current month based on the current date where January = 1
     * @return current month number
     */
    public static  int getMonthNumber(){
        Calendar cal2 = new GregorianCalendar();
        Date date = new Date();
        cal2.setTime(date);
        return cal2.get(Calendar.MONTH) + 1;
    }
}
