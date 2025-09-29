package lab1;

import java.util.Scanner;

import static java.lang.Math.pow;

public class lab1_7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        int n = input.nextInt();
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += (int) pow(x, i);
        }
        System.out.println(sum);
    }
}