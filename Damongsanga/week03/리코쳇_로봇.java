import java.util.Arrays;

/** 리코쳇 로봇
 * 알고리즘 : DFS
 * 핵심 아이디어 :
 * 1. 해당 위치까지 오는데 방문 배열이 boolean이 아닌 int로 해야함
 * 2. 원복하지 말고 해당 위치까지 오는 경우의 수가 더 작은 경우만 추가로 탐색
 * 3. 방금 왔던 방향으로는 탐색을 아예 안해버리면 시간이 절반 정도로 줌
 * */

class 리코쳇_로봇 {
    static int N;
    static int M;
    static int[] rr = {1,0,-1,0};
    static int[] rc = {0,-1,0,1};
    static int answer = Integer.MAX_VALUE;
    static int[][] visited;
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        int nowR = 0;
        int nowC = 0;
        outer : for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = board[i].charAt(j);
                if (c == 'R') {
                    nowR = i;
                    nowC = j;
                    break outer;
                }
            }
        }

        // visited 배열을 boolean이 아닌 int로 해야 함. 해당 위치까지 오는데 걸리는 횟수를 기록.
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[nowR][nowC] = 0;

        dfs(nowR, nowC, 0, -1, board); // 직전 방향은 -1으로 주면 됨

        return answer == Integer.MAX_VALUE? -1 : answer;
    }

    private static void dfs(int nowR, int nowC, int count, int prev, String[] board){
        if (count >= answer) return;

        if (board[nowR].charAt(nowC) == 'G') {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (prev == (i+2) % 4) continue; // 직전 방향으로 다시 돌아가는 경우는 early exit

            int moveLength = getMoveLength(nowR, nowC, i, board);
            if (moveLength == 0) continue;

            int newR = nowR + rr[i] * moveLength;
            int newC = nowC + rc[i] * moveLength;
            if (visited[newR][newC] <= count) continue; // 해당 위치까지 오는 경로가 더 작은경우만 추가 탐색
            visited[newR][newC] = count;

            dfs(newR, newC, count + 1, i, board);
        }
    }

    private static int getMoveLength(int nowR, int nowC, int direction, String[] board){
        int count = 0;
        while(isMovable(nowR+rr[direction], nowC+rc[direction], board)){
            nowR += rr[direction];
            nowC += rc[direction];
            count++;
        }
        return count;
    }

    private static boolean isMovable(int nowR, int nowC, String[] board){
        return !(nowR < 0 || nowC < 0 || nowR >= N || nowC >= M || board[nowR].charAt(nowC) == 'D');
    }
}