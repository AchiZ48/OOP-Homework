package lab2;

public class lab2_1 {
        public static void main(String[] args) {
            Complex a = new Complex(1.0, 2.0);
            Complex b = new Complex(3.0, 4.0);
            Complex c = new Complex(a);
            c.add(b);
            c.print();
        }
    }

class Complex {
    private double r, i;
    Complex(double r, double i) {
        this.r = r; this.i = i;
    }
    Complex(Complex c) {
        this(c.r, c.i);
    }
    public void add(Complex c) {
        r += c.r;
        i += c.i;
    }
    public void minus(Complex c) {
        r -= c.r;
        i -= c.i;
    }
    public void multiply(Complex c) {
        double tmp1,tmp2;
        tmp1 = this.r*c.r-this.i*c.i;
        tmp2 = this.r*c.i+this.i*c.r;
        this.r = tmp1;
        this.i = tmp2;
    }
    public void divide(Complex c) {
        double tmp1,tmp2;
        tmp1 = (this.r*c.r+this.i*c.i)/((c.r*c.r)+(c.i*c.i));
        tmp2 = (this.i*c.r-this.r*c.i)/((c.r*c.r)+(c.i*c.i));
        this.r = tmp1;
        this.i = tmp2;
    }
    public void print() {
        System.out.println(r + "+ i" + i);
    }

}