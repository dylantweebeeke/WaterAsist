package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import java.util.regex.Pattern;

public class RegisterScreen extends  JFrame {
    public JPanel RegisterPanel;
    private JTextField passwordField;
    private JButton RegisterButton;
    private JTextField mailField;
    private JButton terugButton;
    private JTextField usernameField;
    private JTextField passwordFieldCheck;
    private final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
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
                Pattern pat = Pattern.compile(EMAIL_PATTERN);

                if (usernameField.getText().equals("") && passwordField.getText().equals("") && mailField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"U heeft geen van de velden ingevult. \n Vul alle velden in om u te registreren.");
                } else if (usernameField.getText().equals("") && passwordField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"U heeft geen gebruikersnaam en wachtwoord ingevult. \n Vul alle velden in om u te registreren.");
                } else if (passwordField.getText().equals("") && mailField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"U heeft geen wachtwoord en email ingevult. \n Vul alle velden in om u te registreren.");
                } else if (usernameField.getText().equals("") && mailField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"U heeft geen gebruikersnaam en email ingevult. \n Vul alle velden in om u te registreren.");
                } else if (usernameField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"U heeft geen gebruikersnaam. \n Vul alle velden in om u te registreren.");
                } else if (mailField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"U heeft geen email ingevult. \n Vul alle velden in om u te registreren.");
                } else if (passwordField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"U heeft geen wachtwoord ingevult. \n Vul alle velden in om u te registreren.");
                } else {
                    if (passwordField.getText().equals(passwordFieldCheck.getText()) && pat.matcher(mailField.getText()).matches() && usernameField.getText().length() > 5) {
                        if (registerController.checkIfEmailExsist(mailField.getText())){
                            JOptionPane.showMessageDialog(null,"Er is al een account bij ons geregistreert met dit email adres.\nVul een ander Email adres in.");
                        } else {
                            registerController.registerNewUser(usernameField.getText(), passwordField.getText(), mailField.getText());
                            JPanel panel = new JPanel(new GridLayout(2,2));
                            panel.add(new JLabel("Uw account is aangemaakt. U kunt nu inloggen!"));
                            int option = JOptionPane.showConfirmDialog(null, panel,"Account dialog", JOptionPane.DEFAULT_OPTION);
                            if (option == JOptionPane.OK_OPTION){
                                Component component = (Component) e.getSource();
                                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                                frame.setContentPane(new LoginScreen().LoginPanel);
                                frame.setVisible(true);
                                System.out.println("New window opened!");
                            }
                        }
                    } else if (!(usernameField.getText().length() > 5)){
                        JOptionPane.showMessageDialog(null,"Uw username is te kort! \n Probeer het opnieuw!");
                    } else if (!pat.matcher(mailField.getText()).matches()){
                        JOptionPane.showMessageDialog(null,"Uw email is niet correct. \n Vul een correcte Email in om uw te registreren.");
                    } else if (!passwordField.getText().equals(passwordFieldCheck.getText())){
                        JOptionPane.showMessageDialog(null,"Uw wachtwoorden zijn niet hetzelfde. \n Vul 2 keer hetzelfde wachtwoord in om u te registreren.");
                    } else if (!pat.matcher(mailField.getText()).matches()&& !(usernameField.getText().length() > 5)){
                        JOptionPane.showMessageDialog(null,"Uw email klopt niet en uw username is te kort. \n Pas ze aan om uw te registreren.");
                    } else if(!(usernameField.getText().length() > 5) && !passwordField.getText().equals(passwordFieldCheck.getText())){
                        JOptionPane.showMessageDialog(null,"Uw username is te kort en uw wachtwoorden zijn niet hetzelfde. \n Pas ze aan om u te registreren.");
                    } else if (!pat.matcher(mailField.getText()).matches() && !passwordField.getText().equals(passwordFieldCheck.getText())){
                        JOptionPane.showMessageDialog(null,"Uw email klopt niet en uw wachtwoorden zijn niet hetzelfde. \n Pas ze aan om u te registreren.");
                    } else if (!pat.matcher(mailField.getText()).matches() && !passwordField.getText().equals(passwordFieldCheck.getText()) && !(usernameField.getText().length() > 5)){
                        JOptionPane.showMessageDialog(null,"Uw email klopt niet. \n Uw wachtwoorden zijn niet hetzelfde. \n Uw gebruikersnaam is te kort. \n Pas ze aan om u te registreren");
                    }
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
