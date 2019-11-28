package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class startupscherm {
    public JPanel startupView;
    private JButton loginButton;
    private JButton registerButton;
    private JTextArea productName;

    public static void main(String[] args) {
        //creating new Jframe
        JFrame frame = new JFrame("Homescreen");
        frame.setContentPane(new startupscherm().startupView);

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
        frame.setSize(400, 500);
        //setting the frame to be visible
        frame.setVisible(true);
    }

}
