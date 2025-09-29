package lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fan_Thread extends JFrame {
    public Fan_Thread() {
        setTitle("Fan (Thread)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 420);
        setLocationRelativeTo(null);
        add(new FanPanel());
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(() -> new Fan_Thread().setVisible(true)); }
}

class FanPanel extends JPanel implements Runnable {
    private volatile double speed = 2.5;
    private volatile boolean paused = false;
    private double angle = 0;
    private final Thread loop;
    public FanPanel() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_SPACE) paused=!paused;
                if(e.getKeyCode()==KeyEvent.VK_UP) speed=Math.min(20, speed+0.5);
                if(e.getKeyCode()==KeyEvent.VK_DOWN) speed=Math.max(0, speed-0.5);
            }
        });
        loop = new Thread(this,"FanLoop"); loop.setDaemon(true); loop.start();
    }
    public void run(){
        try{
            while(true){
                if(!paused){ angle = (angle + speed) % 360; SwingUtilities.invokeLater(this::repaint); }
                Thread.sleep(16);
            }
        }catch(InterruptedException ignored){}
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int w=getWidth(), h=getHeight(), r=Math.min(w,h)/2-20, cx=w/2, cy=h/2;
        g.setColor(Color.WHITE); g.fillRect(0,0,w,h);
        g.setColor(Color.LIGHT_GRAY); g.fillOval(cx-r, cy-r, 2*r, 2*r);
        drawBlade(g,cx,cy,r,angle, new Color(60,140,200));
        drawBlade(g,cx,cy,r,angle+90, new Color(60,200,140));
        drawBlade(g,cx,cy,r,angle+180, new Color(200,140,60));
        drawBlade(g,cx,cy,r,angle+270, new Color(200,80,120));
        g.setColor(Color.DARK_GRAY); g.fillOval(cx-12, cy-12, 24, 24);
    }
    private void drawBlade(Graphics g,int cx,int cy,int r,double deg,Color c){
        double a=Math.toRadians(deg);
        int tipX=cx+(int)(r*0.95*Math.cos(a));
        int tipY=cy+(int)(r*0.95*Math.sin(a));
        int s1x=cx+(int)(r*0.35*Math.cos(a+Math.toRadians(20)));
        int s1y=cy+(int)(r*0.35*Math.sin(a+Math.toRadians(20)));
        int s2x=cx+(int)(r*0.35*Math.cos(a-Math.toRadians(20)));
        int s2y=cy+(int)(r*0.35*Math.sin(a-Math.toRadians(20)));
        Polygon p=new Polygon(new int[]{tipX,s1x,s2x}, new int[]{tipY,s1y,s2y},3);
        g.setColor(c); ((Graphics2D)g).fill(p);
    }
}

