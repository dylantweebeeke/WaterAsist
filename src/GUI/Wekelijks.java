package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Wekelijks extends JFrame{
    private JButton homeButton;
    private JButton instellingenButton;
    public JPanel Wekelijksview;

    public Wekelijks() {
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new homeScreen().homeView);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        instellingenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new SettingsScreen().SettingsPanel);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame ("Wekelijks");
        frame.setContentPane(new Wekelijks().Wekelijksview);
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
        //loading/resizing/linking image to an buttons
        ImageIcon iiSettings = new ImageIcon("img/settingsLogo.png");
        Image imgSettings = iiSettings.getImage();
        Image newImgSettings = imgSettings.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        iiSettings = new ImageIcon(newImgSettings);
        instellingenButton = new JButton(iiSettings);
        // deleting background color and border
        instellingenButton.setBackground(new Color(0,0,0,0));
        instellingenButton.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        instellingenButton.setContentAreaFilled(false);

        //same for home button
        ImageIcon iiHome = new ImageIcon("img/homeButton.png");
        Image imgHome = iiHome.getImage();
        Image newImgHome = imgHome.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        iiHome = new ImageIcon(newImgHome);
        homeButton = new JButton(iiHome);
        // deleting background color and border
        homeButton.setBackground(new Color(0,0,0,0));
        homeButton.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        homeButton.setContentAreaFilled(false);
    }
}
