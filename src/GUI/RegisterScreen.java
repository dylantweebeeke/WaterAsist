package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class RegisterScreen extends  JFrame {
    public JPanel RegisterPanel;
    private JTextField UsernameField;
    private JButton RegisterButton;
    private JTextField MailField;

    public RegisterScreen() {
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new homeScreen().homeView);
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
}
