package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginScreen {
    public JPanel LoginPanel;
    private JTextField usernameTextfield;
    private JButton loginButton;
    private JButton backButton;
    private JPasswordField passwordTextfield;
    private JLabel loginLabel;
    private boolean succeeded;
    private User user;
    private int gebruikersnr;
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";

    public LoginScreen() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextfield.getText();
                String password = passwordTextfield.getText();
                if(Login.authenticate(username,password)){
                    succeeded = true;
                    user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setGebruikersnr(getUserID(username));
                    Component component = (Component) e.getSource();
                    JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                    frame.setContentPane(new homeScreen(user).homeView);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Gebruikersnaam of wachtwoord is niet correct.");
                    usernameTextfield.setText("");
                    passwordTextfield.setText("");
                    succeeded = false;
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

        passwordTextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextfield.getText();
                String password = passwordTextfield.getText();
                if(Login.authenticate(username,password)){
                    succeeded = true;
                    user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setGebruikersnr(getUserID(username));
                    Component component = (Component) e.getSource();
                    JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                    frame.setContentPane(new homeScreen(user).homeView);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Gebruikersnaam of wachtwoord is niet correct.");
                    usernameTextfield.setText("");
                    passwordTextfield.setText("");
                    succeeded = false;
                }
            }
        });
    }

    public boolean isSucceeded(){
        return this.succeeded;
    }

    public User getUser(){
        return this.user;
    }

    public int getUserID(String username){
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
            String sql = "SELECT * FROM gebruiker WHERE username=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            //inserting textfield value into the statement
            statement.setString(1, username);
            //getting the result set
            ResultSet rs = statement.executeQuery();
            //checking if the input and DB data are the same and then returning true
            if (rs.next()){
                gebruikersnr = rs.getInt("gebruikersnr");
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
        return gebruikersnr;
    }

    private void createUIComponents() {
        //setting home button to an icon
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
