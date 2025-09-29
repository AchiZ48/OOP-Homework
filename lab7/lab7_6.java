package lab7;

import java.io.*;
import java.util.*;

public class lab7_6 {
    static final int[] DX = {1, -1, 0, 0};
    static final int[] DY = {0, 0, 1, -1};
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int rs = Integer.parseInt(st.nextToken()) - 1;
        int cs = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int re = Integer.parseInt(st.nextToken()) - 1;
        int ce = Integer.parseInt(st.nextToken()) - 1;

        int[][] grid = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] distS = bfsPassable(grid, rs, cs);
        int[][] distE = bfsPassable(grid, re, ce);

        int breakableCount = 0;

        long bestCells = Long.MAX_VALUE;


        if (distS[re][ce] < INF) {
            bestCells = Math.min(bestCells, distS[re][ce] + 1L);
        }

        for (int x = 0; x < M; x++) {
            for (int y = 0; y < N; y++) {
                if (grid[x][y] != 0) continue;

                List<int[]> nbS = new ArrayList<>();
                List<int[]> nbE = new ArrayList<>();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + DX[dir], ny = y + DY[dir];
                    if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                    if (grid[nx][ny] != 1) continue;

                    if (distS[nx][ny] < INF) nbS.add(new int[]{nx, ny});
                    if (distE[nx][ny] < INF) nbE.add(new int[]{nx, ny});
                }

                if (!nbS.isEmpty() && !nbE.isEmpty()) {
                    breakableCount++;
                    for (int[] a : nbS) {
                        for (int[] b : nbE) {
                            long edges = (long) distS[a[0]][a[1]] + 1  + 1  + distE[b[0]][b[1]];
                            long cells = edges + 1;
                            if (cells < bestCells) bestCells = cells;
                        }
                    }
                }
            }
        }

        if (bestCells == Long.MAX_VALUE) bestCells = -1;

        System.out.println(breakableCount);
        System.out.println(bestCells);
    }

    static int[][] bfsPassable(int[][] grid, int sx, int sy) {
        int M = grid.length, N = grid[0].length;
        int[][] dist = new int[M][N];
        for (int i = 0; i < M; i++) Arrays.fill(dist[i], INF);

        Deque<int[]> dq = new ArrayDeque<>();
        if (grid[sx][sy] == 1) {
            dist[sx][sy] = 0;
            dq.add(new int[]{sx, sy});
        }
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0], y = cur[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + DX[k], ny = y + DY[k];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if (grid[nx][ny] != 1) continue;
                if (dist[nx][ny] > dist[x][y] + 1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    dq.add(new int[]{nx, ny});
                }
            }
        }
        return dist;
    }
}

