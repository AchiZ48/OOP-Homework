package FinalLab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Practice3 extends JPanel {
    int xBase = 0, speed = 1;
    double angle=0;
    Thread a;
    public Practice3(){
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!e.isControlDown()) return;
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_PLUS || code == KeyEvent.VK_ADD || code == KeyEvent.VK_EQUALS) {
                    speed = Math.min(20, speed + 1);
                } else if (code == KeyEvent.VK_MINUS || code == KeyEvent.VK_SUBTRACT) {
                    speed = Math.max(0, speed - 1);
                }
            }
        });

        a = new Thread(()->{
            try{
                while(true){
                    Thread.sleep(10);
                    xBase = (xBase > getWidth()) ? xBase - getWidth(): xBase + speed;
                    repaint();
                }
            }catch (InterruptedException ignored){}
        },"thread1");
        a.start();

    }
    @Override protected void paintComponent(Graphics g0){
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        int y = getHeight();
        xBase = (xBase > getWidth()) ? -50 : xBase + 1;
        angle += 1;


        g.setColor(Color.GREEN);
        g.fillRect(xBase, y - 20, 50, 10);
        g.setColor(Color.RED);
        Polygon p = new Polygon();
        p.addPoint(xBase + 10, y - 20);
        p.addPoint(xBase + 20, y - 30);
        p.addPoint(xBase + 30, y - 30);
        p.addPoint(xBase + 40, y - 20);
        g.fillPolygon(p);
        g.rotate(angle);

        g.setColor(Color.BLACK);
        g.fillOval(xBase + 10, y - 10, 10, 10);
        g.fillOval(xBase + 30, y - 10, 10, 10);

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame();
            JPanel a = new JPanel(new GridLayout(4,1));
            frame.setTitle("Ptc3");
            frame.setSize(640,360);
            a.add(new Practice3());
            a.add(new Practice3());
            a.add(new Practice3());
            a.add(new Practice3());
            frame.add(a);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}


