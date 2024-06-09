// 정확성 테스트 통과, but 효율성 테스트 실패

import java.util.*;

class Solution {
    static int answer = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int solution(int[][] land) {
        int row = land.length; 
        int col = land[0].length;
        
        for(int c = 0; c < col; c++) {
            boolean[][] visited = new boolean[row][col];
            bfs(land, visited, row, col, c, 0);
        }
        
        return answer;
    }
    public void bfs(int[][] land, boolean[][] visited, int row, int col, int c, int sum) {
        for(int r = 0; r < row; r++) {
            if(land[r][c] == 1 && visited[r][c] == false) {
                int nr = r;
                int nc = c;
                Queue<int[]> que = new LinkedList<>();
                que.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                int cnt = 1;
                
                while(!que.isEmpty()) {
                    int[] cur = que.poll();
                    
                    for(int d = 0; d < 4; d++) {
                        int tmpR = cur[0] + dr[d];
                        int tmpC = cur[1] + dc[d];
                        if(tmpR < 0 || tmpR >= row || tmpC < 0 || tmpC >= col || land[tmpR][tmpC] == 0 || visited[tmpR][tmpC]) {
                            continue;
                        }
                        que.add(new int[] {tmpR, tmpC});
                        visited[tmpR][tmpC] = true;
                        cnt++;
                    }
                }
                sum += cnt;
            }
        }
        answer = Math.max(answer, sum);
    }
}