package y24Jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek9177 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            // 데이터 논리 작업

            st = new StringTokenizer(bf.readLine());
            String w1 = st.nextToken();
            String w2 = st.nextToken();
            String result = st.nextToken();

            sb.append("Data set " + i + ": ");
            if (dataCheck(w1, w2, result)) {
                sb.append("yes");
            } else {
                sb.append("no");
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static boolean dataCheck(String w1, String w2, String result) {

        int p = w1.length();
        int q = w2.length();

        boolean[][] chk = new boolean[p + 1][q + 1];

        chk[0][0] = true;

        // 기본 값 채워주기

        for (int i = 1; i <= p; i++) {
            chk[i][0] = chk[i - 1][0] && (
                    w1.charAt(i - 1) == result.charAt(i - 1)
            );
        }

        for (int i = 1; i <= q; i++) {
            chk[0][i] = chk[0][i - 1] && (
                    w2.charAt(i - 1) == result.charAt(i - 1)
            );
        }

        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= q; j++) {
                chk[i][j] =
                        (chk[i - 1][j] && (w1.charAt(i - 1) == result.charAt(i + j - 1)) ||
                                (chk[i][j - 1] && (w2.charAt(j - 1) == result.charAt(i + j - 1))));

            }
        }

//        for (int i = 0; i <= p; i++) {
//            System.out.println(Arrays.toString(chk[i]));
//        }

        return chk[p][q];
    }
}
