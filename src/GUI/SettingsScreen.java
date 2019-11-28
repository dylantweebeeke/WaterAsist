package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        ImageIcon iiHome = new ImageIcon("img/homeButton.png");
        Image imgHome = iiHome.getImage();
        Image newImgHome = imgHome.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        iiHome = new ImageIcon(newImgHome);
        HomeButton = new JButton(iiHome);
        // deleting background color and border
        HomeButton.setBackground(new Color(0,0,0,0));
        HomeButton.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        HomeButton.setContentAreaFilled(false);
    }
}