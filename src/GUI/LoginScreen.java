package GUI;

import javax.swing.*;

public class LoginScreen {
    public JPanel LoginPanel;
    private JTextField textField1;
    private JButton loginButton;


    public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginScreen().LoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
