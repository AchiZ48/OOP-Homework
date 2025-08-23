
import java.util.Scanner;

public class mintwoset {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] weights = new int[n];
        int totalSum = 0;

        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            totalSum += weights[i];
        }

        int result = minDifference(weights, totalSum);
        System.out.println(result);
    }

    public static int minDifference(int[] weights, int totalSum) {

        boolean[] dp = new boolean[totalSum + 1];
        dp[0] = true;

        for (int weight : weights) {
            for (int j = totalSum; j >= weight; j--) {
                if (dp[j - weight]) {
                    dp[j] = true;
                }
            }
        }
        int half = totalSum / 2;
        for (int s = half; s >= 0; s--) {
            if (dp[s]) {
                return totalSum - 2 * s;
            }
        }
        return totalSum;
    }
}