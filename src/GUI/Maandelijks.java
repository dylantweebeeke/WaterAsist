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

public class Maandelijks extends JFrame{
    private JButton homeButton;
    private JButton instellingenButton;
    public JPanel Maandelijksview;
    private JPanel maandPanel;
    private JLabel maandLimietLabel;
    private JScrollPane scroll;
    private User loggedInUser;

    public Maandelijks(User loggedInUser) {
        this.loggedInUser = loggedInUser;

        maandPanel.setLayout(new BoxLayout(maandPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0,4);
        panel.setLayout(layout);
        panel.setSize(800,700);
        panel.setBackground(new Color(56,149,211));

        ArrayList<Usage> monthlyUsage = getMonthlyUsage();
        for (int i = 0; i < monthlyUsage.size(); i++){
            String year = monthlyUsage.get(i).getDate().toString().substring(0,4);
            String format = String.format("%-30s %-30s %-30s",getMonthWithMonthNumber(monthlyUsage.get(i).getMaandnr()) + " " + year,monthlyUsage.get(i).getDaggebruik(),"Liter");


            String txt = getMonthWithMonthNumber(monthlyUsage.get(i).getMaandnr());
            JLabel label = new JLabel(txt);
            label.setFont(label.getFont().deriveFont(16.0f));

            String txt1 = monthlyUsage.get(i).getDate().toString().substring(0,4);
            JLabel label1 = new JLabel(txt1);
            label.setFont(label1.getFont().deriveFont(16.0f));

            String txt2 = monthlyUsage.get(i).getDaggebruik() + "";
            JLabel label2 = new JLabel(txt2);
            label.setFont(label2.getFont().deriveFont(16.0f));

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
        maandPanel.add(scroll);

        Limit limit = new Limit();
        limit.getMonthlyLimit(loggedInUser,getMonthNumber());
        for(int j = 0; j < monthlyUsage.size(); j++){
            if (monthlyUsage.get(j).getMaandnr() == getMonthNumber()){
                System.out.println(monthlyUsage.get(j).getDaggebruik());
                System.out.println(limit.getMaandlimiet());
                maandLimietLabel.setText(getPercentageUsed(monthlyUsage.get(j).getDaggebruik(),limit.getMaandlimiet()));
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

    public ArrayList<Usage> getMonthlyUsage(){
        Usage usage = new Usage();
        return (ArrayList<Usage>) usage.getUsageTotalByMonth(loggedInUser.getGebruikersnr());
    }

    public String getMonthWithMonthNumber(int monthNumber){
        String month = "";
        switch(monthNumber){
            case 1 :
                month = "Januari";
                break;
            case 2:
                month = "Februari";
                break;
            case 3:
                month = "Maart";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "Mei";
                break;
            case 6:
                month = "Juni";
                break;
            case 7:
                month = "Juli";
                break;
            case 8:
                month = "Augustus";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "Oktober";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;

        }
        return month;
    }

    public String getPercentageUsed(double part, int total){
        String usage = "";
        if(total == 0){
            usage = "U heeft nog geen limiet ingestelt, U kunt deze instellen bij de instellingen";
        } else if (total > 0){
            double result = part * 100 / total;
            usage = String.format("U heeft op dit moment %.2f %% van uw maand limiet verbruikt.", result);
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

    /**
     * get the number of the current month based on the current date where January = 1
     * @return current month number
     */
    public static  int getMonthNumber(){
        Calendar cal2 = new GregorianCalendar();
        Date date = new Date();
        cal2.setTime(date);
        return cal2.get(Calendar.MONTH) + 1;
    }
}

