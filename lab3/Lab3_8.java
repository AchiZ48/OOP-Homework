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

public class EstimatePI {
    public static double m(int i) {
        double sum = 0;
        for (int k = 0; k < i; k++) {
            sum += Math.pow(-1, k) / (2 * k + 1);
        }
        return 4 * sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.printf("%.4f\n", m(i));
        sc.close();
    }
}
