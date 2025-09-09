import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import javax.swing.*;

class USD extends JFrame {
    // Make JTextField a member variable
    JTextField jtf1;
    JTextField jtf2;
    JLabel lb1;
    JLabel lb2;

    USD() {
        jtf1 = new JTextField();
        jtf1.setEditable(true);  // Make the text field non-editable
        jtf2 = new JTextField();
        jtf2.setEditable(false);
        lb1 = new JLabel("US Dollars");// Make the text field non-editable
        lb2 = new JLabel("Canadian Dollars");// Make the text field non-editable

        // Create panel p1 for the buttons and set GridLayout
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2, 1));
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 1));
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 2));
        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(1, 3));
        
        p1.add(lb1);
        p1.add(lb2);

        JButton jbtn0 = new JButton("Convert");
        jbtn0.addActionListener(new Listener());
        p4.add(jbtn0, BorderLayout.EAST);

        p2.add(jtf1);
        p2.add(jtf2);
        p3.add(p1);
        p3.add(p2);
        add(p3,BorderLayout.CENTER);
        add(p4,BorderLayout.SOUTH);
        

    }

    /** Main method */
    public static void main(String[] args) {
        USD frame = new USD();
        frame.setTitle("Convert USD to CAD");
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();
            int value = Integer.parseInt(jtf1.getText());
            if (command.equals("Convert")) {
                double result = Double.parseDouble(jtf1.getText()) * 1.5; 
                double roundedValue = Math.round(result * 100.0) / 100.0;
                jtf2.setText(Double.toString(roundedValue));
            }
        }
    }
}
