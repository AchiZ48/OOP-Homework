package FinalLab;

import javax.swing.*;
import java.awt.*;

public class Practice1 extends JFrame{
    JLabel a;
    JTextArea list;
    JTextField value;
    JButton addbtn;
    int count = 0;
    Practice1(){
        a = new JLabel("count: "+ count);
        value = new JTextField(20);
        add(a, BorderLayout.NORTH);
        list = new JTextArea();
        add(list,BorderLayout.CENTER);
        addbtn = new JButton("add");
        addbtn.addActionListener(e -> {
            list.append(value.getText() + "\n");
            count++;
            a.setText("count: "+ count);
        });
        JPanel a = new JPanel();
        value.setSize(50,10);

        a.add(value, BorderLayout.CENTER);
        a.add(addbtn, BorderLayout.EAST);
        add(a, BorderLayout.SOUTH);

    }


    public static void main(String[] args) {
        Practice1 frame = new Practice1();
        frame.setTitle("Calculator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
