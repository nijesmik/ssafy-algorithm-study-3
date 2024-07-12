/*
1. 트리는 비선형 -> 선형으로 처리 가능하게 바꾸기 -> dfs -> n
2. 정점 번호를 [l, r] 로 치환
3. 정점에 대한 업데이트를 구간 업데이트로, 쿼리는 그대로 -> nlogn
 */
package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_14268_회사_문화_2 {
    static int[] start, end, segTree, lazyVal;
    static boolean[] lazyExist;
    static List<Integer>[] adjList;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        segTree = new int[n << 2];
        lazyVal = new int[n << 2];
        lazyExist = new boolean[n << 2];
        start = new int[n + 1];
        end = new int[n + 1];
        adjList = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 2; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            adjList[parent].add(i);
        }
        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                int node = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());
                int left = start[node];
                int right = end[node];
                update(1, 0, n - 1, left, right, val);
            } else {
                int target = start[Integer.parseInt(st.nextToken())];
                sb.append(query(1, 0, n - 1, target)).append("\n");
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
        if (nodeRight < left || nodeLeft > right) {
            return;
        }
        if (left <= nodeLeft && nodeRight <= right) {
            lazyExist[node] = true;
            lazyVal[node] += val;
            return;
        }
        int mid = nodeLeft + nodeRight >> 1;
        if (lazyExist[node]) {
            propagate(node << 1, nodeLeft, mid, lazyVal[node]);
            propagate(node << 1 | 1, mid + 1, nodeRight, lazyVal[node]);
            lazyExist[node] = false;
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
        lazyExist[node] = true;
        lazyVal[node] += val;
    }


    static int query(int node, int nodeLeft, int nodeRight, int index) {
        if (nodeLeft > index || nodeRight < index) {
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
            propagate(node << 1, nodeLeft, mid, lazyVal[node]);
            propagate(node << 1 | 1, mid + 1, nodeRight, lazyVal[node]);
            lazyVal[node] = 0;
            lazyExist[node] = false;
        }
        int leftVal = query(node << 1, nodeLeft, mid, index);
        int rightVal = query(node << 1 | 1, mid + 1, nodeRight, index);
        return leftVal + rightVal;
    }
}

