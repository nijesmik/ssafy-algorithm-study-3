package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_1992_쿼드트리 {
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				map[i][j] = tmp[j] - 48;
			}
		}
		System.out.println(compress(n, 0, 0));
		br.close();
	}

	static String compress(int n, int r, int c) {
		int res = check(n, r, c);
		if (res == 0) {
			return "0";
		} else if (res == 1) {
			return "1";
		}

		int next = n >> 1;
		return "(" + compress(next, r, c) + compress(next, r, c + next) + compress(next, r + next, c) + compress(next,
			r + next, c + next) + ")";
	}

	static int check(int n, int r, int c) {
		int res = map[r][c];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[r + i][c + j] != res) {
					return -1;
				}
			}
		}
		return res;
	}
}
