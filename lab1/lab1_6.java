import java.util.Scanner;

public class lab1_6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= x; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }
    }
}
