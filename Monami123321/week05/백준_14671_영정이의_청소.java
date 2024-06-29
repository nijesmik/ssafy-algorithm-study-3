package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14671_영정이의_청소 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int flag = 0;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if ((r & 1) == 0 && (c & 1) == 0) {
                flag |= 1;
            } else if ((r & 1) == 0 && (c & 1) != 0) {
                flag |= 2;
            } else if ((r & 1) != 0 && (c & 1) == 0) {
                flag |= 4;
            } else {
                flag |= 8;
            }
        }
        if (flag == 15) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        br.close();
    }
}
