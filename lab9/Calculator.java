import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Calculator extends JFrame {
    JTextField jtf;
    String currentInput = "";
    String operator = "";
    double firstNumber = 0;
    boolean isOperatorClicked = false;

    Calculator() {
        jtf = new JTextField();
        jtf.setEditable(false); 

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(6, 4));

        JButton jbtnSqrt = new JButton("sqrt");
        jbtnSqrt.addActionListener(new Listener());
        p1.add(jbtnSqrt);
        JButton jbtnSquare = new JButton("x^2");
        jbtnSquare.addActionListener(new Listener());
        p1.add(jbtnSquare);
        JButton jbtnPlusMinus = new JButton("+/-");
        jbtnPlusMinus.addActionListener(new Listener());
        p1.add(jbtnPlusMinus);
        JButton jbtnClear = new JButton("C");
        jbtnClear.addActionListener(new Listener());
        p1.add(jbtnClear);
        for (int i = 1; i <= 9; i++) {
            switch(i){
                case 4 :{
                    JButton jbtnAdd = new JButton("+");
                    jbtnAdd.addActionListener(new Listener());
                    p1.add(jbtnAdd);
                    break;
                }
                case 7 :{
                    JButton jbtnSubtract = new JButton("-");
                    jbtnSubtract.addActionListener(new Listener());
                    p1.add(jbtnSubtract);
                    break;
                }
                
            }
            JButton jbtn = new JButton("" + i);
            jbtn.addActionListener(new Listener());
            p1.add(jbtn);
        }

        JButton jbtnMultiply = new JButton("*");
        jbtnMultiply.addActionListener(new Listener());
        p1.add(jbtnMultiply);
                  
        JButton jbtn0 = new JButton("0");
        jbtn0.addActionListener(new Listener());
        p1.add(jbtn0);

        JButton jbtnDot = new JButton(".");
        jbtnDot.addActionListener(new Listener());
        p1.add(jbtnDot);
        JButton jbtnDivide = new JButton("/");
        jbtnDivide.addActionListener(new Listener());
        p1.add(jbtnDivide);

        JButton jbtnEquals = new JButton("=");
        jbtnEquals.addActionListener(new Listener());
        p1.add(jbtnEquals);

        JPanel p2 = new JPanel(new BorderLayout());
        p2.add(jtf, BorderLayout.NORTH);
        p2.add(p1, BorderLayout.CENTER);

        add(p2, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Calculator frame = new Calculator();
        frame.setTitle("Calculator");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();

            if (command.matches("[0-9]") || command.equals(".")) {
                if (isOperatorClicked) {
                    currentInput = "";
                    isOperatorClicked = false;
                }
                currentInput += command;
                jtf.setText(currentInput);

            } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                if (!currentInput.isEmpty()) {
                    firstNumber = Double.parseDouble(currentInput);
                    currentInput = "";
                    operator = command;
                    jtf.setText(firstNumber + " " + operator);
                    isOperatorClicked = true;
                }

            } else if (command.equals("=")) {
                if (!currentInput.isEmpty() && operator != "") {
                    double secondNumber = Double.parseDouble(currentInput);
                    double result = 0;
                    switch (operator) {
                        case "+":
                            result = firstNumber + secondNumber;
                            break;
                        case "-":
                            result = firstNumber - secondNumber;
                            break;
                        case "*":
                            result = firstNumber * secondNumber;
                            break;
                        case "/":
                            if (secondNumber != 0) {
                                result = firstNumber / secondNumber;
                            } else {
                                jtf.setText("Error: Div by 0");
                                return;
                            }
                            break;
                    }
                    jtf.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                    operator = "";
                    isOperatorClicked = false;
                }

            } else if (command.equals("C")) {
                firstNumber = 0;
                currentInput = "";
                operator = "";
                jtf.setText("");
                isOperatorClicked = false;

            } else if (command.equals("x^2")) {
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    double result = Math.pow(value, 2);
                    jtf.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                }

   
            } else if (command.equals("sqrt")) {
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    if (value >= 0) {
                        double result = Math.sqrt(value);
                        jtf.setText(String.valueOf(result));
                        currentInput = String.valueOf(result);
                    } else {
                        jtf.setText("Error: Negative Sqrt");
                    }
                }

            } else if (command.equals("+/-")) {
                if (!currentInput.isEmpty()) {
                    double value = Double.parseDouble(currentInput);
                    value = -value;
                    jtf.setText(String.valueOf(value));
                    currentInput = String.valueOf(value);
                }

            }
        }
    }
}
