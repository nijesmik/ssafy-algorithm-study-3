
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    static int N;
    static int[][] bd;
    static int[][] vt;

    static ArrayList<Integer> countHouse;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        bd = new int[N][N];
        vt = new int[N][N];
        countHouse = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < N; j++) {
                bd[i][j] = line.charAt(j) - '0';
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (bd[i][j] == 0) continue;
                if (vt[i][j] == 1) continue;
                doBfs(new pos(i, j));
                count++;
            }
        }
        Collections.sort(countHouse);
        System.out.println(count);
        for (int cnt : countHouse) {
            System.out.println(cnt);
        }


    }


    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{1, -1, 0, 0};

    static void doBfs(pos p) {


        ArrayDeque<pos> qu = new ArrayDeque<>();
        qu.add(p);
        vt[p.x][p.y] = 1;
        int cntH = 1;

        while (!qu.isEmpty()) {

            pos tmp = qu.poll();


            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (vt[nx][ny] == 1) continue;
                if (bd[nx][ny] != 1) continue;

                vt[nx][ny] = 1;
                qu.add(new pos(nx, ny));
                cntH++;
            }


        }

        countHouse.add(cntH);
    }


    static class pos {
        int x;
        int y;

        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
