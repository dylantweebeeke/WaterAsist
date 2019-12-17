package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class homeScreen extends JFrame {
    private JButton wekelijksButton;
    private JButton maandelijksButton;
    public JPanel homeView;
    private JLabel homeText;
    private JLabel subTitleText;
    private JButton dagelijksVerbruikButton;
    private JButton settingsButton;
    private JLabel greetingLabel;
    public User loggedInUser;

    public homeScreen(User loggedInUser) {
        this.loggedInUser = loggedInUser;

        dagelijksVerbruikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new Dagelijks(loggedInUser).Dagelijksview);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        wekelijksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new Wekelijks(loggedInUser).Wekelijksview);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        maandelijksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new Maandelijks(loggedInUser).Maandelijksview);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new SettingsScreen(loggedInUser).SettingsPanel);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        greetingLabel.setText(getGreeting() + " , " + loggedInUser.getUsername());
    }

    private void createUIComponents() {
        //loading/resizing/linking image to an buttons
        ImageIcon iiSettings = new ImageIcon("img/settingsLogo.png");
        Image imgSettings = iiSettings.getImage();
        Image newImgSettings = imgSettings.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        iiSettings = new ImageIcon(newImgSettings);
        settingsButton = new JButton(iiSettings);
        // deleting background color and border
        settingsButton.setBackground(new Color(0,0,0,0));
        settingsButton.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        settingsButton.setContentAreaFilled(false);
    }

    private String getGreeting(){
        String greeting = "";
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay < 12) greeting = "goedenmorgen";
        else if(timeOfDay >= 12 && timeOfDay <= 17) greeting = "goedenmiddag";
        else if(timeOfDay >= 17 && timeOfDay <= 24) greeting = "goedenavond";
        return greeting;
    }
}
