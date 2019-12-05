package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;

public class RegisterScreen extends  JFrame {
    public JPanel RegisterPanel;
    private JTextField passwordField;
    private JButton RegisterButton;
    private JTextField mailField;
    private JButton terugButton;
    private JTextField usernameField;
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";

    public RegisterScreen() {
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                Statement stmt = null;
                try {
                    //getting input field value from username and email
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    String email = mailField.getText();
                    //setting driver
                    Class.forName(JDBC_DRIVER);
                    //create connection
                    System.out.println("Connecting to a database...");
                    conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    System.out.println("Connected database succesfully...");
                    //creating sql statement specific for this function
                    System.out.println("creating statement...");
                    stmt = conn.createStatement();
                    String sql = "INSERT INTO gebruiker(gebruikersnr,email,wachtwoord,username) VALUES ('3', '" + email + "', '" + password + "', '" + username + "');";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.executeUpdate();
                    System.out.println("Inserted records into the table...");
                    //redirecting to the startup page
                    Component component = (Component) e.getSource();
                    JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                    frame.setContentPane(new startupscherm().startupView);
                    frame.setVisible(true);
                    System.out.println("New window opened!");
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
        });

        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new startupscherm().startupView);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });
    }

    public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        JFrame frame = new JFrame("Register");
        frame.setContentPane(new RegisterScreen().RegisterPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setMaximumSize(new Dimension(440,500));
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Dimension size = frame.getSize();
                Dimension min = frame.getMinimumSize();
                if (size.getWidth() < min.getWidth()) {
                    frame.setSize((int) min.getWidth(), (int) size.getHeight());
                }
                if (size.getHeight() < min.getHeight()) {
                    frame.setSize((int) size.getWidth(), (int) min.getHeight());
                }
            }
        });
        frame.setSize(1100, 1100);

        frame.setVisible(true);

    }

    private void createUIComponents() {
        //same for home button
        ImageIcon iiBack = new ImageIcon("img/backButton.png");
        Image imgBack = iiBack.getImage();
        Image newImgBack = imgBack.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        iiBack = new ImageIcon(newImgBack);
        terugButton = new JButton(iiBack);
        // deleting background color and border
        terugButton.setBackground(new Color(0,0,0,0));
        terugButton.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        terugButton.setContentAreaFilled(false);
    }
}
