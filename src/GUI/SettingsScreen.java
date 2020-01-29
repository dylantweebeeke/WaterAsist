package GUI;

import javafx.scene.web.PromptData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class SettingsScreen {
    private JTextField UsernameField;
    private JTextField MailField;
    private JButton HomeButton;
    private JTextField DagelijksField;
    private JTextField WeekelijksField;
    private JTextField MaandelijksField;
    public JPanel SettingsPanel;
    private JButton opslaanButtonDoel;
    private JButton logUitButton;
    private JButton opslaanButtonGegevens;
    private JPasswordField oldPassfield;
    private JTextField NewPassField;
    private JTextField newPassFieldCheck;
    private JButton opslaanWachtwoord;
    private JLabel currentDailyLimit;
    private JLabel currentWeekylLimit;
    private JLabel currentMonthlyLimit;

    private ImageIcon bg;
    private JLabel bgImage;

    private User loggedInUser;
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/waterassist";
    //DB credentials
    static final String USER = "root";
    static final String PASS = "Welkom01";

    public SettingsScreen(User loggedInUser) {
        this.loggedInUser = loggedInUser;

        Limit limit = new Limit();
        limit.getLimit(loggedInUser.getGebruikersnr(),settingsController.getDateInRightFormat());
        String dayLimitText = "Uw huidige limiet is " + limit.getDaglimiet();
        currentDailyLimit.setText(dayLimitText);

        limit.getWeeklyLimit(loggedInUser, settingsController.getWeekNumber());
        String weekLimitText = "Uw huidige limiet is " + limit.getWeeklimiet();
        currentWeekylLimit.setText(weekLimitText);

        limit.getMonthlyLimit(loggedInUser,settingsController.getMonthNumber());
        String monthLimitText = "Uw huidige limiet is "  + limit.getMaandlimiet();
        currentMonthlyLimit.setText(monthLimitText);

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

        opslaanWachtwoord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (settingsController.checkIfCorrectPass(loggedInUser, oldPassfield.getText())){
                    if (!NewPassField.getText().equals(newPassFieldCheck.getText())){
                        JOptionPane.showMessageDialog(null,"De wachtwoorden zijn niet het zelfde!");
                        NewPassField.setText("");
                        newPassFieldCheck.setText("");
                    } else {
                        settingsController.updatePassword(loggedInUser ,NewPassField.getText());
                        JOptionPane.showMessageDialog(null,"Uw wachtwoord is aangepast!");
                        oldPassfield.setText("");
                        NewPassField.setText("");
                        newPassFieldCheck.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Uw oude wachtwoord is niet correct!");
                    oldPassfield.setText("");
                    NewPassField.setText("");
                    newPassFieldCheck.setText("");
                }
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


        opslaanButtonGegevens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object[] options = {"ja", "nee"};
                JPanel panel = new JPanel();
                panel.add(new JLabel("Weet u zeker dat u uw gegevens wilt aanpassen?"));
                int result = JOptionPane.showOptionDialog(null,panel,"",
                        JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options, null);
                if (result == JOptionPane.YES_OPTION) {
                    if (MailField.getText().equals("")) {
                        settingsController.updateUsername(loggedInUser, UsernameField.getText());
                    } else if (UsernameField.getText().equals("")) {
                        settingsController.updateEmail(loggedInUser, MailField.getText());
                    }
                    JOptionPane.showMessageDialog(null,"Uw gegevens zijn aangepast");
                    UsernameField.setText("");
                    MailField.setText("");
                }
            }
        });

        opslaanButtonDoel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //check for every possibility for different kinds of inputs
                //based on this the filled in limits will be set or updated into the DB

                //Nothing filled in
                if (DagelijksField.getDocument().getLength() > 9 || WeekelijksField.getText().length() > 9 || MaandelijksField.getText().length() > 9) {
                    JOptionPane.showMessageDialog(null, "Kom op man niemand heeft zoveel water nodig ;-) \n Zet even een realistisch aantal neer (minimaal minder dan 999999999!).");
                    DagelijksField.setText("");
                    WeekelijksField.setText("");
                    MaandelijksField.setText("");
                } else {
                    if (DagelijksField.getText().equals("") && WeekelijksField.getText().equals("") && MaandelijksField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "U heeft geen van de velden ingevult. \n Vul minimaal 1 van de velden in om uw limiet aan te passen.");
                        //Only monthly filled in
                    } else if (DagelijksField.getText().equals("") && WeekelijksField.getText().equals("")) {
                        if (settingsController.checkMonthlyExsists()) {
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Maandelijkse limiet is aangepast!");
                        } else {
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Maandelijkse limiet is ingestelt!");
                        }
                        //Only Daily filled in
                    } else if (WeekelijksField.getText().equals("") && MaandelijksField.getText().equals("")) {
                        if (settingsController.checkDailyExists()) {
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Dagelijkse limiet is aangepast!");
                        } else {
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Dagelijkse limiet is ingestelt!");
                        }
                        //only weekly filled in
                    } else if (DagelijksField.getText().equals("") && MaandelijksField.getText().equals("")) {
                        if (settingsController.checkWeeklyExists()) {
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Wekelijkse limiet is aangepast!");
                        } else {
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Wekelijkse limiet is ingestelt!");
                        }
                        //Weekly and Monthly filled in
                    } else if (DagelijksField.getText().equals("")) {
                        if (settingsController.checkMonthlyExsists() && settingsController.checkWeeklyExists()) {
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Maandelijkse en Wekelijkse limiet zijn aangepast!");
                        } else if (settingsController.checkMonthlyExsists() && !settingsController.checkWeeklyExists()) {
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Maandelijkse limiet is aangepast en uw Wekelijkse limiet is ingestelt!");
                        } else if (!settingsController.checkMonthlyExsists() && settingsController.checkWeeklyExists()) {
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Maandelijkse limiet is ingestelt en uw Wekelijkse limiet zijn aangepast!");
                        } else {
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Weeklijkse en Maandelijkse limiet is ingestelt!");
                        }
                        //Weekly and Daily filled in
                    } else if (MaandelijksField.getText().equals("")) {
                        if (settingsController.checkWeeklyExists() && settingsController.checkDailyExists()) {
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Wekelijkse en Dagelijkse limiet zijn aangepast!");
                        } else if (settingsController.checkWeeklyExists() && !settingsController.checkDailyExists()) {
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Wekelijkse limiet is aangepast en uw Dagelijkse limiet is ingestelt!");
                        } else if (!settingsController.checkWeeklyExists() && settingsController.checkDailyExists()) {
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Wekelijks limiet is ingestelt en uw Dagelijkse limiet zijn aangepast!");
                        } else {
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Wekelijkse en Dagelijkse limiet is ingestelt!");
                        }
                        //Daily and Monthly filled in
                    } else if (WeekelijksField.getText().equals("")) {
                        if (settingsController.checkMonthlyExsists() && settingsController.checkDailyExists()) {
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Maandelijkse en Dagelijkse limiet zijn aangepast!");
                        } else if (settingsController.checkMonthlyExsists() && !settingsController.checkDailyExists()) {
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Maandelijkse limiet is aangepast en uw Dagelijkse limiet is ingestelt!");
                        } else if (!settingsController.checkMonthlyExsists() && settingsController.checkDailyExists()) {
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Maandelijkse limiet is ingestelt en uw Dagelijkse limiet zijn aangepast!");
                        } else {
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Dagelijkse en Maandelijkse limiet is ingestelt!");
                        }
                        //All filled in
                    } else {
                        if (settingsController.checkDailyExists() && settingsController.checkWeeklyExists() && settingsController.checkMonthlyExsists()) {
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw wekelijkse, maandelijkse en dagelijkse limieten zijn aangepast");
                        } else if (settingsController.checkDailyExists() && !settingsController.checkWeeklyExists() && !settingsController.checkMonthlyExsists()) {
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Dagelijkse limiet is aangepast en uw wekelijkse en maandelijkse limiet ingestelt!");
                        } else if (!settingsController.checkDailyExists() && settingsController.checkWeeklyExists() && !settingsController.checkMonthlyExsists()) {
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw wekelijkse limiet is aangepast en uw Maandelijskte en Dagelijkse limiet ingestelt!");
                        } else if (!settingsController.checkDailyExists() && !settingsController.checkWeeklyExists() && settingsController.checkMonthlyExsists()) {
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw maandelijkse limiet is aangepast en uw wekelijkse en dagelijkse limiet ingestelt!");
                        } else if (!settingsController.checkDailyExists() && settingsController.checkWeeklyExists() && settingsController.checkMonthlyExsists()) {
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw dagelijkse limiet is ingestelt en uw wekelijkse en maandelijkse limiet ingestelt!");
                        } else if (settingsController.checkDailyExists() && !settingsController.checkWeeklyExists() && settingsController.checkMonthlyExsists()) {
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.updateMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw weeklijkse limiet is ingestelt en uw dagelijkse en maandelijkse aangepast!");
                        } else if (settingsController.checkDailyExists() && settingsController.checkWeeklyExists() && !settingsController.checkMonthlyExsists()) {
                            settingsController.updateDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.updateWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw maandelijkse limiet is ingestelt en uw dagelijkse en weeklijkse aangepast!");
                        } else {
                            settingsController.setDailyLimit(loggedInUser, Integer.parseInt(DagelijksField.getText()));
                            settingsController.setWeeklyLimit(loggedInUser, Integer.parseInt(WeekelijksField.getText()));
                            settingsController.setMonthlyLimit(loggedInUser, Integer.parseInt(MaandelijksField.getText()));
                            JOptionPane.showMessageDialog(null, "Uw Dagelijkse , Wekelijkse en Maandelijkse limiet is ingestelt!");
                        }

                    }

                    DagelijksField.setText("");
                    WeekelijksField.setText("");
                    MaandelijksField.setText("");

                    Limit limit = new Limit();
                    limit.getLimit(loggedInUser.getGebruikersnr(), settingsController.getDateInRightFormat());
                    String dayLimitText = "Uw huidige limiet is " + limit.getDaglimiet();
                    currentDailyLimit.setText(dayLimitText);

                    limit.getWeeklyLimit(loggedInUser, settingsController.getWeekNumber());
                    String weekLimitText = "Uw huidige limiet is " + limit.getWeeklimiet();
                    currentWeekylLimit.setText(weekLimitText);

                    limit.getMonthlyLimit(loggedInUser, settingsController.getMonthNumber());
                    String monthLimitText = "Uw huidige limiet is " + limit.getMaandlimiet();
                    currentMonthlyLimit.setText(monthLimitText);
                }
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