package programmers;

public class 프로그래머스_행렬_테두리_회전하기 {
    private static int[][] map;
    private static int n, m;

    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        n = rows;
        m = columns;
        int num = 1;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                map[i][j] = num++;
            }
        }
        int index = 0;
        int[] ans = new int[queries.length];
        for (int[] q : queries) {
            ans[index++] = query(q[0] - 1, q[1] - 1, q[2] - 1, q[3] - 1);
        }
        return ans;
    }

    private int query(int r1, int c1, int r2, int c2) {
        int res = Integer.MAX_VALUE;
        int prev = map[r1][c1];
        int now = prev;
        // 위
        for (int i = c1 + 1; i <= c2; ++i) {
            res = Math.min(res, prev);
            now = map[r1][i];
            map[r1][i] = prev;
            prev = now;
        }
        // 오른쪽
        for (int i = r1 + 1; i <= r2; ++i) {
            res = Math.min(res, prev);
            now = map[i][c2];
            map[i][c2] = prev;
            prev = now;
        }
        // 아래
        for (int i = c2 - 1; i >= c1; --i) {
            res = Math.min(res, prev);
            now = map[r2][i];
            map[r2][i] = prev;
            prev = now;
        }

        // 왼
        for (int i = r2 - 1; i >= r1; --i) {
            res = Math.min(res, prev);
            now = map[i][c1];
            map[i][c1] = prev;
            prev = now;
        }
        return res;
    }
}
