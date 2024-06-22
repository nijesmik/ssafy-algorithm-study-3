package programmers;


import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_블록_이동하기_실패 {
    static class Node {
        int r1, c1, r2, c2, cnt, dir; // 1 왼 2 오 1 아래 2 위  0 가 1 세

        Node(int r1, int c1, int r2, int c2, int cnt, int dir) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
            this.dir = dir;
        }

    }

    static boolean[][][] visited;
    static int N, ans;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board) {
        Queue<Node> q = new LinkedList<>();
        N = board.length;
        visited = new boolean[N][N][2];
        q.add(new Node(0, 0, 0, 1, 0, 0));
        visited[0][0][0] = visited[0][1][0] = true;
        map = board;
        while (!q.isEmpty()) {
            Node now = q.poll();


            if ((now.r1 == N - 1 && now.c1 == N - 1) || (now.r2 == N - 1 && now.c2 == N - 1)) {
                return now.cnt;
            }
            // 네 방향으로
            for (int i = 0; i < 4; ++i) {
                int nr1 = now.r1 + dr[i];
                int nc1 = now.c1 + dc[i];
                int nr2 = now.r2 + dr[i];
                int nc2 = now.c2 + dc[i];
                Node next = new Node(nr1, nc1, nr2, nc2, now.cnt + 1, now.dir);
                if (!isIn(next)) {
                    continue;
                }
                if (isBlocked(next)) {
                    continue;
                }

                if (isVisited(next)) {
                    continue;
                }
                q.add(next);
                visited[nr1][nc1][now.dir] = visited[nr2][nc2][now.dir] = true;
            }

            // 방향 바꾸기
            // 가로 -> 세로
            if (now.dir == 0) {
                // 왼 -> 아래
                //오른 -> 아래
                if (now.r1 < N - 1) {
                    // boolean flagL = map[now.r1+1][now.c1] == 0 && !visited[now.r1+1][now.c1][1];
                    // boolean flagR = map[now.r2+1][now.c2] == 0 && !visited[now.r2+1][now.c2][1];

                    boolean flagL = map[now.r1 + 1][now.c1] == 0;
                    boolean flagR = map[now.r2 + 1][now.c2] == 0;
                    if (flagL && flagR) {
                        // 1번
                        q.add(new Node(now.r1 + 1, now.c1 + 1, now.r2, now.c2, now.cnt + 1, 1));
                        visited[now.r1 + 1][now.c1 + 1][1] = true;
                        visited[now.r2][now.c2][1] = true;
                        // 2번
                        q.add(new Node(now.r1 + 1, now.c1, now.r1, now.c1, now.cnt + 1, 1));
                        visited[now.r1][now.c1][1] = true;
                        visited[now.r1 + 1][now.c1][1] = true;
                    }
                }
                //왼 -> 위
                //오른 -> 위
                if (now.r1 > 0) {
                    // boolean flagL = map[now.r1 - 1][now.c1] == 0 && !visited[now.r1 - 1][now.c1][1];
                    // boolean flagR = map[now.r1 - 1][now.c2] == 0 && !visited[now.r1 - 1][now.c2][1];

                    boolean flagL = map[now.r1 - 1][now.c1] == 0;
                    boolean flagR = map[now.r1 - 1][now.c2] == 0;

                    if (flagL && flagR) {
                        // 1
                        q.add(new Node(now.r2, now.c2, now.r2 - 1, now.c2, now.cnt + 1, 1));
                        visited[now.r2][now.c2][1] = true;
                        visited[now.r2 - 1][now.c2][1] = true;
                        // 2
                        q.add(new Node(now.r1, now.c1, now.r1 - 1, now.c1, now.cnt + 1, 1));
                        visited[now.r1][now.c1][1] = true;
                        visited[now.r1 - 1][now.c1][1] = true;
                    }

                }
                // 세로 -> 가로
            } else {
                // 아래 -> 오른
                // 위 -> 오른
                if (now.c1 < N - 1) {
                    // boolean flagD = map[now.r1][now.c1+1] == 0 && !visited[now.r1][now.c1+1][0];
                    // boolean flagU = map[now.r2][now.c2+1] == 0 && !visited[now.r2][now.c2+1][0];

                    boolean flagD = map[now.r1][now.c1 + 1] == 0;
                    boolean flagU = map[now.r2][now.c2 + 1] == 0;


                    if (flagD && flagU) {
                        // 1번
                        q.add(new Node(now.r2, now.c2, now.r2, now.c2 + 1, now.cnt + 1, 0));
                        visited[now.r2][now.c2][0] = true;
                        visited[now.r2][now.c2 + 1][0] = true;
                        // 2번
                        q.add(new Node(now.r1, now.c1, now.r1, now.c1 + 1, now.cnt + 1, 0));
                        visited[now.r1][now.c1][0] = true;
                        visited[now.r1][now.c1 + 1][0] = true;
                    }

                }

                // 아래 -> 왼
                // 위 -> 왼
                if (now.c1 > 0) {
                    // boolean flagD = map[now.r1][now.c1 - 1] == 0 && !visited[now.r1][now.c1 - 1][0];
                    // boolean flagU = map[now.r2][now.c2 - 1] == 0 && !visited[now.r2][now.c2 - 1][0];

                    boolean flagD = map[now.r1][now.c1 - 1] == 0;
                    boolean flagU = map[now.r2][now.c2 - 1] == 0;

                    if (flagD && flagU) {
                        // 1번
                        q.add(new Node(now.r2, now.c2 - 1, now.r2, now.c2, now.cnt + 1, 0));
                        visited[now.r2][now.c2 - 1][0] = true;
                        visited[now.r2][now.c2][0] = true;
                        // 2번
                        q.add(new Node(now.r1, now.c1 - 1, now.r1, now.c1, now.cnt + 1, 0));
                        visited[now.r1][now.c1 - 1][0] = true;
                        visited[now.r1][now.c1][0] = true;
                    }
                }

            }

        }
        return 0;
    }

    // 격자 안에 들어왔는지
    static boolean isIn(Node node) {
        return node.r1 < N && node.c1 < N && node.r2 < N && node.c2 < N && node.r1 >= 0 && node.c1 >= 0
                && node.r2 >= 0 && node.c2 >= 0;
    }

    // 막혔는지
    static boolean isBlocked(Node node) {
        return map[node.r1][node.c1] != 0 || map[node.r2][node.c2] != 0;
    }

    // 방문한 적 있는지
    static boolean isVisited(Node node) {
        return visited[node.r1][node.c1][node.dir] || visited[node.r2][node.c2][node.dir];
    }


}
