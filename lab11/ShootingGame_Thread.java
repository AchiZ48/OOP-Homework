package lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ShootingGame_Thread extends JFrame {
    public ShootingGame_Thread(){
        setTitle("Shooting Game (Thread)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        add(new ShootPanel());
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(() -> new ShootingGame_Thread().setVisible(true)); }
}

class ShootPanel extends JPanel implements Runnable {
    private final Thread loop;
    private volatile boolean left, right, shooting, paused;
    private int playerX=300, playerY;
    private final java.util.List<Point> bullets=new ArrayList<>();
    private final java.util.List<Rectangle> targets=new ArrayList<>();
    private int score=0;
    public ShootPanel(){
        setFocusable(true);
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_LEFT) left=true;
                if(e.getKeyCode()==KeyEvent.VK_RIGHT) right=true;
                if(e.getKeyCode()==KeyEvent.VK_SPACE) shooting=true;
                if(e.getKeyCode()==KeyEvent.VK_P) paused=!paused;
            }
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_LEFT) left=false;
                if(e.getKeyCode()==KeyEvent.VK_RIGHT) right=false;
                if(e.getKeyCode()==KeyEvent.VK_SPACE) shooting=false;
            }
        });
        for(int i=0;i<6;i++){
            int x=40+i*90, y=60+(i%2)*40;
            targets.add(new Rectangle(x,y,40,20));
        }
        loop=new Thread(this,"ShootLoop"); loop.setDaemon(true); loop.start();
    }
    public void run(){
        long lastShot=0;
        try{
            while(true){
                if(!paused){
                    int w=getWidth()==0?600:getWidth(), h=getHeight()==0?500:getHeight();
                    playerY=h-40;
                    if(left) playerX=Math.max(20, playerX-6);
                    if(right) playerX=Math.min(w-20, playerX+6);
                    long now=System.nanoTime();
                    if(shooting && now-lastShot>200_000_000L){ bullets.add(new Point(playerX, playerY-20)); lastShot=now; }
                    for(int i=0;i<bullets.size();i++){
                        Point p=bullets.get(i);
                        p.y-=10;
                        if(p.y<0){ bullets.remove(i--); continue; }
                        for(int j=0;j<targets.size();j++){
                            Rectangle r=targets.get(j);
                            if(r.contains(p.x, p.y)){ targets.remove(j); bullets.remove(i); i--; score+=10; break; }
                        }
                    }
                    if(targets.isEmpty()){
                        for(int i=0;i<6;i++){
                            int x=40+i*90, y=60+(i%2)*40;
                            targets.add(new Rectangle(x,y,40,20));
                        }
                    }
                    SwingUtilities.invokeLater(this::repaint);
                }
                Thread.sleep(16);
            }
        }catch(InterruptedException ignored){}
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int w=getWidth(), h=getHeight();
        g.setColor(new Color(18,18,22)); g.fillRect(0,0,w,h);
        g.setColor(new Color(230,230,240)); g.drawString("Score: "+score+"   [Arrows move, Space shoot, P pause]", 12, 16);
        g.setColor(new Color(100,180,255)); g.fillRect(playerX-15, playerY-10, 30, 10);
        g.setColor(new Color(200,80,80)); for(Rectangle r: targets) g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.YELLOW); for(Point p: bullets) g.fillOval(p.x-3,p.y-3,6,6);
    }
}

