package y24Jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek2240 {
    static int T;
    static int W;

    static int[] drop;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        drop = new int[T + 1];

        for (int i = 1; i < T + 1; i++) {
            drop[i] = Integer.parseInt(bf.readLine());
        }

        int[][][] dp = new int[3][T + 1][W + 1];

        for (int i = 1; i <= T; i++) {
            // 삼항 연산자 연습
            dp[1][i][0] = dp[1][i - 1][0] + (drop[i] == 1 ? 1 : 0);
        }

        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= W; j++) {

                dp[1][i][j] = Math.max(
                        dp[1][i - 1][j],
                        dp[2][i - 1][j - 1]
                );

                dp[2][i][j] = Math.max(
                        dp[2][i - 1][j],
                        dp[1][i - 1][j - 1]
                );
                if (drop[i] == 1) {
                    dp[1][i][j]++;

                } else {
                    dp[2][i][j]++;
                }

            }
        }

        int maxCnt = Integer.MIN_VALUE;
        for (int i = 0; i <= W; i++) {
            maxCnt = Math.max(
                    maxCnt,
                    dp[1][T][i]
            );

            maxCnt = Math.max(
                    maxCnt,
                    dp[2][T][i]
            );
        }
        System.out.println(maxCnt);

    }
}
