
import java.util.*;

class Solution {
    // 상하좌우 이동
    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 가로 -> 세로 회전 (↖ ↙ ↗ ↘ 순서)
    static int[][] hRotate = {{-1, 0}, {0, 0}, {-1, 1}, {0, 1}};
    static int[][] hCheck = {{-1, 1}, {1, 1}, {-1, 0}, {1, 0}};
    // 세로 -> 가로 회전 (↖ ↙ ↗ ↘ 순서)
    static int[][] vRotate = {{0, -1}, {1, -1}, {0, 0}, {1, 0}};
    static int[][] vCheck = {{1, -1}, {0, -1}, {1, 1}, {0, 1}};

    static int[][] bd;
    static int ans = 0;
    static boolean[][][] visit;
    static int n;


    public int solution(int[][] board) {
        bd = board;
        n = board.length;

        // 0: 가로, 1: 세로
        visit = new boolean[2][n][n];

        BFS();
        return ans;
    }

    static void BFS() {
        Queue<Robot> q = new LinkedList<>();

        q.offer(new Robot(0, 0, 0, 0));
        visit[0][0][0] = true;

        while(!q.isEmpty()) {
            Robot now = q.poll();
            if((now.dir == 0 && now.x == n - 1 && now.y + 1 == n - 1) ||
                    (now.dir == 1 && now.x + 1 == n - 1 && now.y == n - 1)) {
                ans = now.sec;
                break;
            }

            for(int i=0;i<4;i++) {
                int nX = now.x + move[i][0];
                int nY = now.y + move[i][1];

                if(check(nX, nY, now.dir)) {
                    visit[now.dir][nX][nY] = true;
                    q.offer(new Robot(nX, nY, now.dir, now.sec + 1));
                }
            }

            if(now.dir == 0) { // 가로 -> 세로 회전
                for(int i=0;i<4;i++) {
                    int nX = now.x + hRotate[i][0];
                    int nY = now.y + hRotate[i][1];
                    int nDir = 1;

                    if(check(nX, nY, nDir) && bd[now.x + hCheck[i][0]][now.y + hCheck[i][1]] == 0) {
                        visit[nDir][nX][nY] = true;
                        q.offer(new Robot(nX, nY, nDir, now.sec + 1));
                    }
                }
            }
            else { // 세로 -> 가로 회전
                for(int i=0;i<4;i++) {
                    int nX = now.x + vRotate[i][0];
                    int nY = now.y + vRotate[i][1];
                    int nDir = 0;

                    if(check(nX, nY, nDir) && bd[now.x + vCheck[i][0]][now.y + vCheck[i][1]] == 0) {
                        visit[nDir][nX][nY] = true;
                        q.offer(new Robot(nX, nY, nDir, now.sec + 1));
                    }
                }
            }
        }
    }

    static boolean check(int x, int y, int dir) {
        // 이미 방문한 경우, 배열 범위 벗어난 경우, 벽인 경우
        if(x < 0 || x >= n || y < 0 || y >= n || visit[dir][x][y] || bd[x][y] == 1)
            return false;
        if(dir == 0 && (y + 1 >= n || bd[x][y + 1] == 1))
            return false;
        if(dir == 1 && (x + 1 >= n || bd[x + 1][y] == 1))
            return false;

        return true;
    }


    static class Robot {
        int x, y, dir, sec;

        Robot(int x, int y, int dir, int sec) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.sec = sec;
        }
    }
}
