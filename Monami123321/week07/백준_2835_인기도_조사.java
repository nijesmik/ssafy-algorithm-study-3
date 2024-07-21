package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_2835_인기도_조사 {
    static final int END_OF_DAY = 3600 * 24 - 1;
    static long[] segTree = new long[(END_OF_DAY + 1) << 2];
    static long[] lazyVal = new long[(END_OF_DAY + 1) << 2];
    static boolean[] lazyExist = new boolean[(END_OF_DAY + 1) << 2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            int left = getTime(tmp.substring(0, 8));
            int right = getTime(tmp.substring(11));
            if (left <= right) {
                update(1, 0, END_OF_DAY, left, right);
            } else {
                update(1, 0, END_OF_DAY, left, END_OF_DAY);
                update(1, 0, END_OF_DAY, 0, right);
            }
        }
        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            String tmp = br.readLine();
            int left = getTime(tmp.substring(0, 8));
            int right = getTime(tmp.substring(11));
            double val;
            int range;
            if (left <= right) {
                val = query(1, 0, END_OF_DAY, left, right);
                range = right - left + 1;
            } else {
                val = query(1, 0, END_OF_DAY, left, END_OF_DAY) + query(1, 0, END_OF_DAY, 0, right);
                range = END_OF_DAY - left + 1 + right + 1;
            }
            sb.append(String.format("%.10f\n", val / range));
        }
        System.out.print(sb);
        br.close();
    }

    static int getTime(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 3600
                + Integer.parseInt(time.substring(3, 5)) * 60
                + Integer.parseInt(time.substring(6));
    }

    static long update(int node, int nodeLeft, int nodeRight, int left, int right) {
        if (left > nodeRight || right < nodeLeft) {
            return segTree[node];
        }
        if (left <= nodeLeft && nodeRight <= right) {
            lazyExist[node] = true;
            lazyVal[node]++;
            return segTree[node] += nodeRight - nodeLeft + 1;
        }

        int mid = nodeRight + nodeLeft >> 1;
        if (lazyExist[node]) {
            pushDown(node << 1, nodeLeft, mid, lazyVal[node]);
            pushDown(node << 1 | 1, mid + 1, nodeRight, lazyVal[node]);
            lazyVal[node] = 0;
            lazyExist[node] = false;
        }
        long leftVal = update(node << 1, nodeLeft, mid, left, right);
        long rightVal = update(node << 1 | 1, mid + 1, nodeRight, left, right);
        return segTree[node] = leftVal + rightVal;
    }

    static void pushDown(int node, int nodeLeft, int nodeRight, long val) {
        if (nodeLeft == nodeRight) {
            segTree[node] += val;
            return;
        }
        lazyExist[node] = true;
        lazyVal[node] += val;
        segTree[node] += val * (nodeRight - nodeLeft + 1);
    }

    static long query(int node, int nodeLeft, int nodeRight, int left, int right) {
        if (nodeRight < left || nodeLeft > right) {
            return 0;
        }
        if (left <= nodeLeft && nodeRight <= right) {
            return segTree[node];
        }
        int mid = nodeLeft + nodeRight >> 1;
        if (lazyExist[node]) {
            pushDown(node << 1, nodeLeft, mid, lazyVal[node]);
            pushDown(node << 1 | 1, mid + 1, nodeRight, lazyVal[node]);
            lazyExist[node] = false;
            lazyVal[node] = 0;
        }
        long leftVal = query(node << 1, nodeLeft, mid, left, right);
        long rightVal = query(node << 1 | 1, mid + 1, nodeRight, left, right);
        return leftVal + rightVal;
    }


}
