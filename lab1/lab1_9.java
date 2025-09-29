package lab1;

import java.util.Scanner;

public class lab1_9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int max = -99999999;
        int count = 0;

        while (true) {
            int num = input.nextInt();
            if (num == 0) {
                break;
            }

            if (num > max) {
                max = num;
                count = 1;
            } else if (num == max) {
                count++;
            }
        }
        if (count > 0) {
            System.out.print(max+" "+count);
        }
    }
}
