package lab6.lab6_8;

class Rectangle {
    double width, height;
    double x, y;

    public Rectangle() { this(1, 1, 0, 0); }

    public Rectangle(double width, double height, double x, double y) {
        this.width = width; this.height = height; this.x = x; this.y = y;
    }

    public double getArea(Rectangle a) { return a.width * a.height; }

    double left()   { return x; }
    double right()  { return x + width; }
    double top()    { return y; }
    double bottom() { return y + height; }

    double centerX() { return x + width / 2.0; }
    double centerY() { return y + height / 2.0; }

    boolean containsPoint(double px, double py) {
        return px >= left() && px <= right() && py >= top() && py <= bottom();
    }
}
class Line {
    double x1, y1, x2, y2;

    public Line() { this(0, 0, 1, 0); }

    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
    }

    public double getLong(Line a) {
        double dx = a.x2 - a.x1, dy = a.y2 - a.y1;
        return Math.hypot(dx, dy);
    }

    double midX() { return (x1 + x2) / 2.0; }
    double midY() { return (y1 + y2) / 2.0; }
}
class GeometryUtils {

    public static int contains(Line a, Rectangle b) {
        boolean p1 = b.containsPoint(a.x1, a.y1);
        boolean p2 = b.containsPoint(a.x2, a.y2);
        return (p1 && p2) ? 1 : 0;
    }

    public static int cross(Line a, Line b) {
        return segmentsIntersect(a.x1, a.y1, a.x2, a.y2, b.x1, b.y1, b.x2, b.y2) ? 1 : 0;
    }

    public static int overlaps(Rectangle A, Rectangle B) {
        boolean xOverlap = (A.left() <= B.right()) && (B.left() <= A.right());
        boolean yOverlap = (A.top()  <= B.bottom()) && (B.top()  <= A.bottom());
        return (xOverlap && yOverlap) ? 1 : 0;
    }

    public static double distance(Line a, Rectangle b) {
        double cxL = a.midX(), cyL = a.midY();
        double cxR = b.centerX(), cyR = b.centerY();
        return Math.hypot(cxL - cxR, cyL - cyR);
    }

    private static boolean segmentsIntersect(double x1, double y1, double x2, double y2,
                                             double x3, double y3, double x4, double y4) {
        int o1 = orientation(x1, y1, x2, y2, x3, y3);
        int o2 = orientation(x1, y1, x2, y2, x4, y4);
        int o3 = orientation(x3, y3, x4, y4, x1, y1);
        int o4 = orientation(x3, y3, x4, y4, x2, y2);

        if (o1 != o2 && o3 != o4) return true;

        if (o1 == 0 && onSegment(x1, y1, x3, y3, x2, y2)) return true;
        if (o2 == 0 && onSegment(x1, y1, x4, y4, x2, y2)) return true;
        if (o3 == 0 && onSegment(x3, y3, x1, y1, x4, y4)) return true;
        if (o4 == 0 && onSegment(x3, y3, x2, y2, x4, y4)) return true;

        return false;
    }

    private static int orientation(double ax, double ay, double bx, double by, double cx, double cy) {
        double v = (by - ay) * (cx - bx) - (bx - ax) * (cy - by);
        final double EPS = 1e-12;
        if (Math.abs(v) < EPS) return 0;
        return (v > 0) ? 1 : 2;
    }

    private static boolean onSegment(double ax, double ay, double px, double py, double bx, double by) {
        return px >= Math.min(ax, bx) - 1e-12 && px <= Math.max(ax, bx) + 1e-12 &&
                py >= Math.min(ay, by) - 1e-12 && py <= Math.max(ay, by) + 1e-12;
    }
}
public class lab6_8 {
    public static void main(String[] args) {
        Rectangle A = new Rectangle(10, 6, 2, 3);
        Line L1 = new Line(4, 4, 8, 7);
        Line L2 = new Line(0, 0, 20, 20);

        System.out.println("Area A = " + A.getArea(A));
        System.out.println("Length L1 = " + L1.getLong(L1));

        System.out.println("contains(L1, A) -> " + GeometryUtils.contains(L1, A));
        System.out.println("cross(L1, L2) -> " + GeometryUtils.cross(L1, L2));

        Rectangle B = new Rectangle(5, 5, 8, 5);
        System.out.println("overlaps(A, B) -> " + GeometryUtils.overlaps(A, B));

        System.out.println("distance(L1, A) = " + GeometryUtils.distance(L1, A));
    }
}

