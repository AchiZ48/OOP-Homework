package lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RaceTrack_Thread extends JFrame {
    public RaceTrack_Thread() {
        setTitle("Race Track (Thread)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RaceTrackPanel track = new RaceTrackPanel();
        JPanel controls = new JPanel(new GridLayout(1,4,8,0));
        for (int i = 0; i < 4; i++) {
            int idx = i;
            JPanel box = new JPanel(new BorderLayout());
            JSlider slider = new JSlider(0, 20, track.getSpeed(idx));
            slider.addChangeListener(e -> track.setSpeed(idx, ((JSlider)e.getSource()).getValue()));
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
        SwingUtilities.invokeLater(() -> new RaceTrack_Thread().setVisible(true));
    }
}

class RaceTrackPanel extends JPanel {
    private static final int CAR_W = 50;
    private final int[] x = new int[4];
    private final Color[] body = new Color[4];
    private final Thread loop;
    private volatile int[] speed = new int[]{4,6,3,5};
    private volatile boolean paused = false;

    public RaceTrackPanel() {
        setFocusable(true);
        Random rnd = new Random();
        for (int i = 0; i < 4; i++) {
            body[i] = new Color(80 + rnd.nextInt(150), 120 + rnd.nextInt(120), 120 + rnd.nextInt(120));
            x[i] = 0;
        }
        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE) paused = !paused;
            }
        });
        loop = new Thread(() -> {
            try {
                while (true) {
                    if (!paused) {
                        int w = getWidth() == 0 ? 720 : getWidth();
                        for (int i = 0; i < 4; i++) {
                            x[i] = (x[i] > w) ? -CAR_W : x[i] + speed[i];
                        }
                        SwingUtilities.invokeLater(this::repaint);
                    }
                    Thread.sleep(20);
                }
            } catch (InterruptedException ignored) {}
        }, "Animator");
        loop.setDaemon(true);
        loop.start();
    }

    public int getSpeed(int idx){ return speed[idx]; }
    public void setSpeed(int idx,int v){ speed[idx]=v; }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth(), h = getHeight();
        g.setColor(new Color(30,30,30));
        g.fillRect(0,0,w,h);
        g.setColor(new Color(70,70,70));
        for (int i = 1; i < 4; i++) {
            int y = i * h / 4;
            for (int xi = 0; xi < w; xi += 30) g.fillRect(xi+10, y-2, 10, 4);
        }
        for (int i = 0; i < 4; i++) drawCar(g, i, (i+1)*h/4);
    }

    private void drawCar(Graphics g, int i, int y) {
        g.setColor(Color.BLACK);
        g.fillOval(x[i] + 10, y - 10, 10, 10);
        g.fillOval(x[i] + 30, y - 10, 10, 10);
        g.setColor(body[i]);
        g.fillRect(x[i], y - 20, CAR_W, 10);
        g.setColor(new Color(220,60,60));
        Polygon p = new Polygon();
        p.addPoint(x[i] + 10, y - 20);
        p.addPoint(x[i] + 20, y - 30);
        p.addPoint(x[i] + 30, y - 30);
        p.addPoint(x[i] + 40, y - 20);
        g.fillPolygon(p);
    }
}
