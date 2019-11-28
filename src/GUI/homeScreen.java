package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class homeScreen extends JFrame {
    private JButton wekelijksButton;
    private JButton maandelijksButton;
    public JPanel homeView;
    private JLabel homeText;
    private JLabel subTitleText;
    private JButton dagelijksVerbruikButton;
    private JButton settingsButton;

    public homeScreen() {
        dagelijksVerbruikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new Dagelijks().Dagelijksview);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        wekelijksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new Wekelijks().Wekelijksview);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        maandelijksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new Maandelijks().Maandelijksview);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
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
        //creating new Jframe
        JFrame frame = new JFrame("Homescreen");
        frame.setContentPane(new homeScreen().homeView);

        //Making the window able to close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        //defining minimum and maximum size of the window
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
        //setting the frame to be visible
        frame.setVisible(true);
    }

    private void createUIComponents() {
        //loading/resizing/linking image to an button
        ImageIcon ii = new ImageIcon("img/settingsLogo.png");
        Image img = ii.getImage();
        Image newImg = img.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ii = new ImageIcon(newImg);
        settingsButton = new JButton(ii);

        //no opacity background and remove border
        settingsButton.setBackground(new Color(0,0,0,0));
        settingsButton.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        settingsButton.setContentAreaFilled(false);

    }
}
