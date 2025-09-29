package lab4;

import java.util.Scanner;

public class Lab4_10 {
    private static final int MOVE_LEFT = 1;
    private static final int MOVE_RIGHT = 2;
    private static final int STAY = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int m_lanes = scanner.nextInt();
            if (m_lanes < 2 || m_lanes > 40) {
                System.err.println("Error: Number of lanes (m) must be between 2 and 40.");
                return;
            }

            int n_start_lane = scanner.nextInt();
            if (n_start_lane < 1 || n_start_lane > m_lanes) {
                System.err.println("Error: Starting lane (n) must be between 1 and m.");
                return;
            }
            int start_lane_idx = n_start_lane - 1;

            int t_total_time = scanner.nextInt();
            if (t_total_time < 1 || t_total_time > 100) {
                System.err.println("Error: Total time units (t) must be between 1 and 100.");
                return;
            }

            int[][] road_map = new int[t_total_time + 1][m_lanes];
            for (int time = 1; time <= t_total_time; time++) {
                for (int lane_idx = 0; lane_idx < m_lanes; lane_idx++) {
                    road_map[time][lane_idx] = scanner.nextInt();
                }
            }

            boolean[][] can_reach = new boolean[t_total_time + 1][m_lanes];
            int[][] move_history = new int[t_total_time + 1][m_lanes];

            can_reach[0][start_lane_idx] = true;

            for (int time = 1; time <= t_total_time; time++) {
                for (int lane = 0; lane < m_lanes; lane++) {
                    if (road_map[time][lane] == 1) {
                        can_reach[time][lane] = false;
                        continue;
                    }

                    if (can_reach[time - 1][lane]) {
                        can_reach[time][lane] = true;
                        move_history[time][lane] = STAY;
                    }

                    if ((lane + 1 < m_lanes) && can_reach[time - 1][lane + 1]) {
                        if (!can_reach[time][lane]) {
                            can_reach[time][lane] = true;
                            move_history[time][lane] = MOVE_LEFT;
                        }
                    }

                    // Check if reachable from previous time step by moving right (move 2)
                    if ((lane - 1 >= 0) && can_reach[time - 1][lane - 1]) {
                        if (!can_reach[time][lane]) {
                            can_reach[time][lane] = true;
                            move_history[time][lane] = MOVE_RIGHT;
                        }
                    }
                }
            }

            int[] final_path = new int[t_total_time];
            int final_lane_idx = -1;

            for (int lane_idx = 0; lane_idx < m_lanes; lane_idx++) {
                if (can_reach[t_total_time][lane_idx]) {
                    final_lane_idx = lane_idx;
                    break;
                }
            }

            if (final_lane_idx == -1) {
                System.out.println("No safe path found.");
                return;
            }

            int current_lane = final_lane_idx;
            for (int time_step = t_total_time; time_step >= 1; time_step--) {
                int movement = move_history[time_step][current_lane];
                final_path[time_step - 1] = movement;

                if (movement == STAY) {
                } else if (movement == MOVE_LEFT) {
                    current_lane++;
                } else if (movement == MOVE_RIGHT) {
                    current_lane--;
                }
            }

            for (int move : final_path) {
                System.out.println(move);
            }

        } catch (java.util.InputMismatchException e) {
            System.err.println("Error: Please enter integer numbers only.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
