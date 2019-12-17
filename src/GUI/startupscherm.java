package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class startupscherm extends JFrame {
    public JPanel startupView;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel logoField;
    private JTextArea productName;

    public startupscherm() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new LoginScreen().LoginPanel);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new RegisterScreen().RegisterPanel);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });
    }

    private void createUIComponents() {
        //same for home button
        ImageIcon iiLogo = new ImageIcon("img/tempLogo.png");
        Image imgLogo = iiLogo.getImage();
        Image newImgLogo = imgLogo.getScaledInstance(500,500, Image.SCALE_SMOOTH);
        iiLogo = new ImageIcon(newImgLogo);

        logoField = new JLabel(iiLogo);

    }
}
