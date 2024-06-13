package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1766_문제집 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<Integer>[] adjList = new ArrayList[n + 1];
		for (int i = 1; i < n + 1; i++) {
			adjList[i] = new ArrayList<>();
		}
		int[] degree = new int[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			degree[b]++;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i < n + 1; i++) {
			if (degree[i] == 0) {
				pq.add(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			int node = pq.poll();
			sb.append(node).append(" ");
			for (int e : adjList[node]) {
				if (--degree[e] == 0) {
					pq.add(e);
				}
			}
		}
		System.out.print(sb);
		br.close();
	}
}
