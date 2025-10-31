package FinalLab;

import javax.swing.*;
import java.awt.*;

public class Practice2 extends JFrame{
    JPanel Cal = new JPanel(new GridLayout(5,2));
    JTextField rate = new JTextField();
    JTextField year = new JTextField();
    JTextField loan = new JTextField();
    JTextField mpay = new JTextField();
    JTextField total = new JTextField();
    JButton calbtn = new JButton("Calculate");

    public Practice2() {
        JLabel text = new JLabel("Enter Pls");
        Cal.add(new JLabel("Annual Interest Year"));
        Cal.add(rate);
        Cal.add(new JLabel("Number of year"));
        Cal.add(year);
        Cal.add(new JLabel("Loan"));
        Cal.add(loan);
        Cal.add(new JLabel("Monthly Payment"));
        Cal.add(mpay);
        Cal.add(new JLabel("Total Payment"));
        Cal.add(total);
        calbtn.addActionListener(e -> {
            double x = Double.parseDouble(rate.getText()+year.getText()+loan.getText()+mpay.getText());                  ;
            total.setText(String.valueOf(x));
        });
        add(text, BorderLayout.NORTH);
        add(Cal, BorderLayout.CENTER);
        add(calbtn, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        Practice2 frame = new Practice2();
        frame.setTitle("KUY");
        frame.setSize(400,200);
        frame.setVisible(true);
    }
}
