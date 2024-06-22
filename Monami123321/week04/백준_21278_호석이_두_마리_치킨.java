package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_21278_호석이_두_마리_치킨 {
    static final int INF = 1 << 20;
    static int start = 0;
    static int end = 0;
    static int ans = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = dist[b][a] = 1;
        }

        for (int m = 1; m < n + 1; m++) {
            for (int s = 1; s < n + 1; s++) {
                for (int e = 1; e < n + 1; e++) {
                    if (dist[s][e] > dist[s][m] + dist[m][e]) {
                        dist[s][e] = dist[s][m] + dist[m][e];
                    }
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                check(dist, i, j);
            }
        }
        System.out.printf("%d %d %d", start, end, ans);
        br.close();
    }

    static void check(int[][] dist, int a, int b) {
        int cnt = 0;
        for (int i = 1; i < dist.length; i++) {
            cnt += Math.min(dist[i][a] + dist[a][i], dist[i][b] + dist[b][i]);
        }
        if (ans > cnt) {
            start = a;
            end = b;
            ans = cnt;
        }
    }
}
