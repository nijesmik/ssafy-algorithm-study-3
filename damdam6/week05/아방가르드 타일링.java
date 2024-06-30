import java.util.*;

class Solution {
    long MOD = 1_000_000_007;
    
    public int solution(int n) {
        int answer = 0;
        
        long[] cnt = new long[Math.max(n + 1, 7)];
        
        
        cnt[1] = getRemainerFromInt(1);
        cnt[2] = getRemainerFromInt(2);
        cnt[3] = getRemainerFromInt(5);
        cnt[4] = getRemainerFromInt(2);
        cnt[5] = getRemainerFromInt(2);
        cnt[6] = getRemainerFromInt(4);
        
        for(int i=1;i<=n;i++){
            if(i+1 <=n){
                 cnt[i+1] = getRemainer(cnt[i+1]+cnt[i]);
            }
           
            if(i+2 <= n){
            cnt[i+2] = getRemainer(cnt[i+2]+cnt[i] * 2);
            }
            
            if(i+3 <= n){
            cnt[i+3] = getRemainer(cnt[i+3] + cnt[i] * 5);
            }
            
            int k = 4;
            
            while(i+k <= n){
                
                
                cnt[i+k] = getRemainer(cnt[i+k] + cnt[i]*2);
                
                if(i+k+1 <= n){

                    cnt[i+k+1] = getRemainer(cnt[i+k+1] + cnt[i]*2);
                }
                if(i+k+2 <= n){
                    cnt[i+k+2] = getRemainer(cnt[i+k+2] + cnt[i] * 4);
                }
                
                k+= 3;
            }
            
            System.out.println(Arrays.toString(cnt));
        }
        
        answer = (int) cnt[n];
        
        return answer;
    }
    
    long getRemainerFromInt(int num){
        return num%MOD;
    }
    
    long getRemainer(long num){
        return num%MOD;
    }
    
    
}