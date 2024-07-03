package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1018_체스판_다시_칠하기 {
    static int N, M;
    static char[] colors = new char[]{'W', 'B'};
    static char[][] ref = new char[2][8];

    static {
        for (int i = 0; i < 8; i++) {
            ref[0][i] = colors[i & 1];
            ref[1][i] = colors[(i & 1) ^ 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                char[][] now = getBoard(map, i, j);
                ans = Math.min(ans, getCnt(now));
            }
        }
        System.out.println(ans);
        br.close();
    }

    static char[][] getBoard(char[][] map, int r, int c) {
        char[][] res = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                res[i][j] = map[r + i][c + j];
            }
        }
        return res;
    }

    static int getCnt(char[][] board) {
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == ref[i & 1][j]) {
                    cnt1++;
                } else {
                    cnt2++;
                }
            }
        }
        return Math.min(cnt1, cnt2);
    }
}
