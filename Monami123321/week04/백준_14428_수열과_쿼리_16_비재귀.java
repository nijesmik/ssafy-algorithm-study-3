package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14428_수열과_쿼리_16_비재귀 {
    static int[] arr, segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int startIndex = 1;
        while (startIndex < n) {
            startIndex <<= 1;
        }
        segTree = new int[startIndex << 1];
        arr = new int[n + 1];
        arr[0] = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            segTree[startIndex + i - 1] = i;
            update(startIndex + i - 1);
        }
        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                int index = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());
                arr[index] = val;
                update(index + startIndex - 1);
            } else {
                int left = Integer.parseInt(st.nextToken()) + startIndex - 1;
                int right = Integer.parseInt(st.nextToken()) + startIndex - 1;
                sb.append(query(left, right)).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }

    static void update(int index) {
        while (index > 1) {
            if (arr[segTree[index]] < arr[segTree[index ^ 1]]) {
                segTree[index >> 1] = segTree[index];
            } else if (arr[segTree[index]] > arr[segTree[index ^ 1]]) {
                segTree[index >> 1] = segTree[index ^ 1];
            } else {
                segTree[index >> 1] = Math.min(segTree[index], segTree[index ^ 1]);
            }
            index >>= 1;
        }
    }

    static int query(int left, int right) {
        int res = 0;
        while (right > left) {
            if ((left & 1) == 0) {
                left >>= 1;
            } else {
                if (arr[res] > arr[segTree[left]]) {
                    res = segTree[left];
                } else if (arr[res] == arr[segTree[left]] && segTree[left] < res) {
                    res = segTree[left];
                }
                left++;
                left >>= 1;
            }

            if ((right & 1) == 0) {
                if (arr[res] > arr[segTree[right]]) {
                    res = segTree[right];
                } else if (arr[res] == arr[segTree[right]] && res > segTree[right]) {
                    res = segTree[right];
                }
                right--;
                right >>= 1;
            } else {
                right >>= 1;
            }
        }
        if (left == right) {
            if (arr[res] > arr[segTree[left]]) {
                res = segTree[left];
            } else if (arr[res] == arr[segTree[left]] && res > segTree[left]) {
                res = segTree[left];

            }
        }
        return res;
    }
}
