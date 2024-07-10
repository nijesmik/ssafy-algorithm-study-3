import java.util.*;

class Solution {
    public int solution(int n) {
        
        int MOD = 1_000_000_007;
        
        int[] dp = new int[n+1];
        
        dp[1] = 1;
        if(n == 1)return dp[1];
        dp[2] = 3;
        if(n == 2)return dp[2];
        dp[3] = 10;
        if(n == 3)return dp[3];
        dp[4] = 23;
        if(n==4)return dp[4];
        dp[5] = 62;
        if(n==5)return dp[5];
        dp[6] = 170;
        
        for(int i=7;i<=n;i++){

            
            dp[i] =(dp[i-1]%MOD + dp[i-2]*2%MOD + dp[i-3]*5%MOD)%MOD;
            
            // System.out.println(dp[i]);
            
            int k = i - 4;
            while (k > 0) {
                dp[i] = (dp[i] + dp[k] * 2 % MOD) % MOD;
                k -= 3;
            }

            k = i - 5;
            while (k > 0) {
                dp[i] = (dp[i] + dp[k] * 2 % MOD) % MOD;
                k -= 3;
            }

            k = i - 6;
            while (k > 0) {
                dp[i] = (dp[i] + dp[k] * 4 % MOD) % MOD;
                k -= 3;
            }
            
         System.out.println(Arrays.toString(dp));
        }

        int answer = dp[n];
        return answer;
    }
}