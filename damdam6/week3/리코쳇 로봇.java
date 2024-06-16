import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = 0;
        
        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};
        
        pos start = null;
        pos goal = null;
        
        int xLength = board.length;
        int yLength = board[0].length();
        
        int[][] vt = new int[xLength][yLength];
        
       for(int i = 0; i<xLength;i++){
            for(int j=0; j<yLength; j++){
                if(board[i].charAt(j) == 'R'){
                    start = new pos(i,j);
                }else if(board[i].charAt(j) == 'G'){
                    goal = new pos(i,j);
                }
            }
        }
        
        ArrayDeque<pos> qu = new ArrayDeque<>();
        qu.add(start);
        vt[start.x][start.y] = 1;
        
        while(!qu.isEmpty()){
            pos tmp = qu.poll();
            
            if((tmp.x == goal.x ) && (tmp.y == goal.y)){
                answer = tmp.count;
                break;
            }
            
            for(int i=0; i<4;i++){
                int nx = tmp.x;
                int ny = tmp.y;
                
                while(true){
                     nx += dx[i];
                     ny += dy[i];
                    
                
                    if(nx <0 || ny <0 || nx >= xLength || ny >= yLength
                      || (board[nx].charAt(ny) == 'D')){
                        nx = nx - dx[i];
                        ny = ny - dy[i];
                        break;
                    };

                }
                
                if(vt[nx][ny] == 1)continue;
                
                vt[nx][ny] = 1;

                
                pos nxPos = new pos(nx, ny, tmp.count);
                nxPos.upCnt();
                qu.add(nxPos);
            }
            
        }
        
        if(answer == 0){
            answer = -1;
        }
        
        return answer;
    }
    

    class pos {
        int x;
        int y;
        int count;
        
        pos(int x, int y){
            this.x = x;
            this.y = y;
            this.count = 0;
        }
        
        pos(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.count = cnt;
        }
        
        void upCnt(){
            this.count++;
        }
        
    }
}