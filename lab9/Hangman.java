import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class Hangman extends JFrame {

    public Hangman() {
        add(new HangPanel());
        setTitle("Hangman Swing");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Hangman().setVisible(true));
    }


    static class HangPanel extends JPanel {

        final int baseX = 20, baseY = 220;
        final int postTopY = 20;
        final int postX = baseX + 40;       
        final int beamX = postX + 100;         
        final int hookY = postTopY + 20;     

        double t = 0;                      
        final double speed = 0.08;               
        final double maxAngle = Math.toRadians(15); 

        final int headR = 20;
        final int torsoLen = 80;
        final int armLen = 60;
        final int legLen = 40;
        final int neckLen = 0;                   

        public HangPanel() {
            new Timer(16, e -> { t += speed; repaint(); }).start();
        }

        @Override
        protected void paintComponent(Graphics g0) {
            super.paintComponent(g0);
            Graphics2D g = (Graphics2D) g0.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            

            g.drawArc(baseX, baseY, 80, 40, 0, 180);           
            g.drawLine(postX, baseY, postX, postTopY);        
            g.drawLine(postX, postTopY, beamX, postTopY);      
            g.drawLine(beamX, postTopY, beamX, hookY);         


            double angle = Math.sin(t) * maxAngle;             
            AffineTransform old = g.getTransform();
            g.translate(beamX, hookY);                         
            g.rotate(angle);                                   

            int headCx = 0;
            int headCy = neckLen + headR;
            g.drawOval(headCx - headR, headCy - headR, headR * 2, headR * 2);
            
            int neckBottomY = headCy + headR;
            int bodyBottomY = neckBottomY + torsoLen;
            g.drawLine(0, neckBottomY, 0, bodyBottomY);

            g.drawLine(-(int)(armLen * Math.cos(Math.toRadians(45))), neckBottomY + (int)(headR * 0.2), 0, neckBottomY + (int)(headR * 0.2));
            g.drawLine(0, neckBottomY + (int)(headR * 0.2),(int)(armLen * Math.cos(Math.toRadians(45))), neckBottomY + (int)(headR * 0.2));

            g.drawLine(0, bodyBottomY, -40, bodyBottomY + legLen);
            g.drawLine(0, bodyBottomY,  40, bodyBottomY + legLen);

            g.setTransform(old);

            g.dispose();
        }
    }
}
