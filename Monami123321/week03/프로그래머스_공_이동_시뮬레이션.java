package programmers;

public class 프로그래머스_공_이동_시뮬레이션 {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long left = y;
        long right = y;
        long ceil = x;
        long floor = x;
        for (int i = queries.length - 1; i >= 0; --i) {
            int cmd = queries[i][0];
            int d = queries[i][1];
            switch (cmd) {
                case 0:
                    if (left != 0) {
                        left += d;
                    }
                    right = Math.min(m - 1, right + d);
                    break;

                case 1:
                    if (right != m - 1) {
                        right -= d;
                    }
                    left = Math.max(0, left - d);
                    break;

                case 2:
                    if (ceil != 0) {
                        ceil += d;
                    }
                    floor = Math.min(n - 1, floor + d);
                    break;
                case 3:
                    if (floor != n - 1) {
                        floor -= d;
                    }
                    ceil = Math.max(0, ceil - d);
                    break;
                default:
                    break;
            }
            if (left > m - 1 || right < 0 || ceil > n - 1 || floor < 0) {
                return 0;
            }
        }
        return (right - left + 1) * (floor - ceil + 1);
    }
}
