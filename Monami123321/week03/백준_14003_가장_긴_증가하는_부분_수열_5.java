package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_14003_가장_긴_증가하는_부분_수열_5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] tmp = new int[n];
        int[] lis = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            tmp[i] = Integer.MAX_VALUE;
        }
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            int index = Arrays.binarySearch(tmp, arr[i]);
            if (index < 0) {
                index = -(index + 1);
            }
            tmp[index] = arr[i];
            maxIndex = Math.max(maxIndex, index);
            lis[i] = index;
        }
        Stack<Integer> stack = new Stack<>();
        int index = n - 1;
        while (maxIndex >= 0) {
            if (lis[index] == maxIndex) {
                stack.push(arr[index]);
                --maxIndex;
            }
            --index;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.print(sb);


        br.close();
    }
}
