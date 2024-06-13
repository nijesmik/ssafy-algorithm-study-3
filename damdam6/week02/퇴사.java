package y24Jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek14501 {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        int[][] meeting = new int[N + 1][2];
        StringTokenizer st;

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            meeting[i][0] = Integer.parseInt(st.nextToken());
            meeting[i][1] = Integer.parseInt(st.nextToken());
        }

        // 이번껄 썻냐 안썻냐 결정
        // N 번째 날짜에 대해서
        // max값 /  day
        int[][][] maxGet = new int[2][N + 1][2];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < i; j++) {
                // today 상담 안할 때 기준

                // 날짜 체킹
                if (maxGet[0][j][1] <= i) {
                    // 값 비교
                    if (maxGet[0][i][0] < maxGet[0][j][0]) {

                        maxGet[0][i][0] = maxGet[0][j][0];
                        maxGet[0][i][1] = maxGet[0][j][1];

                    } else if (maxGet[0][i][0] == maxGet[0][j][0]) {
                        // 동일할 땐 날짜 체크
                        maxGet[0][i][1] = Math.min(
                                maxGet[0][i][1],
                                maxGet[0][j][1]
                        );
                    }
                    ;

                }
                if (maxGet[1][j][1] <= i) {
                    // 값 비교
                    if (maxGet[0][i][0] < maxGet[1][j][0]) {
                        maxGet[0][i][0] = maxGet[1][j][0];
                        maxGet[0][i][1] = maxGet[1][j][1];
                    } else if (maxGet[0][i][0] == maxGet[1][j][0]) {
                        // 동일할 땐 날짜 체크
                        maxGet[0][i][1] = Math.min(
                                maxGet[0][i][1],
                                maxGet[1][j][1]
                        );
                    }
                    ;
                }


            }

            if (meeting[i][0] + i > N) {
                continue;
            }
            ;
            // 값
            maxGet[1][i][0] = maxGet[0][i][0] + meeting[i][1];
            maxGet[1][i][1] = meeting[i][0] + i;

        }
        for (int i = 0; i < N + 1; i++) {
            System.out.println("자기 자신 안쓴거" + Arrays.toString(maxGet[0][i]));
            ;
            System.out.println("자기 자신 쓴거" + Arrays.toString(maxGet[1][i]));
            ;


        }

        System.out.println(Math.max(maxGet[0][N][0], maxGet[1][N][0]));
    }
}
