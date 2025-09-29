package lab10;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ShootingGame extends JFrame {
    public ShootingGame() {
        setTitle("Balloon Shooting");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ShootingGame();
    }
}

class Balloon {
    int x, y, size;
    int dx, dy;

    public Balloon(int x, int y, int size, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dx = dx;
        this.dy = dy;
    }

    public void move(int panelWidth, int panelHeight) {
        x += dx;
        y += dy;

        if (x < 0 || x + size > panelWidth) {
            dx = -dx;
        }
        if (y < 0 || y + size > panelHeight) {
            dy = -dy;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}

class GamePanel extends JPanel implements ActionListener {
    private final int TIMER_DELAY = 35; 
    private final int BULLET_SPEED = 6;  
    private final int BALLOON_MIN_DX = -2; 
    private final int BALLOON_MAX_DX = 2;
    private final int BALLOON_MIN_DY = -2; 
    private final int BALLOON_MAX_DY = -1;

    private int gunX;
    private final int gunY;
    private int bulletX, bulletY;
    private boolean shooting = false;

    private final int GUN_WIDTH = 10;
    private final int GUN_HEIGHT = 40;

    private Timer timer;
    private Random rand = new Random();

    private java.util.List<Balloon> balloons = new ArrayList<>();
    private int score = 0;

    public GamePanel() {
        setBackground(Color.LIGHT_GRAY);

        gunX = 200;
        gunY = 330;

        balloons.add(new Balloon(rand.nextInt(400), rand.nextInt(200), 30, 0, 0));

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    gunX -= 10;
                    if (gunX < 0) gunX = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    gunX += 10;
                    if (gunX > getWidth() - GUN_WIDTH) gunX = getWidth() - GUN_WIDTH;
                } else if (key == KeyEvent.VK_UP) {
                    if (!shooting) {
                        shooting = true;
                        bulletX = gunX + GUN_WIDTH / 2 - 4;
                        bulletY = gunY - GUN_HEIGHT;
                    }
                }
                repaint();
            }
        });

        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, 10, 20);

        g.setColor(Color.BLACK);
        for (Balloon b : balloons) {
            g.drawOval(b.x, b.y, b.size, b.size);
        }

        g.fillRect(gunX, gunY - GUN_HEIGHT, GUN_WIDTH, GUN_HEIGHT);

        if (shooting) {
            g.fillOval(bulletX, bulletY, 8, 8);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (shooting) {
            bulletY -= BULLET_SPEED;
            if (bulletY < 0) {
                shooting = false;
            }

            Rectangle bulletRect = new Rectangle(bulletX, bulletY, 8, 8);

            Iterator<Balloon> it = balloons.iterator();
            while (it.hasNext()) {
                Balloon b = it.next();
                if (bulletRect.intersects(b.getBounds())) {
                    it.remove();
                    shooting = false;
                    score++;

                    if (b.size > 10) {
                        int numPieces = 4 + rand.nextInt(3);
                        for (int i = 0; i < numPieces; i++) {
                            int dx = BALLOON_MIN_DX + rand.nextInt(BALLOON_MAX_DX - BALLOON_MIN_DX + 1);
                            int dy = BALLOON_MIN_DY + rand.nextInt(BALLOON_MAX_DY - BALLOON_MIN_DY + 1);
                            balloons.add(new Balloon(b.x, b.y, b.size / 2, dx, dy));
                        }
                    }
                    break;
                }
            }
        }

        for (Balloon b : balloons) {
            b.move(getWidth(), getHeight());
        }

        repaint();
    }
}