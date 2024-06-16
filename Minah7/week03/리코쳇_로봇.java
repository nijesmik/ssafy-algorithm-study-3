import java.util.*;

class Point{
    int x;
    int y;
    Point(int a, int b) {
        x = a;
        y = b;
    }
}
class Solution {
    int n, m;
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        int[][] visit = new int[n][m];
        Queue<Point> que = new ArrayDeque();
        
        for(int row = 0; row < n; row++) {
            String s = board[row];
            for(int col = 0; col < m; col++) {
                if(s.charAt(col) == 'R') {
                    que.add(new Point(row, col));
                    visit[row][col] = 1;
                    break;
                }
            }
        }
        
        int answer = -1;
        while(!que.isEmpty()) {
            Point current = que.poll();
            if(board[current.x].charAt(current.y) == 'G') {
                answer = visit[current.x][current.y] - 1;
                break;
            }
            
            for(int d = 0; d < 4; d++) {
                int dx = current.x + dir[d][0];
                int dy = current.y + dir[d][1];
                while(true) {
                    if(isInRange(dx, dy) && board[dx].charAt(dy) != 'D') {
                        dx += dir[d][0];
                        dy += dir[d][1];
                    } else {
                        dx -= dir[d][0];
                        dy -= dir[d][1];
                        break;
                    }
                }
                
                if(visit[dx][dy] == 0) {
                    que.add(new Point(dx, dy));
                    visit[dx][dy] = visit[current.x][current.y] + 1;
                }
            }
        }
        
        return answer;
    }
    
    public boolean isInRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < m) {
            return true;
        }
        return false;
    }
}