package GUI;

import javax.swing.*;

public class Dagelijks {
    private JButton button1;
    private JButton button2;
    private JTextField dagelijksGebruikTextField;
    private JTextField uwMaximaleDoelstellingIsTextField;
    private JTextField zondagLiterTextField;
    private JTextField maandagLiterTextField;
    private JTextField zaterdagLiterTextField;
    private JTextField vrijdagLiterTextField;
    private JTextField dinsdagLiterTextField;
    private JTextField woensdagLiterTextField;
    private JTextField donderdagLiterTextField;

    public static void main(String[] args) {
        JFrame frame = new JFrame ("Dagelijks");
        frame.setContentPane(new Dagelijks().hoofdscherm);
    }
}

