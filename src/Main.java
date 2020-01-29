import GUI.ImagePanel;
import GUI.Login;
import GUI.LoginScreen;
import GUI.startupscherm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        ImagePanel panel = new ImagePanel(new ImageIcon("img/background.jpg").getImage());

        //creating new Jframe
        JFrame frame = new JFrame("Water Assist");
        frame.setContentPane(new startupscherm().startupView);

        //Making the window able to close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        //defining minimum and maximum size of the window
        frame.setMaximumSize(new Dimension(900,900));
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
        frame.setSize(900, 900);
        //Setting the frame to not be able to resize
        frame.setResizable(false);
        //setting the frame to be visible
        frame.setVisible(true);
    }
}