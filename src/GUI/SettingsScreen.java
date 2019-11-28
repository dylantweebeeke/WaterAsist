package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsScreen {
    private JTextField UsernameField;
    private JTextField MailField;
    private JButton HomeButton;
    private JTextField DagelijksField;
    private JTextField WeekelijksField;
    private JTextField MaandelijksField;
    public JPanel SettingsPanel;
    private JButton saveButton;
    private JButton logUitButton;

    public SettingsScreen() {
        HomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new homeScreen().homeView);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        logUitButton.addActionListener(new ActionListener() {
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
        JFrame frame = new JFrame("Settings");
        frame.setContentPane(new SettingsScreen().SettingsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}