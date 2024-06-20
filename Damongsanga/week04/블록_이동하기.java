import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/** 블록 이동하기
 * 알고리즘 : BFS (최단거리)
 * 핵심 아이디어 :
 *  1. 일단 회전 구현 => 이게 코드가 너무 더러움....
 *  1.1 처음에는 회전을 모든 방향에 대해 안해도 된다고 생각했는데, 이게 벽 때문에 돌아갈 수 있어서 모든 방향을 다 고려해야 함..
 *  1.2 회전 방향 구현 시 로봇의 위치 표현과 회전시에 부딛히는 벽이 없도록 확인하는 과정을 잘 설계해야 함..
 *  1.3 여기까지는 어찌저찌 했고, 그 이후에 이를 간결하게 표현하기 위해 리팩토링까지 진행
 *
 *  2. 방문 배열을 3차원으로 구성해야 하는가? 가로, 세로, 현재 놓아져 있는 위치
 *      놓아진 위치를 어떻게..? 무조건 작은 것 기준으로 & 가로인지 세로인지만 보면 될듯!
 *
 *  3. 로봇의 위치를 어떻게 표현할 것인가? => 출발 지점에 가까운 좌표를 기준으로 r, c, 로봇의 방향으로 dir를 지정하면 됨
 *  3.1 여기서 dir는 오른쪽 방향, 아래 방향 총 2가지만 가질 수 있음. 출발 지점에서 가까운 좌표를 기준으로 함으로
 *
 * */
class 블록_이동하기 {
    static int[] rr = {0,1,0,-1};
    static int[] rc = {1,0,-1,0};
    static int[][] rotateR = {{-1,-1,0,0}, {0,1,0,1}};
    static int[][] rotateC = {{0,1,0,1}, {-1,-1,0,0}};
    static int R;
    static int C;
    static int[][][] visited;

    // r, c는 로봇이 차지하는 2개의 칸 중 입구에 가까운 칸이며, dir는 0, 1만 가진다
    static class Robot{
        int r; int c; int dir; int count;
        Robot(int r, int c, int dir, int count){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.count = count;
        }

    }

    public int solution(int[][] board) {
        R = board.length;
        C = board[0].length;
        visited = new int[R][C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Robot> queue = new ArrayDeque<>();
        queue.add(new Robot(0,0,0, 0));
        visited[0][0][0] = 0;

        while(!queue.isEmpty()){
            Robot robot = queue.poll();

            int r = robot.r;
            int c = robot.c;
            int dir = robot.dir;

            if ((r + rr[dir] == R-1 && c + rc[dir] == C-1) || (r == R-1 && c == C-1)) return robot.count;

            for (int i = 0; i < 4; i++) {
                move(board, queue, new Robot(r + rr[i], c + rc[i], dir, robot.count));
            }

            for (int i = 0; i < 4; i++) {
                rotate(board, queue, robot, dir, i);
            }
        }

        return -1;
    }

    private static void rotate(int[][] board, Queue<Robot> queue, Robot robot, int dir, int i) {
        int newDir = (dir + 1) % 2;
        int nr = robot.r + rotateR[dir][i];
        int nc = robot.c + rotateC[dir][i];
        int edgeR = robot.r + rotateR[dir][i % 2 == 0 ? i + 1 : i - 1];
        int edgeC = robot.c + rotateC[dir][i % 2 == 0 ? i + 1 : i - 1];
        if (nr < 0  || nr + rr[newDir] >= R || nc < 0 || nc + rc[newDir] >= C) return;
        if (board[nr][nc] == 1 || board[edgeR][edgeC] == 1 || board[nr + rr[newDir]][nc+rc[newDir]] == 1 || board[edgeR + rr[newDir]][edgeC + rc[newDir]] == 1)
            return;
        if (visited[nr][nc][newDir] <= robot.count) return;
        visited[nr][nc][newDir] = robot.count;
        queue.add(new Robot(nr, nc, newDir, robot.count+1));
    }


    private static void move(int[][] board, Queue<Robot> queue, Robot robot) {
        int nr1 = robot.r;
        int nc1 = robot.c;
        int nr2 = robot.r + rr[robot.dir];
        int nc2 = robot.c + rc[robot.dir];
        if (!(nr1 >= 0 && nc1 >= 0 && nr2 < R && nc2 < C)) return;
        if (board[nr1][nc1] == 1 || board[nr2][nc2] == 1) return;
        if (visited[nr1][nc1][robot.dir] <= robot.count) return;
        visited[nr1][nc1][robot.dir] = robot.count;
        queue.add(new Robot(nr1, nc1, robot.dir, robot.count+1));
    }

}