package lab11;

import javax.swing.*;
import java.awt.*;
import java.time.*;

public class ClockAnimation_Thread extends JFrame {
    public ClockAnimation_Thread() {
        setTitle("Clock (Thread)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 360);
        setLocationRelativeTo(null);
        add(new ClockPanel());
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(() -> new ClockAnimation_Thread().setVisible(true)); }
}

class ClockPanel extends JPanel implements Runnable {
    private final Thread loop;
    private volatile boolean running = true;
    public ClockPanel() {
        loop = new Thread(this, "ClockLoop");
        loop.setDaemon(true);
        loop.start();
    }
    public void run() {
        try {
            while (running) {
                SwingUtilities.invokeLater(this::repaint);
                Thread.sleep(40);
            }
        } catch (InterruptedException ignored) {}
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w=getWidth(), h=getHeight(), r=Math.min(w,h)/2-10, cx=w/2, cy=h/2;
        g.setColor(Color.WHITE); g.fillRect(0,0,w,h);
        g.setColor(Color.BLACK); g.drawOval(cx-r, cy-r, 2*r, 2*r);
        for(int i=0;i<60;i++){
            double a=Math.toRadians(i*6);
            int r1=r-8, r2= r-2;
            if(i%5==0){ r1=r-16; }
            int x1=cx+(int)(r1*Math.sin(a)), y1=cy-(int)(r1*Math.cos(a));
            int x2=cx+(int)(r2*Math.sin(a)), y2=cy-(int)(r2*Math.cos(a));
            g.drawLine(x1,y1,x2,y2);
        }
        LocalTime t=LocalTime.now();
        double sAng=Math.toRadians(t.getSecond()*6 + t.getNano()/1_000_000_000.0*6);
        double mAng=Math.toRadians(t.getMinute()*6 + t.getSecond()/60.0*6);
        double hAng=Math.toRadians((t.getHour()%12)*30 + t.getMinute()/60.0*30);
        drawHand(g, cx, cy, hAng, (int)(r*0.5), 6, new Color(20,20,20));
        drawHand(g, cx, cy, mAng, (int)(r*0.75), 4, new Color(50,50,50));
        drawHand(g, cx, cy, sAng, (int)(r*0.85), 2, Color.RED);
        g.fillOval(cx-4, cy-4, 8, 8);
    }
    private void drawHand(Graphics g,int cx,int cy,double ang,int len,int th,Color c){
        Graphics2D g2=(Graphics2D)g;
        Stroke old=g2.getStroke();
        g2.setStroke(new BasicStroke(th, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(c);
        int x=cx+(int)(len*Math.sin(ang)), y=cy-(int)(len*Math.cos(ang));
        g2.drawLine(cx,cy,x,y);
        g2.setStroke(old);
    }
}

