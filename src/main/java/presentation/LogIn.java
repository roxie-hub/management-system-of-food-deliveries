package presentation;

import business.DeliveryService;
import data.FileWr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LogIn extends JFrame{
    JPanel u = new JPanel();
    JPanel p = new JPanel();
    JPanel b = new JPanel();
    JPanel w = new JPanel();
    JButton log = new JButton("Log In");
    JButton sign = new JButton("Sign In");
    JLabel us = new JLabel("Username:");
    JLabel l = new JLabel("Welcome!");
    JLabel pas = new JLabel("Password: ");
    private JTextField text1 = new JTextField(20);
    JPasswordField text2 = new JPasswordField(20);
    public LogIn(DeliveryService ds){
        w.setBounds(0,0,450,50);
        b.setBounds(0,150,450,50);
        u.setBounds(0,50,450,50);
        p.setBounds(0,100,450,50);
        w.setBackground(new Color(120,225,225));
        b.setBackground(new Color(120,225,225));
        u.setBackground(new Color(120,225,225));
        p.setBackground(new Color(120,225,225));

        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileWr fw = new FileWr();
                String username,pass;
                username=text1.getText();
                pass=text2.getText();
                if(fw.getUserRole(username,pass) == null)
                {
                    JOptionPane.showMessageDialog(log, "Nu ai un cont!");
                }
                else if(fw.getUserRole(username,pass).equals("a")){
                    Administrator admin = new Administrator(ds);
                }
                else {
                    Client client = new Client(ds,text1.getText());
                }
            }
        });

        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileWr fw = new FileWr();
                String username,pass;
                username=text1.getText();
                pass=text2.getText();
                if(fw.verifyAcc(username,pass)==1){
                    JOptionPane.showMessageDialog(sign, "Contul deja exista!");
                } else {
                    fw.writeClientInFile(username, pass);
                    JOptionPane.showMessageDialog(sign, "Contul a fost adaugat!");
                }
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,250);
        this.setTitle("LogIN");
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(120,225,225));

        this.add(w);
        this.add(b);
        this.add(u);
        this.add(p);
        w.add(l);
        b.add(log);
        b.add(sign);
        u.add(us);
        u.add(text1);
        p.add(pas);
        p.add(text2);
    }
}
