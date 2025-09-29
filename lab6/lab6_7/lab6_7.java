package lab6.lab6_7;

class MyRectangle2D {
    private double x;
    private double y;
    private double width;
    private double height;

    public MyRectangle2D() { this(0, 0, 1, 1); }

    public MyRectangle2D(double x, double y, double width, double height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getArea() { return width * height; }
    public double getPerimeter() { return 2 * (width + height); }

    private double left()   { return x - width / 2.0; }
    private double right()  { return x + width / 2.0; }
    private double top()    { return y + height / 2.0; }
    private double bottom() { return y - height / 2.0; }

    public boolean contains(double px, double py) {
        return (px >= left() && px <= right() && py >= bottom() && py <= top());
    }

    public boolean contains(MyRectangle2D r) {
        return r.left() >= this.left()
                && r.right() <= this.right()
                && r.bottom() >= this.bottom()
                && r.top() <= this.top();
    }

    public boolean overlaps(MyRectangle2D r) {
        boolean xOverlap = Math.abs(this.x - r.x) <= (this.width + r.width) / 2.0;
        boolean yOverlap = Math.abs(this.y - r.y) <= (this.height + r.height) / 2.0;
        return xOverlap && yOverlap;
    }
}

public class lab6_7 {
    public static void main(String[] args) {
        MyRectangle2D R1 = new MyRectangle2D(0, 0, 4, 2);
        MyRectangle2D R2 = new MyRectangle2D(0.5, 0, 1, 1);
        MyRectangle2D R3 = new MyRectangle2D(1.5, 0, 3, 2);

        System.out.println("R1 area=" + R1.getArea() + ", peri=" + R1.getPerimeter());
        System.out.println("R1 contains point (1,0)? " + R1.contains(1, 0));
        System.out.println("R1 contains R2? " + R1.contains(R2));
        System.out.println("R1 overlaps R3? " + R1.overlaps(R3));
    }
}

