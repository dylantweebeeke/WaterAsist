package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class Dagelijks extends JFrame {
    public JPanel Dagelijksview;
    private JButton homeButton;
    private JButton instellingenButton;
    private JPanel dailyUsagePanel;
    private User loggedInUser;
    private JScrollPane scroll;
    private User getLoggedInUser;

    public Dagelijks(User loggedInUser) {
        this.loggedInUser = loggedInUser;

            dailyUsagePanel.setLayout(new BoxLayout(dailyUsagePanel, BoxLayout.Y_AXIS));

            JPanel panel = new JPanel();
            GridLayout layout = new GridLayout(0,4);
            panel.setLayout(layout);
            panel.setSize(800,700);
            panel.setBackground(new Color(56,149,211));

        ArrayList<Usage> test = getDailyUsage();
        for (int i = 0; i < test.size(); i++){

            String date = test.get(i).getDate().toString();
            String txt = getDayInDutch(getDayWithDate(test.get(i).getDate())) + " ";
            JLabel label = new JLabel(txt);
            label.setFont(label.getFont().deriveFont(16.0f));

            String txt1 = date + " ";
            JLabel label1 = new JLabel(txt1);
            label1.setFont(label1.getFont().deriveFont(16.0f));

            String txt2 =  test.get(i).getDaggebruik() + " ";
            JLabel label2 = new JLabel(txt2);
            label2.setFont(label2.getFont().deriveFont(16.0f));

            String txt3 = "Liter.";
            JLabel label3 = new JLabel(txt3);
            label3.setFont(label3.getFont().deriveFont(16.0f));

            panel.add(label);
            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
        }

        scroll = new JScrollPane(panel);
        scroll.setBounds(50,50,200,500);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(500,500));
        dailyUsagePanel.add(scroll);


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new homeScreen(loggedInUser).homeView);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });

        instellingenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setContentPane(new SettingsScreen(loggedInUser).SettingsPanel);
                frame.setVisible(true);
                System.out.println("New window opened!");
            }
        });
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

    public static String getDayWithDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        return simpleDateFormat.format(date);
    }

    public ArrayList<Usage> getDailyUsage(){
        Usage usage = new Usage();
        return (ArrayList<Usage>) usage.getUsageTotalByDay(this.loggedInUser.getGebruikersnr());
    }

    public String getDayInDutch(String day){
        String dayDutch = "";
        switch (day){
            case "Monday":
                dayDutch = "Maandag";
                break;
            case "Tuesday":
                dayDutch = "Dinsdag";
                break;
            case "Wednesday":
                dayDutch = "Woensdag";
                break;
            case "Thursday":
                dayDutch = "Donderdag";
                break;
            case "Friday":
                dayDutch = "Vrijdag";
                break;
            case "Saturday":
                dayDutch = "Zaterdag";
                break;
            case "Sunday":
                dayDutch = "Zondag";
                break;
            default:
                dayDutch = day;
        }
        return dayDutch;
    }
}

