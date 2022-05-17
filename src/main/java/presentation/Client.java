package presentation;

import business.BaseProduct;
import business.DeliveryService;
import business.MenuItem;
import business.Order;
import data.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static java.util.Collections.sort;

public class Client extends JFrame{
    MenuItem y;
    int p;
    JLabel label1 = new JLabel("Nume:    ");
    JLabel label2 = new JLabel("Rating:   ");
    JLabel label3 = new JLabel("Calorii:   ");
    JLabel label4 = new JLabel("Proteine:");
    JLabel label5 = new JLabel("Grasimi:  ");
    JLabel label6 = new JLabel("Sodiu:    ");
    JLabel label7 = new JLabel("Pret:       ");
    JLabel label8 = new JLabel("Pret Total:");
    JTable table;
    JButton button1 = new JButton("SEARCH");
    JButton button2 = new JButton("MENU");
    JButton button3 = new JButton("ORDER");
    JTextField text1 = new JTextField(12);
    JTextField text2 = new JTextField(12);
    JTextField text3 = new JTextField(12);
    JTextField text4 = new JTextField(12);
    JTextField text5 = new JTextField(12);
    JTextField text6 = new JTextField(12);
    JTextField text7 = new JTextField(12);
    JTextField text8 = new JTextField(15);
    JScrollPane scroll;
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel5 = new JPanel();
    JLabel label = new JLabel();
    ImageIcon image = new ImageIcon("C:/Users/omega/Downloads/Imagine1.png");
    public Client(DeliveryService ds,String name){
        ArrayList<MenuItem> items= new ArrayList<MenuItem>();
        panel1.setBounds(0,0,200,250);
        panel1.setBackground(new Color(120,225,225));

        panel2.setBackground(new Color(120,225,225));
        panel2.setBounds(200,0,500,450);

        panel3.setBackground(new Color(120,225,225));
        panel3.setBounds(100,450,700,50);

        panel5.setBounds(0,220,220,200);
        panel5.setBackground(new Color(120,225,225));

        label.setIcon(image);

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value0 = text1.getText();
                String value1 = text2.getText();
                String value2 = text3.getText();
                String value3 = text4.getText();
                String value4 = text5.getText();
                String value5 = text6.getText();
                String value6 = text7.getText();
                ArrayList<MenuItem> rez=ds.getMi();
                if(value0!=""){
                rez = ds.prodByName(rez,value0);}
                if(!value1.isEmpty()) {
                    rez = ds.prodByRating(rez,Float.parseFloat(value1));
                }
                if(!value2.isEmpty()) {
                    rez = ds.prodByCals(rez,Integer.parseInt(value2));
                }
                if(!value3.isEmpty()) {
                    rez = ds.prodByProtein(rez,Integer.parseInt(value3));
                }
                if(!value4.isEmpty()) {
                    rez = ds.prodByFat(rez,Integer.parseInt(value4));
                }
                if(!value5.isEmpty()) {
                    rez = ds.prodBySodium(rez,Integer.parseInt(value5));
                }
                if(!value6.isEmpty()) {
                    rez = ds.prodByPrice(rez,Integer.parseInt(value6));
                }
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                tm.setRowCount(0);
                for(MenuItem m: rez){
                    tm.addRow(new Object[]{m.getName(),m.getRating(),m.getCalories(),m.getProtein(),m.getFat(),m.getSodium(),m.getPrice()});
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> elements = ds.getMi();
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                sort(elements);
                tm.setRowCount(0);
                for(MenuItem m: elements){
                    tm.addRow(new Object[]{m.getName(),m.getRating(),m.getCalories(),m.getProtein(),m.getFat(),m.getSodium(),m.getPrice()});
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order ord = new Order(name, new Date(),Integer.parseInt(text8.getText()));
                ds.addToHash(items,ord);
                p=0;
                try {
                    Serializator.Serializare(ds);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ds.getHash().size();
            }
        });

        String[] coloane = {"NAME","RATING","CALORIES","PROTEIN","FAT","SODIUM","PRICE"};
        String[][] data={};
        table = new JTable();
        table.setModel(new DefaultTableModel(data,coloane));
        table.setFillsViewportHeight(true);
        scroll = new JScrollPane(table);
        panel2.add(scroll);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                if(table.getSelectedRow()!=-1){
                    String value0 = table.getModel().getValueAt(table.getSelectedRow(),0).toString();
                    String value1 = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
                    String value2 = table.getModel().getValueAt(table.getSelectedRow(),2).toString();
                    String value3 = table.getModel().getValueAt(table.getSelectedRow(),3).toString();
                    String value4 = table.getModel().getValueAt(table.getSelectedRow(),4).toString();
                    String value5 = table.getModel().getValueAt(table.getSelectedRow(),5).toString();
                    String value6 = table.getModel().getValueAt(table.getSelectedRow(),6).toString();
                    y = new MenuItem(value0, Float.parseFloat(value1), Integer.parseInt(value2),Integer.parseInt(value3),Integer.parseInt(value4),Integer.parseInt(value5),Integer.parseInt(value6));
                    p+=Integer.parseInt(value6);
                    items.add(y);
                    text8.setText(String.valueOf(p));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.setSize(700,550);
        this.setTitle("Client");
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(120,225,225));

        this.add(panel5);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        panel1.add(label1);
        panel1.add(text1);
        panel1.add(label2);
        panel1.add(text2);
        panel1.add(label3);
        panel1.add(text3);
        panel1.add(label4);
        panel1.add(text4);
        panel1.add(label5);
        panel1.add(text5);
        panel1.add(label6);
        panel1.add(text6);
        panel1.add(label7);
        panel1.add(text7);
        panel1.add(button1);
        panel3.add(button2);
        panel3.add(label8);
        panel3.add(text8);
        panel5.add(label);
        panel3.add(button3);
    }
}
