package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 앱 {
    static class Process implements Comparable<Process>{
        int m; int t;

        @Override
        public int compareTo(Process o) {
            return this.t - o.t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Process[] processes = new Process[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            processes[i] = new Process();
            processes[i].m = Integer.parseInt(st.nextToken());
        }

        int maxT = 0; // 모든 프로세스를 메모리에서 지우고 재시동할 때 드는 총 시간 (dp의 index 값이 될 것임)
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            processes[i].t = Integer.parseInt(st.nextToken());
            maxT += processes[i].t;
        }

        int[] dp = new int[maxT+1];
        Arrays.sort(processes); // 시간 오름차순으로 sort

        for (int i = 0; i < N; i++) {
            for (int j = maxT; j >= processes[i].t; j--) { // 역순탐색!!!!
                int t = processes[i].t;
                int m = processes[i].m;
                dp[j] = Math.max(dp[j - t] + m, dp[j]);
            }
        }

        for (int i = 0; i <= maxT; i++) { // 정순 탐색하면서 값 나오면 바로 break;
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}
