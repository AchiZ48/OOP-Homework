/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

/**
 *
 * @author student
 */
import java.util.Scanner;

class MyTriangle {
    public boolean isValid(double s1, double s2, double s3) {
        return (s1 + s2 > s3) && (s1 + s3 > s2) && (s2 + s3 > s1);
    }

    public double area(double s1, double s2, double s3) {
        double s = (s1 + s2 + s3) / 2;
        return Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
    }
}

public class TestTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble(), b = sc.nextDouble(), c = sc.nextDouble();
        MyTriangle t = new MyTriangle();

        if (t.isValid(a, b, c)) {
            System.out.println(1);
            System.out.printf("%.2f\n", t.area(a, b, c));
        } else {
            System.out.println(0);
        }

        sc.close();
    }
}