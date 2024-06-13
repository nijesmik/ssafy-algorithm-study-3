package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_석유_시추 {

	static int[] sum;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R, C;

	static int[] bfs(int startR, int startC, int[][] map) {
		int minIndex = Integer.MAX_VALUE;
		int maxIndex = Integer.MIN_VALUE;
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {startR, startC});
		visited[startR][startC] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0];
			int c = now[1];
			maxIndex = Math.max(maxIndex, c);
			minIndex = Math.min(minIndex, c);
			cnt++;

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nr > R - 1 || nc < 0 || nc > C - 1 || visited[nr][nc] || map[nr][nc] == 0) {
					continue;
				}
				queue.add(new int[] {nr, nc});
				visited[nr][nc] = true;
			}

		}
		return new int[] {minIndex, maxIndex, cnt};
	}

	public int solution(int[][] land) {
		R = land.length;
		C = land[0].length;
		sum = new int[C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (visited[i][j] || land[i][j] == 0) {
					continue;
				}
				int[] res = bfs(i, j, land);
				for (int k = res[0]; k <= res[1]; k++) {
					sum[k] += res[2];
				}
			}
		}
		int ans = sum[0];
		for (int i = 1; i < C; i++) {
			ans = Math.max(ans, sum[i]);

		}
		return ans;

	}
}