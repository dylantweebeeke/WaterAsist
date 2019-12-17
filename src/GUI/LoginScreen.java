package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen {
    public JPanel LoginPanel;
    private JTextField usernameTextfield;
    private JButton loginButton;
    private JButton backButton;
    private JPasswordField passwordTextfield;
    private JLabel loginLabel;
    private boolean succeeded;
    private User user;
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
                    Component component = (Component) e.getSource();
                    JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                    frame.setContentPane(new homeScreen(user).homeView);
                    frame.setVisible(true);
                    System.out.println("New window opened!");
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
                    Component component = (Component) e.getSource();
                    JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                    frame.setContentPane(new homeScreen(user).homeView);
                    frame.setVisible(true);
                    System.out.println("New window opened!");
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
