package lab11;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;

public class ClockAnimation_Thread extends JFrame {
    private StillClock[] clocks = new StillClock[3];

    public ClockAnimation_Thread() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
       
        clocks[0] = new StillClock(TimeZone.getTimeZone("Asia/Bangkok"), "Thailand");
        clocks[1] = new StillClock(TimeZone.getTimeZone("Europe/London"), "England");
        clocks[2] = new StillClock(TimeZone.getTimeZone("Asia/Tokyo"), "Japan");
       
        for (StillClock clock : clocks) {
            panel.add(clock);
        }
        add(panel);

        new Thread(new ClockUpdater()).start();
    }

    private class ClockUpdater implements Runnable {
        @Override
        public void run() {
            while (true) {
                for (StillClock clock : clocks) {
                    clock.setCurrentTime();
                    clock.repaint();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new ClockAnimation_Thread();
        frame.setTitle("ClockAnimation_Thread");
        frame.setSize(600, 220);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class StillClock extends JPanel {
    private int hour;
    private int minute;
    private int second;
    private TimeZone timeZone;
    private String countryName;

    public StillClock() {
        this(TimeZone.getDefault(), "Default");
    }

    public StillClock(TimeZone tz, String country) {
        this.timeZone = tz;
        this.countryName = country;
        setCurrentTime();
    }

    public StillClock(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.timeZone = TimeZone.getDefault();
        this.countryName = "Default";
    }

    public StillClock(int hour, int minute, int second, TimeZone tz) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.timeZone = tz;
        this.countryName = "Default";
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        repaint();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        repaint();
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int clockRadius = (int)(Math.min(getWidth(), getHeight() - 40) * 0.8 * 0.5);
        int xCenter = getWidth() / 2;
        int yCenter = (getHeight() - 40) / 2;

        g.setColor(Color.black);
        g.drawOval(xCenter - clockRadius, yCenter - clockRadius,
                   2 * clockRadius, 2 * clockRadius);
        g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
        g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
        g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
        g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);

        int sLength = (int)(clockRadius * 0.8);
        int xSecond = (int)(xCenter + sLength * Math.sin(second * (2 * Math.PI / 60)));
        int ySecond = (int)(yCenter - sLength * Math.cos(second * (2 * Math.PI / 60)));
        g.setColor(Color.red);
        g.drawLine(xCenter, yCenter, xSecond, ySecond);

        int mLength = (int)(clockRadius * 0.65);
        int xMinute = (int)(xCenter + mLength * Math.sin(minute * (2 * Math.PI / 60)));
        int yMinute = (int)(yCenter - mLength * Math.cos(minute * (2 * Math.PI / 60)));
        g.setColor(Color.blue);
        g.drawLine(xCenter, yCenter, xMinute, yMinute);

        int hLength = (int)(clockRadius * 0.5);
        int xHour = (int)(xCenter + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
        int yHour = (int)(yCenter - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
        g.setColor(Color.green);
        g.drawLine(xCenter, yCenter, xHour, yHour);

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        int nameY = yCenter + clockRadius + 25;
        int nameWidth = g.getFontMetrics().stringWidth(countryName);
        g.drawString(countryName, xCenter - (nameWidth / 2), nameY);
    }

    public void setCurrentTime() {
        Calendar calendar = new GregorianCalendar(timeZone);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 220);
    }
}
