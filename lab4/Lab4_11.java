package lab4;

import java.util.Scanner;

public class Lab4_11 {
    private static final int[] DR = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DC = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static void DepthFirstSearch(char[][] grid_map, boolean[][] visited_status, int current_row, int current_col) {
        int num_rows = grid_map.length;
        int num_cols = grid_map[0].length;

        if (current_row < 0 || current_row >= num_rows || current_col < 0 || current_col >= num_cols) {
            return;
        }
        if (grid_map[current_row][current_col] == '*') {
            return;
        }
        if (visited_status[current_row][current_col]) {
            return;
        }

        visited_status[current_row][current_col] = true;

        for (int i = 0; i < 8; i++) {
            DepthFirstSearch(grid_map, visited_status, current_row + DR[i], current_col + DC[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                int m_rows = scanner.nextInt();
                int n_cols = scanner.nextInt();

                if (m_rows == 0) {
                    break;
                }

                if (m_rows < 1 || m_rows > 100 || n_cols < 1 || n_cols > 100) {
                    System.err.println("Error: Dimensions m and n must be between 1 and 100.");
                    scanner.nextLine();
                    continue;
                }

                scanner.nextLine();

                char[][] grid_map = new char[m_rows][n_cols];
                boolean[][] visited_status = new boolean[m_rows][n_cols];

                for (int row_idx = 0; row_idx < m_rows; row_idx++) {
                    StringBuilder row_chars_builder = new StringBuilder();
                    while (row_chars_builder.length() < n_cols) {
                        row_chars_builder.append(scanner.next());
                    }

                    String row_str = row_chars_builder.substring(0, n_cols);

                    for (int col_idx = 0; col_idx < n_cols; col_idx++) {
                        grid_map[row_idx][col_idx] = row_str.charAt(col_idx);
                    }
                }

                int oil_pocket_count = 0;

                for (int row_idx = 0; row_idx < m_rows; row_idx++) {
                    for (int col_idx = 0; col_idx < n_cols; col_idx++) {
                        if (grid_map[row_idx][col_idx] == '@' && !visited_status[row_idx][col_idx]) {
                            oil_pocket_count++;
                            DepthFirstSearch(grid_map, visited_status, row_idx, col_idx);
                        }
                    }
                }
                System.out.println(oil_pocket_count);
            }
        } catch (java.util.InputMismatchException e) {
            System.err.println("Error: Please enter integer numbers for dimensions.");
        } catch (java.util.NoSuchElementException e) {
            System.err.println("Error: Not enough input provided for the grid or dimensions. Please ensure all characters for the grid are provided.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
