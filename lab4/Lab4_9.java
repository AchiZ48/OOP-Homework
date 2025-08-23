import java.util.Scanner;

public class consecutivefour {
    public static boolean IsConsecutiveFour(int[][] values) {
        int num_rows = values[0].length;
        int num_cols = values.length;

        // Check horizontal
        for (int row_index = 0; row_index < num_rows; row_index++) {
            for (int col_index = 0; col_index <= num_cols - 4; col_index++) {
                if (values[row_index][col_index] == values[row_index][col_index + 1] &&
                        values[row_index][col_index] == values[row_index][col_index + 2] &&
                        values[row_index][col_index] == values[row_index][col_index + 3]) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int col_index = 0; col_index < num_cols; col_index++) {
            for (int row_index = 0; row_index <= num_rows - 4; row_index++) {
                if (values[row_index][col_index] == values[row_index + 1][col_index] &&
                        values[row_index][col_index] == values[row_index + 2][col_index] &&
                        values[row_index][col_index] == values[row_index + 3][col_index]) {
                    return true;
                }
            }
        }

        // Check diagonal (down-right)
        for (int row_index = 0; row_index <= num_rows - 4; row_index++) {
            for (int col_index = 0; col_index <= num_cols - 4; col_index++) {
                if (values[row_index][col_index] == values[row_index + 1][col_index + 1] &&
                        values[row_index][col_index] == values[row_index + 2][col_index + 2] &&
                        values[row_index][col_index] == values[row_index + 3][col_index + 3]) {
                    return true;
                }
            }
        }

        // Check diagonal (up-right)
        for (int row_index = 3; row_index < num_rows; row_index++) {
            for (int col_index = 0; col_index <= num_cols - 4; col_index++) {
                if (values[row_index][col_index] == values[row_index - 1][col_index + 1] &&
                        values[row_index][col_index] == values[row_index - 2][col_index + 2] &&
                        values[row_index][col_index] == values[row_index - 3][col_index + 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner input_scanner = new Scanner(System.in);

        try {
            int num_cols = input_scanner.nextInt();
            int num_rows = input_scanner.nextInt();


            if (num_rows < 4 && num_cols < 4) {
                System.out.println("Array must be at least 4xN or Nx4 to contain 4 consecutive numbers.");
                return;
            }

            int[][] user_values = new int[num_rows][num_cols];

            for (int row_index = 0; row_index < num_rows; row_index++) {
                for (int col_index = 0; col_index < num_cols; col_index++) {
                    user_values[row_index][col_index] = input_scanner.nextInt();
                }
            }

            boolean has_consecutive_four = IsConsecutiveFour(user_values);

            int result_output = has_consecutive_four ? 1 : 0;
            System.out.println(result_output);

        } catch (java.util.InputMismatchException e) {
            System.err.println("Error: Please enter integer numbers only.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            input_scanner.close();
        }
    }
}
