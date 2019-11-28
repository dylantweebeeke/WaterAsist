package GUI;

import javax.swing.*;

public class SettingsScreen {
    private JTextField UsernameField;
    private JTextField MailField;
    private JButton HomeButton;
    private JTextField DagelijksField;
    private JTextField WeekelijksField;
    private JTextField MaandelijksField;
    private JPanel SettingsPanel;
    private JButton saveButton;

    public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        JFrame frame = new JFrame("Settings");
        frame.setContentPane(new SettingsScreen().SettingsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}