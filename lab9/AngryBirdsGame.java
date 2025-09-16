import java.awt.*;
import javax.swing.*;
import java.util.Random;

class Bird {
    double x, y;
    double speed;
    double angle;
    double Ux, Uy;
    double t = 0; // เวลา
    Bird(int x, int y, int speed, int angle) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;

        double rad = Math.toRadians(angle);
        Ux = speed * Math.cos(rad);
        Uy = speed * Math.sin(rad);
    }
}

class Pig {
    int x, y;
    Pig(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class AngryBirdsGame extends JFrame {
    private JTextField txtY, txtSpeed, txtAngle;
    private JTextField txtScore;
    private GamePanel panel;
    private Bird bird;
    private Pig pig;
    private int score = 0;
    private Timer timer;

    public AngryBirdsGame() {
        setTitle("Angry Birds Game");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---- Top Panel (Scene + Score) ----
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblScene = new JLabel("SCENE 1: At Tokyo", JLabel.LEFT);
        lblScene.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(lblScene, BorderLayout.WEST);

        JPanel scorePanel = new JPanel();
        scorePanel.add(new JLabel("SCORE"));
        txtScore = new JTextField("0", 6);
        txtScore.setEditable(false);
        scorePanel.add(txtScore);
        topPanel.add(scorePanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // ---- Game Panel ----
        panel = new GamePanel();
        add(panel, BorderLayout.CENTER);
        pig = new Pig(400, 150);        
        panel.setObjects(null, pig);  
        panel.repaint();
        // ---- Bottom Panel (Inputs) ----
        JPanel inputPanel = new JPanel(new GridLayout(4, 3));
        inputPanel.add(new JLabel("Bird Position in y-axis"));
        txtY = new JTextField();  
        inputPanel.add(txtY);
        inputPanel.add(new JLabel("m"));

        inputPanel.add(new JLabel("Shooting speed"));
        txtSpeed = new JTextField(); 
        inputPanel.add(txtSpeed);
        inputPanel.add(new JLabel("m/s"));

        inputPanel.add(new JLabel("Angle"));
        txtAngle = new JTextField();
        inputPanel.add(txtAngle);
        inputPanel.add(new JLabel("degree"));

        JButton btnOk = new JButton("OK");
        inputPanel.add(btnOk);

        inputPanel.add(new JLabel("")); // filler
        add(inputPanel, BorderLayout.SOUTH);

        // ---- Button Action ----
        btnOk.addActionListener(e -> startGame());
    }

    private void startGame() {
        try {
            int y = Integer.parseInt(txtY.getText());
            int speed = Integer.parseInt(txtSpeed.getText());
            int angle = Integer.parseInt(txtAngle.getText());

            bird = new Bird(250, y, speed, angle);

            Random rand = new Random();
            int pigX = 400;
            int pigY = 150;
            pig = new Pig(pigX, pigY);

            panel.setObjects(bird, pig);

            if (timer != null && timer.isRunning()) timer.stop();

            timer = new Timer(50, e -> updateBird());
            timer.start();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "!");
        }
    }

    private void updateBird() {
        if (bird == null) return;

        double g = 10.0;
        bird.t += 0.2;
        bird.x = 250 + bird.Ux * bird.t;
        bird.y = (bird.speed * Math.sin(Math.toRadians(bird.angle))) * bird.t - 0.5 * g * bird.t * bird.t + Integer.parseInt(txtY.getText());

        if (pig != null && Math.abs(bird.x - pig.x) <= 30 && Math.abs(bird.y - pig.y) <= 30) {
            score += 100;
            txtScore.setText(String.valueOf(score));
            JOptionPane.showMessageDialog(this, "Hit the pig! +100 points");
            timer.stop();
        }

        if (bird.y <= 0) {
            JOptionPane.showMessageDialog(this, "Missed! Bird landed at (" + (int) bird.x + ", 0)");
            timer.stop();
        }

        panel.repaint();
    }

    class GamePanel extends JPanel {
        Bird bird;
        Pig pig;
        Image bg, birdImg, pigImg;

        GamePanel() {
            bg = Toolkit.getDefaultToolkit().getImage("bg.png");
            birdImg = Toolkit.getDefaultToolkit().getImage("bird.png");
            pigImg = Toolkit.getDefaultToolkit().getImage("pig.png");
        }

        public void setObjects(Bird b, Pig p) {
            this.bird = b;
            this.pig = p;
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

            if (bird != null) {
                int by = getHeight() - (int) bird.y - 80;
                g.drawImage(birdImg, (int) bird.x, by, 60, 60, this);
            }
            if (pig != null) {
                g.drawImage(pigImg, pig.x, getHeight() - pig.y - 80, 60, 60, this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AngryBirdsGame().setVisible(true);
        });
    }
}