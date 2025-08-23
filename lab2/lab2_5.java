
import java.util.Scanner;

public class lab2_5 {
    public static void main(String[] args) {
        System.out.print("Enter a b c d e f : ");
        Scanner input = new Scanner(System.in);
        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();
        double d = input.nextDouble();
        double e = input.nextDouble();
        double f = input.nextDouble();
        Linear linear1 = new Linear(a,b,c,d,e,f);
        if(linear1.isSolvable(linear1)) System.out.println("The equation has no solution");
        else{
            System.out.println("X is "+linear1.getX(linear1)+" Y is "+linear1.getY(linear1));
        }
    }
}
class Linear {
    private double a, b, c, d,e,f;

    public Linear(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    public Linear(Linear ln){
        this(ln.a,ln.b,ln.c,ln.d,ln.e,ln.f);
    }
    public void geta(double a){
        this.a = a;
    }
    public void getb(double b){
        this.b = b;
    }
    public void getc(double c){
        this.c = c;
    }
    public void getd(double d){
        this.d = d;
    }
    public void gete(double e){
        this.e = e;
    }
    public void getf(double f){
        this.f = f;
    }
    //Method
    public boolean isSolvable(Linear ln){
        return (ln.a * ln.d - ln.b * ln.c) == 0;
    }

    public double getX(Linear ln){
        return (ln.e*ln.d-ln.b*ln.f)/(ln.a*ln.d-ln.b*ln.c);
    }
    public double getY(Linear ln){
        return (ln.a*ln.f-ln.e*ln.c)/(ln.a*ln.d-ln.b*ln.c);
    }
}