package nijesmik.week04.블록_이동하기;

import java.util.*;

class Solution {
    int N;
    int[][] board;
    Queue<Robot> q;
    boolean[][][] visited;

    public int solution(int[][] board) {
        N = board.length;
        this.board = board;

        int time = 0;
        visited = new boolean[2][N][N];
        q = new ArrayDeque<>();

        addQueue(0, 1, 0);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Robot cur = q.poll();
                if (cur.r == N - 1 && cur.c == N - 1) {
                    return time;
                }
                if (cur.v == 0) {
                    moveVerticalRobot(cur);
                } else {
                    moveHorizontalRobot(cur);
                }
            }
            time++;
        }

        return time;
    }

    void moveHorizontalRobot(Robot cur) {
        if (canGo(cur.r - 2, cur.c)) {
            addQueue(cur.r - 1, cur.c, 1);
        }
        if (canGo(cur.r + 1, cur.c)) {
            addQueue(cur.r + 1, cur.c, 1);
        }
        for (int i = 0; i < 2; i++) {

            int c = cur.c + i;
            int r1 = cur.r - 1;
            int r2 = cur.r;
            if (canGo(r1, c - 1 + i) && canGo(r2, c - 1 + i)) {
                addQueue(r1, c, 0);
                addQueue(r2, c, 0);
                addQueue(cur.r, c - 1 + i, 1);
            }
        }
    }

    void moveVerticalRobot(Robot cur) {
        if (canGo(cur.r, cur.c - 2)) {
            addQueue(cur.r, cur.c - 1, 0);
        }
        if (canGo(cur.r, cur.c + 1)) {
            addQueue(cur.r, cur.c + 1, 0);
        }
        for (int i = 0; i < 2; i++) {
            int r = cur.r + i;
            int c1 = cur.c - 1;
            int c2 = cur.c;
            if (canGo(r - 1 + i, c1) && canGo(r - 1 + i, c2)) {
                addQueue(r, c1, 1);
                addQueue(r, c2, 1);
                addQueue(r - 1 + i, cur.c, 0);
            }
        }
    }

    boolean canGo(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= N) {
            return false;
        }
        if (board[r][c] == 1) {
            return false;
        }
        return true;
    }

    void addQueue(int r, int c, int v) {
        if (visited[v][r][c]) {
            return;
        }
        q.add(new Robot(r, c, v));
        visited[v][r][c] = true;
    }

    class Robot {
        int r, c;
        int v; // 0 : vertical, 1 : horizontal

        Robot(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
}
