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

public class CheckTriangle {
    public static int checkPosition(double x0, double y0, double x1, double y1, double x2, double y2) {
        double position = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
        if (position == 0) return 0;
        return (position < 0) ? 1 : 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x0 = sc.nextDouble(), y0 = sc.nextDouble();
        double x1 = sc.nextDouble(), y1 = sc.nextDouble();
        double x2 = sc.nextDouble(), y2 = sc.nextDouble();

        System.out.println(checkPosition(x0, y0, x1, y1, x2, y2));
        sc.close();
    }
}
