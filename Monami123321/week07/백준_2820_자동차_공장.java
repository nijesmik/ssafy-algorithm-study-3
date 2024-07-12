package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_2820_자동차_공장 {
    static List<Integer>[] adjList;
    static int[] segTree, lazyVal, start, end, initVal;
    static boolean[] lazyExist;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        initVal = new int[n + 1];

        initVal[1] = Integer.parseInt(br.readLine());
        for (int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            initVal[i] = val;
            adjList[parent].add(i);
        }
        start = new int[n + 1];
        end = new int[n + 1];

        dfs(1);
        segTree = new int[n << 2];
        lazyVal = new int[n << 2];
        lazyExist = new boolean[n << 2];
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().charAt(0) == 'p') {
                int node = Integer.parseInt(st.nextToken());
                int left = start[node] + 1;
                int right = end[node];
                if (left > right) {
                    continue;
                }
                int val = Integer.parseInt(st.nextToken());
                update(1, 0, n - 1, left, right, val);
            } else {
                int node = Integer.parseInt(st.nextToken());
                int index = start[node];
                sb.append(query(1, 0, n - 1, index) + initVal[node]).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

    static void dfs(int node) {
        start[node] = cnt;
        adjList[node].forEach(e -> {
            ++cnt;
            dfs(e);
        });
        end[node] = cnt;
    }

    static void update(int node, int nodeLeft, int nodeRight, int left, int right, int val) {
        if (nodeRight < left || right < nodeLeft) {
            return;
        }
        if (left <= nodeLeft && nodeRight <= right) {
            lazyExist[node] = true;
            lazyVal[node] += val;
            return;
        }
        int mid = nodeLeft + nodeRight >> 1;
        if (lazyExist[node]) {
            lazyExist[node] = false;
            propagate(node << 1, nodeLeft, mid, lazyVal[node]);
            propagate(node << 1 | 1, mid + 1, nodeRight, lazyVal[node]);
            lazyVal[node] = 0;
        }
        update(node << 1, nodeLeft, mid, left, right, val);
        update(node << 1 | 1, mid + 1, nodeRight, left, right, val);
    }

    static void propagate(int node, int nodeLeft, int nodeRight, int val) {
        if (nodeLeft == nodeRight) {
            segTree[node] += val;
            return;
        }
        lazyVal[node] += val;
        lazyExist[node] = true;
    }

    static int query(int node, int nodeLeft, int nodeRight, int index) {
        if (index < nodeLeft || nodeRight < index) {
            return 0;
        }
        if (nodeLeft == index && nodeRight == index) {
            if (lazyExist[node]) {
                lazyExist[node] = false;
                segTree[node] += lazyVal[node];
                lazyVal[node] = 0;
            }
            return segTree[node];
        }
        int mid = nodeLeft + nodeRight >> 1;
        if (lazyExist[node]) {
            lazyExist[node] = false;
            propagate(node << 1, nodeLeft, mid, lazyVal[node]);
            propagate(node << 1 | 1, mid + 1, nodeRight, lazyVal[node]);
            lazyVal[node] = 0;
        }
        int leftVal = query(node << 1, nodeLeft, mid, index);
        int rightVal = query(node << 1 | 1, mid + 1, nodeRight, index);
        return leftVal + rightVal;
    }
}

