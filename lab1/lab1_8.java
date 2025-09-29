package lab1;

import java.util.Scanner;

public class lab1_8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int a0 = input.nextInt();
        int a1 = input.nextInt();
        for (int i = 2; i <= n+2; i++) {
            int a2 = (int) (Math.pow(i,2)*a1-a0+Math.pow(3,i));
            System.out.print(a0+" ");
            a0 = a1;
            a1 = a2;
        }
    }
}
