package lab8;

public class lab8_5 {
    public static void main(String[] args){
        Circle c1=new Circle(3),c2=new Circle(3),c3=new Circle(4);
        System.out.println(c1.equals(c2));
        System.out.println(c1.compareTo(c3));
        Rectangle2 r1=new Rectangle2(2,6),r2=new Rectangle2(3,4),r3=new Rectangle2(2,6);
        System.out.println(r1.equals(r2));
        System.out.println(r1.equals(r3));
        System.out.println(r1.compareTo(r2));
        Octagon o1=new Octagon(5);
        System.out.println(String.format("%.4f",o1.getArea()));
        System.out.println(String.format("%.4f",o1.getPerimeter()));
        Octagon o2=(Octagon)o1.clone();
        System.out.println(o1.equals(o2));
        ComparableCircle cc1=new ComparableCircle(5);
        ComparableCircle cc2=new ComparableCircle(7);
        ComparableCircle bigger=cc1.compareTo(cc2)>=0?cc1:cc2;
        System.out.println(String.format("%.4f",bigger.getArea()));
    }
}

abstract class GeometricObject{
    private String color="white";
    private boolean filled;
    protected GeometricObject(){}
    protected GeometricObject(String color,boolean filled){
        this.color=color;this.filled=filled;
    }
    public String getColor(){return color;}
    public void setColor(String color){this.color=color;}
    public boolean isFilled(){return filled;}
    public void setFilled(boolean filled){this.filled=filled;}
    public abstract double getArea();
    public abstract double getPerimeter();
}

class Circle extends GeometricObject implements Comparable<Circle>{
    private double radius;
    public Circle(){this(1.0);}
    public Circle(double r){this(r,"white",false);}
    public Circle(double r,String color,boolean filled){super(color,filled);this.radius=r;}
    public double getRadius(){return radius;}
    public void setRadius(double r){this.radius=r;}
    @Override public double getArea(){return Math.PI*radius*radius;}
    @Override public double getPerimeter(){return 2*Math.PI*radius;}
    @Override public boolean equals(Object o){
        if(!(o instanceof Circle))return false;
        Circle c=(Circle)o;
        return Double.compare(radius,c.radius)==0;
    }
    @Override public int compareTo(Circle o){return Double.compare(this.radius,o.radius);}
}

class Rectangle2 extends GeometricObject implements Comparable<Rectangle2>{
    private double width,height;
    public Rectangle2(){this(1,1);}
    public Rectangle2(double w,double h){this(w,h,"white",false);}
    public Rectangle2(double w,double h,String color,boolean filled){super(color,filled);this.width=w;this.height=h;}
    public double getWidth(){return width;}
    public double getHeight(){return height;}
    public void setWidth(double w){this.width=w;}
    public void setHeight(double h){this.height=h;}
    @Override public double getArea(){return width*height;}
    @Override public double getPerimeter(){return 2*(width+height);}
    @Override public boolean equals(Object o){
        if(!(o instanceof Rectangle2))return false;
        Rectangle2 r=(Rectangle2)o;
        return Double.compare(getArea(),r.getArea())==0;
    }
    @Override public int compareTo(Rectangle2 o){return Double.compare(this.getArea(),o.getArea());}
}

class Octagon extends GeometricObject implements Comparable<Octagon>,Cloneable{
    private double side;
    public Octagon(){this(1);}
    public Octagon(double s){this(s,"white",false);}
    public Octagon(double s,String color,boolean filled){super(color,filled);this.side=s;}
    public double getSide(){return side;}
    public void setSide(double s){this.side=s;}
    @Override public double getArea(){return (2+4/Math.sqrt(2))*side*side;}
    @Override public double getPerimeter(){return 8*side;}
    @Override public boolean equals(Object o){
        if(!(o instanceof Octagon))return false;
        Octagon x=(Octagon)o;
        return Double.compare(getArea(),x.getArea())==0;
    }
    @Override public int compareTo(Octagon o){return Double.compare(this.getArea(),o.getArea());}
    @Override public Object clone(){
        try{return super.clone();}catch(Exception e){return null;}
    }
}

class ComparableCircle extends Circle{
    public ComparableCircle(double r){super(r);}
    public ComparableCircle(double r,String color,boolean filled){super(r,color,filled);}
    @Override public int compareTo(Circle o){return Double.compare(this.getArea(),o.getArea());}
}
