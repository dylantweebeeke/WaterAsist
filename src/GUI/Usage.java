package GUI;

import java.sql.*;
import java.util.ArrayList;

public class Usage {
    private int dagwaternr;
    private int datum;
    private int daggebruik;
    private int sensornr;
    private int total;
    private int weeknr;
    private int maandnr;
    private Date date;
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";
    private ArrayList<Usage> listOfUsage;

    public int getDagwaternr(){
        return this.dagwaternr;
    }

    public int getDatum(){
        return this.datum;
    }

    public int getDaggebruik(){
        return this.daggebruik;
    }

    public int getSensornr(){
        return this.sensornr;
    }

    public Date getDate(){return this.date;}

    public int getWeeknr() {return this.weeknr;}

    public int getMaandnr() {return this.maandnr;}

    public void setDatum(int datum){
        this.datum = datum;
    }

    public void setDaggebruik(int daggebruik){
        this.daggebruik = daggebruik;
    }

    public void setSensornr(int sensornr){
        this.sensornr = sensornr;
    }

    public void setWeeknr(int weeknr) {this.weeknr = weeknr;}

    public void setMaandnr(int maandnr) {this.maandnr = maandnr;}

    public void setDate(Date date) {this.date = date;}
    public int getTotalUsage(){
        return this.total;
    }

    /**
     * this method sets the total usage based on the usage of a certain date. The date can be retrieved by calling the getTotal method.
     * @param gebruikersnr
     * @param datum
     */
    public void getUsage(int gebruikersnr, String datum){
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
            String sql = "SELECT * FROM dagelijkswatergebruik WHERE gebruikersnr=? AND datum=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting gebruikersnr into statement
            statement.setInt(1, gebruikersnr);
            statement.setString(2, datum);
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then linking to new screen
            while(rs.next()){
                setDatum(rs.getInt("datum"));
                this.total += rs.getInt("daggebruik");
                setSensornr(rs.getInt("sensornr"));
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
    }

    /**
     * This method sets a list with the total usage of an user found in the database with a certain user ID.
     * @param gebruikersnr
     * @return an arraylist of the total usage by day.
     */
    public ArrayList getUsageTotalByDay(int gebruikersnr){
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
            String sql = "SELECT datum, SUM(daggebruik) AS totaalGebruik FROM dagelijkswatergebruik GROUP BY datum;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting gebruikersnr into statement
            //statement.setInt(1, gebruikersnr);
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then linking to new screen
            listOfUsage = new ArrayList<Usage>();
            while(rs.next()){
                Usage usage = new Usage();
                usage.setDate(rs.getDate("datum"));
                usage.setDaggebruik(rs.getInt("totaalGebruik"));
                listOfUsage.add(usage);
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

        return listOfUsage;
    }

    /**
     * his method sets a list with the total usage of an user found in the database with a certain user ID.
     * @param gebruikersnr
     * @return an arraylist of the total usage by week.
     */
    public ArrayList getUsageTotalByWeek(int gebruikersnr){
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
            String sql = "SELECT weeknr, datum, SUM(daggebruik) AS totaalWeekGebruik FROM dagelijkswatergebruik GROUP BY weeknr;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting gebruikersnr into statement
            //statement.setInt(1, gebruikersnr);
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then linking to new screen
            listOfUsage = new ArrayList<Usage>();
            while(rs.next()){
                Usage usage = new Usage();
                usage.setWeeknr(rs.getInt("weeknr"));
                usage.setDaggebruik(rs.getInt("totaalWeekGebruik"));
                usage.setDate(rs.getDate("datum"));
                listOfUsage.add(usage);
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

        return listOfUsage;
    }

    public ArrayList getUsageTotalByMonth(int gebruikersnr){
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
            String sql = "SELECT datum ,maandnr, SUM(daggebruik) AS totaalMaandGebruik FROM dagelijkswatergebruik GROUP BY maandnr;";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting gebruikersnr into statement
            //statement.setInt(1, gebruikersnr);
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then linking to new screen
            listOfUsage = new ArrayList<Usage>();
            while(rs.next()){
                Usage usage = new Usage();
                usage.setMaandnr(rs.getInt("maandnr"));
                usage.setDaggebruik(rs.getInt("totaalMaandGebruik"));
                usage.setDate(rs.getDate("datum"));
                listOfUsage.add(usage);
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

        return listOfUsage;
    }


}
