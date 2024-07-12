package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_2371_파일_구별하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] list = new ArrayList[n];
        StringTokenizer st;
        int len = 0;
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) {
                    break;
                }
                list[i].add(num);
            }
            len = Math.max(len, list[i].size());
        }
        for (int i = 0; i < n; i++) {
            if (list[i].size() < len) {
                int l = len - list[i].size();
                for (int j = 0; j < l; j++) {
                    list[i].add(0);
                }
            }
        }
        int ans = -1;
        outer:
        while (true) {
            ans++;
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int now = list[i].get(ans);
                if (now == 0) {
                    continue;
                }
                if (set.contains(now)) {
                    continue outer;
                } else {
                    set.add(now);
                }
            }
            break;
        }
        System.out.println(ans + 1);
        br.close();
    }
}
