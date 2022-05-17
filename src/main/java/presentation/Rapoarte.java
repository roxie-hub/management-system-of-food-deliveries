package presentation;

import javax.swing.*;
import java.awt.*;

public class Rapoarte extends JFrame {
    JPanel plabels = new JPanel();
    JPanel pta = new JPanel();
    private JTextArea text = new JTextArea(20,40);
    public Rapoarte(String str){
        plabels.setBounds(0,0,450,50);
        plabels.setBackground(new Color(120,225,225));
        pta.setBounds(0,50,450,400);
        pta.setBackground(new Color(120,225,225));
        text.setText(str);

        this.setSize(500,450);
        this.setTitle("Rapoarte");
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(120,225,225));

        this.add(plabels);
        this.add(pta);
        pta.add(text);
    }

    public JTextArea getText() {
        return text;
    }

    public void setText(JTextArea text) {
        this.text = text;
    }
}
