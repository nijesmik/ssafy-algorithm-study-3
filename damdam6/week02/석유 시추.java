import java.util.*;

class Solution {
    public int solution(int[][] land) {
        
        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};
        
        int n = land.length;
        int m = land[0].length;
        
        int[][] vt = new int[n][m];
        int[][] oilSet = new int[n][m];
        
        // 0 이면 빈 땅 1이면 석유
        // 몇 덩어리의 석유가 있는지는 모름
        HashMap<Integer,Integer> oilGroup = new HashMap<>();
        
        ArrayDeque<pos> qu = new ArrayDeque<>();
        int oilNumCnt = 1;
        for(int i=0; i<n;i++){
            for(int j= 0; j<m;j++){
                if(land[i][j] == 1 && vt[i][j] != 1){
                    
                    qu.add(new pos(i,j,oilNumCnt));
                    vt[i][j] = 1;
                    
                    int cntSize = 1;
                    
                    
                    while(!qu.isEmpty()){
                        pos tmp = qu.poll();
                        
                        oilSet[tmp.x][tmp.y] = oilNumCnt;
                        
                        for(int k=0;k<4;k++){
                            int nx = tmp.x + dx[k];
                            int ny = tmp.y + dy[k];
                            
                            if(nx < 0 || ny < 0 || nx >= n || ny >= m)continue;
                            if(land[nx][ny] == 0)continue;
                            if(vt[nx][ny] == 1)continue;
                            
                            vt[nx][ny] = 1;
                            cntSize++;
                            
                            qu.add(new pos(nx,ny));
                        }
                        
                        
                        
                    }
                    oilGroup.put(oilNumCnt, cntSize);
                    oilNumCnt++;
                }
            }
        }
        
        
        int answer = -1;
        
        for(int j=0; j<m;j++){

            HashSet<Integer> oils = new HashSet<>();
            // 매 세로 줄에서 x 줄 체크
            for(int i=0;i<n;i++){
                if(oilSet[i][j] != 0){
                    oils.add(oilSet[i][j]);
                }
            }
            
            int sum = 0;
            for(int  group:oils){
                sum+= oilGroup.get(group);
            }
            
            answer = Math.max(answer, sum);
            
        }
        
        
        return answer;
    }
    
    class pos{
        int x;
        int y;
        int oilNum;
        
    pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        
        pos(int x, int y, int oilNum){
            this.x = x;
            this.y = y;
            this.oilNum = oilNum;
        }
    }
}