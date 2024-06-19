package nijesmik.week03.리코쳇_로봇;
import java.util.*;

class Solution {
    char[][] board;
    int R, C, startR, startC, endR, endC;

    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();
        this.board = new char[R + 2][C + 2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char item = board[i].charAt(j);
                if (item == 'R') {
                    startR = i + 1;
                    startC = j + 1;
                } else if (item == 'G') {
                    endR = i + 1;
                    endC = j + 1;
                } else if (item == 'D') {
                    item = 0;
                }
                this.board[i + 1][j + 1] = item;
            }
        }

        int answer = bfs();
        return answer;
    }

    static final int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
    boolean[][] visited;
    Queue<Record> q;

    int bfs() {
        visited = new boolean[R + 2][C + 2];
        q = new LinkedList<>();

        q.add(new Record(startR, startC));
        visited[startR][startC] = true;

        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Record cur = q.poll();
                if (cur.r == endR && cur.c == endC) {
                    return count;
                }
                for (int i = 0; i < dr.length; i++) {
                    move(cur.r, cur.c, dr[i], dc[i]);
                }
            }
            count++;
        }

        return -1;
    }

    void move(int r, int c, int dr, int dc) {
        int nr = r, nc = c;
        while (board[nr + dr][nc + dc] != 0) {
            nr += dr;
            nc += dc;
        }
        if (nr == r && nc == c) {
            return;
        }
        if (visited[nr][nc]) {
            return;
        }
        q.add(new Record(nr, nc));
        visited[nr][nc] = true;
    }

    class Record {
        int r, c;

        Record(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
