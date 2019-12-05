package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;

public class LoginScreen {
    public JPanel LoginPanel;
    private JTextField usernameTextfield;
    private JTextField PasswordTextfield;
    private JButton loginButton;
    private JButton backButton;
    private JTextField passwordTextfield;
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";




    public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginScreen().LoginPanel);
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

    public LoginScreen() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //declaring connection and statement as empty
                Connection conn = null;
                Statement stmt = null;
                try {
                    //getting input field value from username and password
                    String username = usernameTextfield.getText();
                    String password = passwordTextfield.getText();
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
                        Component component = (Component) e.getSource();
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setContentPane(new homeScreen().homeView);
                        frame.setVisible(true);
                        System.out.println("New window opened!");
                    } else {  //if value and DB data are not the same then give an error msg to the user
                        JOptionPane.showMessageDialog(null, "Username is not correct!");
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
            }
        });
        backButton.addActionListener(new ActionListener() {
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

    private void createUIComponents() {
        //same for home button
        ImageIcon iiBack = new ImageIcon("img/backButton.png");
        Image imgBack = iiBack.getImage();
        Image newImgBack = imgBack.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        iiBack = new ImageIcon(newImgBack);
        backButton = new JButton(iiBack);
        // deleting background color and border
        backButton.setBackground(new Color(0,0,0,0));
        backButton.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        backButton.setContentAreaFilled(false);
    }
}
