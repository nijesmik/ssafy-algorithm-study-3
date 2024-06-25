// 도저히 못 풀겠어서 블로그 보고 따라서 풂.
// 나중에 다시 풀어볼 것
// https://howudong.tistory.com/436

import java.util.*;

class Solution {
    
    private static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private static final int MAX = 999999;
    
    public int[][] map;
    private boolean redEnd, blueEnd;
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    private boolean[][][] visited;
    
    public int solution(int[][] maze) {
        Point cntRed = null;
        Point cntBlue = null;
        
        map = new int[maze.length][maze[0].length];
        visited = new boolean[maze.length][maze[0].length][2];
        
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                map[i][j] = maze[i][j];
                // 각 수레 시작위치 초기화
                if(maze[i][j] == 1) {
                    cntRed = new Point(i, j);
                } else if(maze[i][j] == 2) {
                    cntBlue = new Point(i, j);
                }
            }
        }
        // 시작 위치 방문 처리. 0 = red, 1 = blue;
        visited[cntRed.x][cntRed.y][0] = true;
        visited[cntBlue.x][cntBlue.y][1] = true;
        int answer = backtracking(cntRed, cntBlue, 0);
        return (answer == MAX) ? 0 : answer;
    }
    
    private Point getNext(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        return new Point(nx, ny);
    }
    
    //해당 방향으로 움직임 가능한지 판단
    private boolean isPossible(Point cntRed, Point red, Point cntBlue, Point blue) {
        if(red.x < 0 || red.y < 0 || red.x >= map.length 
           || red.y >= map[0].length || blue.x < 0 
           || blue.y < 0 || blue.x >= map.length 
           || blue.y >= map[0].length || map[red.x][red.y] == 5 
           || map[blue.x][blue.y] == 5) {
            return false;
        }
        // 수레 스위치 체크
        if((cntRed.x == blue.x && cntRed.y == blue.y) 
           && (cntBlue.x == red.x && cntBlue.y == red.y)) {
            return false;
        }
        // 도착 전 중복 방문 false 처리
        if((!redEnd && visited[red.x][red.y][0]) 
           || (!blueEnd && visited[blue.x][blue.y][1])) {
            return false;
        }
        // 두 수레 동일 지점 위치 시
        if(red.x == blue.x && red.y == blue.y) {
            return false;
        }
        return true;
    }
    
    // 백트래킹
    private int backtracking(Point red, Point blue, int result) {
        if(redEnd && blueEnd) {
            return result;
        }
        int answer = MAX;
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                Point nRed = (!redEnd) ? getNext(red.x, red.y, i) : red;
                Point nBlue = (!blueEnd) ? getNext(blue.x, blue.y, j) : blue;
                
                // 불가능한 경우
                if(!isPossible(red, nRed, blue, nBlue)) {
                    continue;
                }
                
                visited[nRed.x][nRed.y][0] = true;
                visited[nBlue.x][nBlue.y][1] = true;
                
                if(map[nRed.x][nRed.y] == 3) {
                    redEnd = true;
                }
                if(map[nBlue.x][nBlue.y] == 4) {
                    blueEnd = true;
                }
                
                answer = Math.min(answer, backtracking(nRed, nBlue, result + 1));
                
                redEnd = false;
                blueEnd = false;
                visited[nRed.x][nRed.y][0] = false;
                visited[nBlue.x][nBlue.y][1] = false;
            }
        }
        return answer;
    }
}