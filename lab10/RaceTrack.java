package lab10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RaceTrack extends JFrame {
    public RaceTrack() {
        setTitle("Race Track (Q4)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RaceTrackPanel track = new RaceTrackPanel();
        JPanel controls = new JPanel(new GridLayout(1,4,8,0));
        for (int i = 0; i < 4; i++) {
            int carIdx = i;
            JPanel box = new JPanel(new BorderLayout());
            JSlider slider = new JSlider(0, 20, track.getSpeed(carIdx));
            slider.addChangeListener(e -> track.setSpeed(carIdx, ((JSlider)e.getSource()).getValue()));
            JLabel lab = new JLabel("Car " + (i+1), SwingConstants.CENTER);
            box.add(lab, BorderLayout.NORTH);
            box.add(slider, BorderLayout.CENTER);
            controls.add(box);
        }
        controls.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        add(track, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
        setSize(720, 420);
        setLocationRelativeTo(null);
        SwingUtilities.invokeLater(track::requestFocusInWindow);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RaceTrack().setVisible(true));
    }
}

class RaceTrackPanel extends JPanel {
    private static final int CAR_W = 50;
    private static final int TICK_MS = 20;
    private final Car[] cars = new Car[4];
    private final Timer timer;

    public RaceTrackPanel() {
        setFocusable(true);
        Random rnd = new Random();
        for (int i = 0; i < 4; i++) {
            cars[i] = new Car(new Color(80 + rnd.nextInt(150), 120 + rnd.nextInt(120), 120 + rnd.nextInt(120)),
                    2 + rnd.nextInt(6));
        }
        timer = new Timer(TICK_MS, e -> repaint());
        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                    if (timer.isRunning()) timer.stop(); else timer.start();
                }
            }
        });
    }

    public int getSpeed(int idx){ return cars[idx].speed; }
    public void setSpeed(int idx, int v){ cars[idx].speed = v; }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth(), h = getHeight();
        g.setColor(new Color(30,30,30));
        g.fillRect(0,0,w,h);
        g.setColor(new Color(70,70,70));
        for (int i = 1; i < 4; i++) {
            int y = i * h / 4;
            for (int x = 0; x < w; x += 30) {
                g.fillRect(x+10, y-2, 10, 4);
            }
        }
        for (int i = 0; i < 4; i++) {
            int laneY = (i+1) * h / 4;
            cars[i].advance(w);
            cars[i].draw(g, laneY);
        }
    }

    static class Car {
        int x = 0;
        int speed;
        final Color body;

        Car(Color body, int speed) { this.body = body; this.speed = speed; }

        void advance(int panelW) { x = (x > panelW) ? -CAR_W : x + speed; }

        void draw(Graphics g, int yBase) {
            g.setColor(Color.BLACK);
            g.fillOval(x + 10, yBase - 10, 10, 10);
            g.fillOval(x + 30, yBase - 10, 10, 10);
            g.setColor(body);
            g.fillRect(x, yBase - 20, CAR_W, 10);
            g.setColor(new Color(220,60,60));
            Polygon p = new Polygon();
            p.addPoint(x + 10, yBase - 20);
            p.addPoint(x + 20, yBase - 30);
            p.addPoint(x + 30, yBase - 30);
            p.addPoint(x + 40, yBase - 20);
            g.fillPolygon(p);
        }
    }
}

