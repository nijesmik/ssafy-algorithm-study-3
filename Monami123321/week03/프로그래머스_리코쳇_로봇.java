package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_리코쳇_로봇 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int N, M;

    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        int startR = 0;
        int startC = 0;
        int endR = 0;
        int endC = 0;

        map = new int[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i].charAt(j) == 'D') {
                    map[i][j] = 1;
                } else if (board[i].charAt(j) == 'R') {
                    startR = i;
                    startC = j;
                } else if (board[i].charAt(j) == 'G') {
                    endR = i;
                    endC = j;
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[]{startR, startC, 0});
        visited[startR][startC] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            int t = now[2];

            if (r == endR && c == endC) {
                return t;
            }

            for (int i = 0; i < 4; ++i) {
                int[] tmp = getPos(r, c, i);
                int nr = tmp[0];
                int nc = tmp[1];

                if (visited[nr][nc]) {
                    continue;
                }
                q.add(new int[]{nr, nc, t + 1});
                visited[nr][nc] = true;
            }

        }
        return -1;

    }

    static int[] getPos(int r, int c, int dir) {
        while (true) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (nr < 0 || nr > N - 1 || nc < 0 || nc > M - 1 || map[nr][nc] != 0) {
                return new int[]{r, c};
            } else {
                r = nr;
                c = nc;
            }
        }
    }
}
