import java.util.Scanner;

public class findpokemon {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int table_height = scanner.nextInt();
        int table_width = scanner.nextInt();

        if (table_height <= 0 || table_width <= 0)
        {
            System.err.println("Error: Table dimensions must be positive integers.");
            return;
        }

        int[][] pokemon_frequency_table = new int[table_height][table_width];

        for (int row_index = 0; row_index < table_height; row_index++)
        {
            for (int col_index = 0; col_index < table_width; col_index++)
            {
                pokemon_frequency_table[row_index][col_index] = scanner.nextInt();
            }
        }

        int max_total_frequency = -1;
        int best_start_row = -1;
        int best_start_col = -1;

        for (int row_index = 0; row_index < table_height; row_index++)
        {
            for (int col_index = 0; col_index < table_width; col_index++)
            {
                if (col_index + 1 < table_width)
                {
                    int current_frequency_1 = pokemon_frequency_table[row_index][col_index];
                    int current_frequency_2 = pokemon_frequency_table[row_index][col_index + 1];

                    if (Math.abs(current_frequency_1 - current_frequency_2) <= 10)
                    {
                        int current_total_frequency = current_frequency_1 + current_frequency_2;
                        if (current_total_frequency > max_total_frequency)
                        {
                            max_total_frequency = current_total_frequency;
                            best_start_row = row_index + 1;
                            best_start_col = col_index + 1;
                        }
                    }
                }

                if (row_index + 1 < table_height)
                {
                    int current_frequency_1 = pokemon_frequency_table[row_index][col_index];
                    int current_frequency_2 = pokemon_frequency_table[row_index + 1][col_index];

                    if (Math.abs(current_frequency_1 - current_frequency_2) <= 10)
                    {
                        int current_total_frequency = current_frequency_1 + current_frequency_2;
                        if (current_total_frequency > max_total_frequency)
                        {
                            max_total_frequency = current_total_frequency;
                            best_start_row = row_index + 1;
                            best_start_col = col_index + 1;
                        }
                    }
                }
            }
        }

        if (best_start_row != -1 && best_start_col != -1)
        {
            System.out.println(best_start_row + " " + best_start_col);
        } else
        {
            System.out.println("No suitable Pokemon location found.");
        }

        scanner.close();
    }
}
