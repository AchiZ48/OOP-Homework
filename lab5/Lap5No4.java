/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lap5no1;

import java.util.ArrayList;
import java.util.Scanner;

public class Lap5No4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();
        int K = sc.nextInt();

        ArrayList<ArrayList<Integer>> area = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                row.add(sc.nextInt());
            }
            area.add(row);
        }

        int maxSum = 0;

        for (int i = 0; i <= M - K; i++) {
            for (int j = 0; j <= N - K; j++) {
                int sum = 0;
                for (int x = 0; x < K; x++) {
                    for (int y = 0; y < K; y++) {
                        sum += area.get(i + x).get(j + y);
                    }
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        System.out.println(maxSum);
    }
}
