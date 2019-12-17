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
    private User loggedInUser;

    public SettingsScreen(User loggedInUser) {
        this.loggedInUser = loggedInUser;

        HomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new homeScreen(loggedInUser).homeView);
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

        //same for logout
        ImageIcon iiLogOut = new ImageIcon("img/logOutButton.png");
        Image imgLogOut = iiLogOut.getImage();
        Image newImgLogOut = imgLogOut.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        iiLogOut = new ImageIcon(newImgLogOut);
        logUitButton = new JButton(iiLogOut);
        // deleting background color and border
        logUitButton.setBackground(new Color(0,0,0,0));
        logUitButton.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        logUitButton.setContentAreaFilled(false);
    }
}