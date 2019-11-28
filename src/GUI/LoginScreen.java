package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginScreen {
    public JPanel LoginPanel;
    private JTextField usernameTextfield;
    private JButton loginButton;
    private JButton backButton;


    public LoginScreen() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameTextfield.getText().equals("test")) {
                    Component component = (Component) e.getSource();
                    JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                    frame.setContentPane(new homeScreen().homeView);
                    frame.setVisible(true);
                    System.out.println("New window opened!");
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
