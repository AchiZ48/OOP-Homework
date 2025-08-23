/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author studentcs
 */
import java.util.*;

public class ClosestPair {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[][] points = new double[n][2];

        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextDouble();
            points[i][1] = sc.nextDouble();
        }

        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double dist = distance(points[i], points[j]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }

        System.out.printf("%.2f\n", minDist);
    }

    static double distance(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
}

