package lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RaceCar_Thread extends JFrame {
    public RaceCar_Thread() {
        setTitle("RaceCar (Thread)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 160);
        setLocationRelativeTo(null);
        RaceCarPanel panel = new RaceCarPanel();
        add(panel);
        SwingUtilities.invokeLater(panel::requestFocusInWindow);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RaceCar_Thread().setVisible(true));
    }
}

class RaceCarPanel extends JPanel {
    private volatile int speed = 1;
    private int xBase = 0;
    private final Thread loop;

    public RaceCarPanel() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (!e.isControlDown()) return;
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_PLUS || code == KeyEvent.VK_ADD || code == KeyEvent.VK_EQUALS) {
                    speed = Math.min(20, speed + 1);
                } else if (code == KeyEvent.VK_MINUS || code == KeyEvent.VK_SUBTRACT) {
                    speed = Math.max(0, speed - 1);
                }
            }
        });
        loop = new Thread(() -> {
            try {
                while (true) {
                    int w = getWidth() == 0 ? 360 : getWidth();
                    xBase = (xBase > w) ? -50 : xBase + speed;
                    SwingUtilities.invokeLater(this::repaint);
                    Thread.sleep(16);
                }
            } catch (InterruptedException ignored) {}
        }, "Animator");
        loop.setDaemon(true);
        loop.start();
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = getHeight();
        g.setColor(Color.BLACK);
        g.fillOval(xBase + 10, y - 10, 10, 10);
        g.fillOval(xBase + 30, y - 10, 10, 10);
        g.setColor(Color.GREEN);
        g.fillRect(xBase, y - 20, 50, 10);
        g.setColor(Color.RED);
        Polygon p = new Polygon();
        p.addPoint(xBase + 10, y - 20);
        p.addPoint(xBase + 20, y - 30);
        p.addPoint(xBase + 30, y - 30);
        p.addPoint(xBase + 40, y - 20);
        g.fillPolygon(p);
    }
}

