package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_16562_친구비_re {
    static int[] parent, cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        cost = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            cost[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int sum = 0;
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            int target = findSet(i);
            if (visited[target]) {
                continue;
            }
            sum += cost[target];
            visited[target] = true;
        }
        if (k < sum) {
            System.out.println("Oh no");
        } else {
            System.out.println(sum);
        }
        br.close();
    }

    static void union(int a, int b) {
        int parentA = findSet(a);
        int parentB = findSet(b);
        if (parentA == parentB) {
            return;
        }
        if (cost[parentA] > cost[parentB]) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
        }
    }

    static int findSet(int a) {
        if (parent[a] != a) {
            return parent[a] = findSet(parent[a]);
        }
        return a;
    }
}
