package nijesmik.week03.공_이동_시뮬레이션;

class Solution {
    static final int[] dr = { 0, 0, -1, 1 }, dc = { -1, 1, 0, 0 };
    int n, m, x, y;

    public long solution(int n, int m, int x, int y, int[][] queries) {
        this.n = n;
        this.m = m;
        this.x = x;
        this.y = y;

        long top = x, left = y;
        long bottom = x, right = y;

        int i = queries.length;
        while (i-- > 0) {
            int command = queries[i][0];
            int dx = queries[i][1];

            if (command == 0) {
                if (left != 0) {
                    left += dx;
                }
                if (left >= m) {
                    return 0;
                }
                right = Math.min(m - 1, right + dx);
            } else if (command == 1) {
                if (right != m - 1) {
                    right -= dx;
                }
                if (right < 0) {
                    return 0;
                }
                left = Math.max(0, left - dx);
            } else if (command == 2) {
                if (top != 0) {
                    top += dx;
                }
                if (top >= n) {
                    return 0;
                }
                bottom = Math.min(n - 1, bottom + dx);
            } else if (command == 3) {
                if (bottom != n - 1) {
                    bottom -= dx;
                }
                if (bottom < 0) {
                    return 0;
                }
                top = Math.max(0, top - dx);
            }
        }

        return (bottom - top + 1) * (right - left + 1);
    }
}