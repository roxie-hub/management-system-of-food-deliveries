package presentation;

import business.CompositeProduct;
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
import java.util.HashMap;

import static java.util.Collections.sort;

public class Administrator extends JFrame{
    MenuItem y;
   JLabel label1 = new JLabel("Nume:    ");
   JLabel label2 = new JLabel("Rating:   ");
   JLabel label3 = new JLabel("Calorii:   ");
   JLabel label4 = new JLabel("Proteine:");
   JLabel label5 = new JLabel("Grasimi:  ");
   JLabel label6 = new JLabel("Sodiu:    ");
   JLabel label7 = new JLabel("Pret:       ");
   JLabel label8 = new JLabel("Nume meniu:");
   JTable table;
    JButton button1 = new JButton("ADD");
    JButton button2 = new JButton("IMPORT");
    JButton button3 = new JButton("DELETE");
    JButton button4 = new JButton("MODIFY");
    JButton button5 = new JButton("COMPOSITE");
    JButton button6 = new JButton("RAPORT1");
    JButton button7 = new JButton("RAPORT2");
    JButton button8 = new JButton("RAPORT3");
    JButton button9 = new JButton("RAPORT4");
   JTextField text1 = new JTextField(12);
    JTextField text2 = new JTextField(12);
    JTextField text3 = new JTextField(12);
    JTextField text4 = new JTextField(12);
    JTextField text5 = new JTextField(12);
    JTextField text6 = new JTextField(12);
    JTextField text7 = new JTextField(12);
    JTextField text8 = new JTextField(15);
    JTextField text9 = new JTextField(13);
    JTextField text10 = new JTextField(13);
    JScrollPane scroll;
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JLabel label = new JLabel();
    ImageIcon image = new ImageIcon("C:/Users/omega/Downloads/Imagine1.png");
    public Administrator(DeliveryService ds){
        ArrayList<MenuItem> items= new ArrayList<MenuItem>();
        panel1.setBounds(0,0,200,250);
        panel1.setBackground(new Color(120,225,225));

        panel2.setBackground(new Color(120,225,225));
        panel2.setBounds(200,0,500,450);

        panel3.setBackground(new Color(120,225,225));
        panel3.setBounds(0,450,700,50);

        panel4.setBackground(new Color(120,225,225));
        panel4.setBounds(0,500,700,50);

        panel5.setBounds(0,220,210,200);
        panel5.setBackground(new Color(120,225,225));

        label.setIcon(image);

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                String name = text8.getText();
                CompositeProduct x = new CompositeProduct(name,items);
                ds.addItem(x);
                tm.addRow(new Object[]{x.getName(),x.getRating(),x.getCalories(),x.getProtein(),x.getFat(),x.getSodium(),x.getPrice()});
                items.clear();
                try {
                    Serializator.Serializare(ds);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str="";
                ArrayList<Order> o;
                o=ds.timeInterval(Integer.parseInt(text9.getText()),Integer.parseInt(text10.getText()));
                for(Order i: o){
                    str+=i.toString()+"\n";
                }
                Rapoarte r1 = new Rapoarte(str);
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str="";
                ArrayList<MenuItem> x;
                x=ds.moreThenMyNumber(Integer.parseInt(text9.getText()));
                for (MenuItem m: x){
                    str+="Produsul "+ m.getName() +"\n";
                }
                Rapoarte r2 = new Rapoarte(str);

            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str="";
                ArrayList<String> o;
                o=ds.moreThenMyNumberAndMyValue(Integer.parseInt(text9.getText()),Integer.parseInt(text10.getText()));
                for(String i: o){
                    str+=i+"\n";
                }
                Rapoarte r3 = new Rapoarte(str);
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str="";
                ArrayList<MenuItem> x;
                HashMap<MenuItem,Integer> o;
                o=ds.inMyDay(Integer.parseInt(text9.getText()));
                x= new ArrayList<>(o.keySet());
                for (MenuItem m: x){
                    str+= m.getName() + "a fost cumparat de: " + o.get(m) + " ori\n";
                }
                Rapoarte r4 = new Rapoarte(str);
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                String value0 = text1.getText();
                String value1 = text2.getText();
                String value2 = text3.getText();
                String value3 = text4.getText();
                String value4 = text5.getText();
                String value5 = text6.getText();
                String value6 = text7.getText();
                MenuItem x = new MenuItem(value0, Float.parseFloat(value1), Integer.parseInt(value2),Integer.parseInt(value3),Integer.parseInt(value4),Integer.parseInt(value5),Integer.parseInt(value6));
                tm.addRow(new Object[]{x.getName(),x.getRating(),x.getCalories(),x.getProtein(),x.getFat(),x.getSodium(),x.getPrice()});
                ds.addItem(x);
                try {
                    Serializator.Serializare(ds);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sr = table.getSelectedRow();
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                String value0 = text1.getText();
                String value1 = text2.getText();
                String value2 = text3.getText();
                String value3 = text4.getText();
                String value4 = text5.getText();
                String value5 = text6.getText();
                String value6 = text7.getText();
                MenuItem x = new MenuItem(value0, Float.parseFloat(value1), Integer.parseInt(value2),Integer.parseInt(value3),Integer.parseInt(value4),Integer.parseInt(value5),Integer.parseInt(value6));
                tm.setValueAt(text1.getText(),sr,0);
                tm.setValueAt(text2.getText(),sr,1);
                tm.setValueAt(text3.getText(),sr,2);
                tm.setValueAt(text4.getText(),sr,3);
                tm.setValueAt(text5.getText(),sr,4);
                tm.setValueAt(text6.getText(),sr,5);
                tm.setValueAt(text7.getText(),sr,6);
                ds.modifyItem(y,x);
                try {
                    Serializator.Serializare(ds);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                items.clear();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                if(table.getSelectedRow()!=-1){
                    String value0 = table.getModel().getValueAt(table.getSelectedRow(),0).toString();
                    String value1 = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
                    String value2 = table.getModel().getValueAt(table.getSelectedRow(),2).toString();
                    String value3 = table.getModel().getValueAt(table.getSelectedRow(),3).toString();
                    String value4 = table.getModel().getValueAt(table.getSelectedRow(),4).toString();
                    String value5 = table.getModel().getValueAt(table.getSelectedRow(),5).toString();
                    String value6 = table.getModel().getValueAt(table.getSelectedRow(),6).toString();
                    MenuItem x = new MenuItem(value0, Float.parseFloat(value1), Integer.parseInt(value2),Integer.parseInt(value3),Integer.parseInt(value4),Integer.parseInt(value5),Integer.parseInt(value6));
                    ds.deleteItem(x);
                    tm.removeRow(table.getSelectedRow());
                    try {
                        Serializator.Serializare(ds);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    items.clear();
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ds.importProd();
                try {
                    Serializator.Serializare(ds);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ArrayList<MenuItem> elements = ds.getMi();
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                sort(elements);
                tm.setRowCount(0);
                for(MenuItem m: elements){
                    tm.addRow(new Object[]{m.getName(),m.getRating(),m.getCalories(),m.getProtein(),m.getFat(),m.getSodium(),m.getPrice()});
                }
            }
        });
        String[] coloane = {"NAME","RATING","CALORIES","PROTEIN","FAT","SODIUM","PRICE"};
        String[][] data={};
        table = new JTable();
        table.setModel(new DefaultTableModel(data,coloane));
        table.setFillsViewportHeight(true);
        scroll = new JScrollPane(table);
        panel2.add(scroll);

        ArrayList<MenuItem> elements = ds.getMi();
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        sort(elements);
        tm.setRowCount(0);
        for(MenuItem m: elements){
            tm.addRow(new Object[]{m.getName(),m.getRating(),m.getCalories(),m.getProtein(),m.getFat(),m.getSodium(),m.getPrice()});
        }

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                DefaultTableModel tm = (DefaultTableModel) table.getModel();
                if(table.getSelectedRow()!=-1){
                    String value0 = table.getModel().getValueAt(table.getSelectedRow(),0).toString();
                    String value1 = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
                    String value2 = table.getModel().getValueAt(table.getSelectedRow(),2).toString();
                    String value3 = table.getModel().getValueAt(table.getSelectedRow(),3).toString();
                    String value4 = table.getModel().getValueAt(table.getSelectedRow(),4).toString();
                    String value5 = table.getModel().getValueAt(table.getSelectedRow(),5).toString();
                    String value6 = table.getModel().getValueAt(table.getSelectedRow(),6).toString();
                    text1.setText(value0);
                    text2.setText(value1);
                    text3.setText(value2);
                    text4.setText(value3);
                    text5.setText(value4);
                    text6.setText(value5);
                    text7.setText(value6);
                    y = new MenuItem(value0, Float.parseFloat(value1), Integer.parseInt(value2),Integer.parseInt(value3),Integer.parseInt(value4),Integer.parseInt(value5),Integer.parseInt(value6));
                    items.add(y);
                }
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

        this.setSize(700,600);
        this.setTitle("Admin");
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(120,225,225));

        this.add(panel5);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
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
        panel3.add(button3);
        panel3.add(button4);
        panel3.add(label8);
        panel3.add(text8);
        panel3.add(button5);
        panel4.add(text9);
        panel4.add(text10);
        panel4.add(button6);
        panel4.add(button7);
        panel4.add(button8);
        panel4.add(button9);
        panel5.add(label);
    }
}
