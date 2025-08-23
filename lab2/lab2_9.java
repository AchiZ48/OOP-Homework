import java.util.Scanner;

public class lab2_9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int max = -99999999;
        int maxprime = -99999999;
        int count = 0;
        boolean isPrime = true;

        while (true) {
            int num = input.nextInt();
            if (num == 0) {
                break;
            }
            if (num > maxprime) {
                for (int i = 2; i <= num/2 ; i++) {
                    if (num%i == 0) {
                        isPrime = false;
                        break;
                    }
                    else{
                        maxprime = num;
                        count = 1;
                        isPrime = true;
                    }
                }

            } else if (num == maxprime) {
                count++;
            }
        }
        if (count > 0) {
            System.out.print(maxprime+" "+count);
        }
        else System.out.print("-1");
    }
}
