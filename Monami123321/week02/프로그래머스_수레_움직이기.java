package programmers;

public class 프로그래머스_수레_움직이기 {
	static int ans = Integer.MAX_VALUE;
	static int R, C, redEndR, redEndC, blueEndR, blueEndC;
	static int[][] map;
	static boolean[][] visitedR, visitedB;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};

	public int solution(int[][] maze) {
		R = maze.length;
		C = maze[0].length;
		map = new int[R][C];
		visitedR = new boolean[R][C];
		visitedB = new boolean[R][C];
		int redR = 0;
		int redC = 0;
		int blueR = 0;
		int blueC = 0;
		redEndR = 0;
		redEndC = 0;
		blueEndR = 0;
		blueEndC = 0;

		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				map[i][j] = maze[i][j];
				if (map[i][j] == 1) {
					redR = i;
					redC = j;
				} else if (map[i][j] == 2) {
					blueR = i;
					blueC = j;
				} else if (map[i][j] == 3) {
					redEndR = i;
					redEndC = j;
				} else if (map[i][j] == 4) {
					blueEndR = i;
					blueEndC = j;
				}
			}
		}
		dfs(redR, redC, blueR, blueC, 0);
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}

	static void dfs(int redR, int redC, int blueR, int blueC, int cnt) {
		boolean flagR = redR == redEndR && redC == redEndC;
		boolean flagB = blueR == blueEndR && blueC == blueEndC;
		if (flagR && flagB) {
			ans = Math.min(ans, cnt);
			return;
		}
		visitedR[redR][redC] = true;
		visitedB[blueR][blueC] = true;

		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				int nrr = redR + dr[i];
				int nrc = redC + dc[i];
				int nbr = blueR + dr[j];
				int nbc = blueC + dc[j];
				// 도착한 수레는 고정
				if (flagR) {
					nrr = redEndR;
					nrc = redEndC;
				} else if (flagB) {
					nbr = blueEndR;
					nbc = blueEndC;
				}
				// 격자 범위 내
				if (nrr < 0 || nrr > R - 1 || nrc < 0 || nrc > C - 1) {
					continue;
				}
				if (nbr < 0 || nbr > R - 1 || nbc < 0 || nbc > C - 1) {
					continue;
				}
				// 방문했던 곳
				if ((!flagR && visitedR[nrr][nrc]) || (!flagB && visitedB[nbr][nbc])) {
					continue;
				}
				// 벽
				if (map[nrr][nrc] == 5 || map[nbr][nbc] == 5) {
					continue;
				}
				// 다음 칸이 같음
				if (nrr == nbr && nrc == nbc) {
					continue;
				}
				// 서로 자리 바꿈
				if (nrr == blueR && nrc == blueC && nbr == redR && nbc == redC) {
					continue;
				}
				dfs(nrr, nrc, nbr, nbc, cnt + 1);
			}

		}

		visitedR[redR][redC] = false;
		visitedB[blueR][blueC] = false;

	}
}