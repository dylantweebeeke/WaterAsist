package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Wekelijks extends JFrame{
    private JButton homeButton;
    private JButton instellingenButton;
    public JPanel Wekelijksview;
    private JPanel weekPanel;
    private JLabel weeklyLimitLabel;
    private JScrollPane scroll;
    private User loggedInUser;

    public Wekelijks(User loggedInUser) {
        this.loggedInUser = loggedInUser;

        weekPanel.setLayout(new BoxLayout(weekPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0,4);
        panel.setLayout(layout);
        panel.setSize(800,700);
        panel.setBackground(new Color(56,149,211));

        ArrayList<Usage> weekUsage = getWeeklyUsage();
        for (int i = 0; i < weekUsage.size(); i++){
            String txt = "week" + weekUsage.get(i).getWeeknr();
            JLabel label = new JLabel(txt);
            label.setFont(label.getFont().deriveFont(16.0f));
            String txt1 = weekUsage.get(i).getDate().toString().substring(0,4);
            JLabel label1 = new JLabel(txt1);
            label1.setFont(label1.getFont().deriveFont(16.0f));
            String txt2 = Double.toString(weekUsage.get(i).getDaggebruik());
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
        weekPanel.add(scroll);

        Limit limit = new Limit();
        limit.getWeeklyLimit(loggedInUser,getWeekNumber());
        String percentage = "";
        for(int j = 0; j < weekUsage.size(); j++){
            if (weekUsage.get(j).getWeeknr() == getWeekNumber()){
                System.out.println(weekUsage.get(j).getDaggebruik());
                System.out.println(limit.getWeeklimiet());
                weeklyLimitLabel.setText(getPercentageUsed(weekUsage.get(j).getDaggebruik(),limit.getWeeklimiet()));
            }
        }

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

    public  ArrayList<Usage> getWeeklyUsage(){
        Usage usage = new Usage();
        return (ArrayList<Usage>) usage.getUsageTotalByWeek(loggedInUser.getGebruikersnr());
    }

    public String getPercentageUsed(double part, int total){
        String usage = "";
        if(total == 0){
            usage = "U heeft nog geen limiet ingestelt, U kunt deze instellen bij de instellingen";
        } else if (total > 0){
            double result = part * 100 / total;
            usage = String.format("U heeft op dit moment %.2f %% van uw week limiet verbruikt.", result);
        }
        return usage;
    }

    /**
     * get the number of the current week based on the current date
     * @return current week number
     */
    public static int getWeekNumber(){
        Calendar cal = new GregorianCalendar();
        Date date = new Date();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
}
