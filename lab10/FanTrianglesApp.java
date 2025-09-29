package lab10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class FanTrianglesApp extends JFrame {
    private FanPanel fanPanel;
    private JButton[] speedButtons;
    private int currentSpeed = 0;

    public FanTrianglesApp() {
        setTitle("Fan-like Isosceles Triangles with Speed Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        fanPanel = new FanPanel();
        add(fanPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        speedButtons = new JButton[4];
        String[] labels = {"0", "1", "2", "3"};
        int[] delays = {0, 100, 50, 20};

        for (int i = 0; i < 4; i++) {
            final int speed = i;
            speedButtons[i] = new JButton(labels[i]);
            speedButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setSpeed(speed);
                }
            });
            buttonPanel.add(speedButtons[i]);
        }

 
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setSpeed(int speed) {
        currentSpeed = speed;
        fanPanel.setSpeed(speed);

        for (int i = 0; i < speedButtons.length; i++) {
            speedButtons[i].setBackground(i == speed ? Color.YELLOW : null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FanTrianglesApp();
            }
        });
    }

    private static class FanPanel extends JPanel {
        private double rotationAngle = 0.0;
        private Timer rotationTimer;
        private int currentSpeed = 0;
        private static final int NUM_TRIANGLES = 4;
        private static final double BASE_ANGLE = 2 * Math.PI / NUM_TRIANGLES;
        private static final int CENTER_X = 200;
        private static final int CENTER_Y = 200;
        private static final int RADIUS = 150;
        private static final double TRIANGLE_ANGLE = Math.PI / 6;
        private static final double ANGLE_DELTA = Math.toRadians(5);

        public FanPanel() {
            setPreferredSize(new Dimension(400, 400));
            setBackground(Color.WHITE);

        }

        public void setSpeed(int speed) {
            if (currentSpeed != speed) {
                currentSpeed = speed;
                if (rotationTimer != null) {
                    rotationTimer.stop();
                }
                if (speed > 0) {
                    int delay = getDelayForSpeed(speed);
                    rotationTimer = new Timer(delay, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rotationAngle += ANGLE_DELTA;
                            if (rotationAngle >= 2 * Math.PI) {
                                rotationAngle -= 2 * Math.PI;
                            }
                            repaint();
                        }
                    });
                    rotationTimer.start();
                }
            }
        }

        private int getDelayForSpeed(int speed) {
            switch (speed) {
                case 1: return 100;
                case 2: return 50;  
                case 3: return 20;  
                default: return 0;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

 
            AffineTransform transform = AffineTransform.getTranslateInstance(CENTER_X, CENTER_Y);
            transform.rotate(rotationAngle);
            g2d.setTransform(transform);

            for (int i = 0; i < NUM_TRIANGLES; i++) {
                double startAngle = i * BASE_ANGLE;
                drawTriangle(g2d, startAngle);
            }

            g2d.dispose();
        }

        private void drawTriangle(Graphics2D g2d, double startAngle) {
            GeneralPath triangle = new GeneralPath();

            double apexX = 0;
            double apexY = 0;

            double baseAngle1 = startAngle - TRIANGLE_ANGLE / 2;
            double baseAngle2 = startAngle + TRIANGLE_ANGLE / 2;

            double baseX1 = RADIUS * Math.cos(baseAngle1);
            double baseY1 = RADIUS * Math.sin(baseAngle1);
            double baseX2 = RADIUS * Math.cos(baseAngle2);
            double baseY2 = Math.sin(baseAngle2) * RADIUS;

            triangle.moveTo(apexX, apexY);
            triangle.lineTo(baseX1, baseY1);
            triangle.lineTo(baseX2, baseY2);
            triangle.closePath();

            g2d.setColor(Color.PINK);
            g2d.fill(triangle);
            g2d.setColor(Color.PINK);
            g2d.draw(triangle);
        }
    }
}