
import java.util.Scanner;

public class lab2_7 {
    public static void main(String[] args) {
        System.out.print("Enter a b c d e f : ");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter r1's center x-, y-coordinates, width, and height: ");
        double r1x = input.nextDouble();
        double r1y = input.nextDouble();
        double r1w = input.nextDouble();
        double r1h = input.nextDouble();
        Rectangle r1 = new Rectangle(r1x,r1y,r1w,r1h);

        System.out.print("Enter r2's center x-, y-coordinates, width, and height: ");
        double r2x = input.nextDouble();
        double r2y = input.nextDouble();
        double r2w = input.nextDouble();
        double r2h = input.nextDouble();
        Rectangle r2 = new Rectangle(r2x,r2y,r2w,r2h);

        if(r1.isInside(r2)) {
            System.out.println("r2 is inside r1");
        }
        else{
            if(r1.isOverlap(r2))System.out.println("r2 overlaps r1");
            else System.out.println("r2 does not overlap r1");;
        }
    }
}
class Rectangle {
    private double x,y,w,h;
    private double Left;
    private double Right;
    private double Bottom;
    private double Top;

    public Rectangle() {
    }

    public Rectangle(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.Top = this.y + this.h / 2;
        this.Bottom = this.y - this.h / 2;
        this.Right = this.x + this.w / 2;
        this.Left = this.x - this.w / 2;
    }



    public boolean isInside(Rectangle r){
        if (r.Left >= this.Left && r.Right <= this.Right && r.Bottom >= this.Bottom && r.Top <= this.Top) return true;
        else return false;
    }

    public boolean  isOverlap(Rectangle r){
        if(r.Right > this.Left && r.Left < this.Right && r.Top > this.Bottom && r.Bottom < this.Top) return true;
        else return false;
    }
}
