package GUI;

import java.sql.*;

public class Limit {
    private int daglimietnr;
    private int datum;
    private int daglimiet;
    private int weeklimiet;
    private int maandlimiet;
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";

    public int getDaglimietnr() {
        return daglimietnr;
    }

    public int getDatum() {
        return datum;
    }

    public int getDaglimiet() {
        return daglimiet;
    }

    public int getWeeklimiet(){return  weeklimiet;}

    public int getMaandlimiet(){return  maandlimiet;}

    public void setWeeklimiet(int weeklimiet){this.weeklimiet = weeklimiet;}

    public void setMaandlimiet(int maandlimiet){this.maandlimiet = maandlimiet;}

    public void setDaglimietnr(int daglimietnr) {
        this.daglimietnr = daglimietnr;
    }

    public void setDatum(int datum) {
        this.datum = datum;
    }

    public void setDaglimiet(int daglimiet) {
        this.daglimiet = daglimiet;
    }

    public int getLimit(int gebruikersnr, String datum){
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
            String sql = "SELECT * FROM daglimiet WHERE gebruikersnr=? AND datum=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting gebruikersnr into statement
            statement.setInt(1, gebruikersnr);
            statement.setString(2, datum);
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then linking to new screen
            while(rs.next()){
                setDaglimiet(rs.getInt("daglimiet"));
            }
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
        return 0;
    }

    public int getWeeklyLimit( User loggedInUser,int weeknr){
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
            String sql = "SELECT weeklimiet FROM weeklimiet WHERE weeknr=? AND gebruikersnr=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting gebruikersnr into statement
            statement.setInt(1, weeknr);
            statement.setInt(2, loggedInUser.getGebruikersnr());
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then linking to new screen
            while(rs.next()){
                setWeeklimiet(rs.getInt("weeklimiet"));
            }
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
        return 0;
    }

    public int getMonthlyLimit(User loggedInUser, int maandnr){
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
            String sql = "SELECT maandlimiet FROM maandlimiet WHERE maandnr=? AND gebruikersnr=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting gebruikersnr into statement
            statement.setInt(1, loggedInUser.getGebruikersnr());
            statement.setInt(2, maandnr);
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then linking to new screen
            while(rs.next()){
                setMaandlimiet(rs.getInt("maandlimiet"));
            }
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
        return 0;
    }

}
