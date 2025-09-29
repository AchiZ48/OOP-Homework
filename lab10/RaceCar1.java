package lab10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RaceCar1 extends JFrame {
    public RaceCar1() {
        setTitle("RaceCar (Q3)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 160);
        setLocationRelativeTo(null);
        RaceCar panel = new RaceCar();
        add(panel);
        SwingUtilities.invokeLater(panel::requestFocusInWindow);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RaceCar1().setVisible(true));
    }
}

class RaceCar extends JPanel {
    private int xBase = 0;
    private final Timer timer;

    public RaceCar() {
        setFocusable(true);
        timer = new Timer(10, e -> repaint());
        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (!e.isControlDown()) return;
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_PLUS || code == KeyEvent.VK_ADD || code == KeyEvent.VK_EQUALS) {
                    int d = Math.max(1, timer.getDelay() - 5);
                    timer.setDelay(d);
                } else if (code == KeyEvent.VK_MINUS || code == KeyEvent.VK_SUBTRACT) {
                    timer.setDelay(timer.getDelay() + 5);
                }
            }
        });
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int yBase = getHeight();
        xBase = (xBase > getWidth()) ? -50 : xBase + 1;

        g.setColor(Color.BLACK);
        g.fillOval(xBase + 10, yBase - 10, 10, 10);
        g.fillOval(xBase + 30, yBase - 10, 10, 10);

        g.setColor(Color.GREEN);
        g.fillRect(xBase, yBase - 20, 50, 10);

        g.setColor(Color.RED);
        Polygon p = new Polygon();
        p.addPoint(xBase + 10, yBase - 20);
        p.addPoint(xBase + 20, yBase - 30);
        p.addPoint(xBase + 30, yBase - 30);
        p.addPoint(xBase + 40, yBase - 20);
        g.fillPolygon(p);
    }
}
