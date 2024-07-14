// 모르겠어서 정답 봄.
// 다시 풀어보기.

import java.util.*;

class Solution {
    public static int mod = 1000000007;
    
    public int solution(int n) {
        long[] dp = new long[n + 1];
        long[] dp4 = new long[n + 1];
        long[] dp5 = new long[n + 1];
        long[] dp6 = new long[n + 1];
        dp[0] = 1;
        
        for(int i = 1; i <= n; i++) {
            if(i - 1 >= 0) {
                dp[i] = (dp[i] + dp[i - 1]) % mod;
            }
            if(i - 2 >= 0) {
                dp[i] = (dp[i] + dp[i - 2] * 2) % mod;
            }
            if(i - 3 >= 0) {
                dp[i] = (dp[i] + dp[i - 3] * 5) % mod;
            }
            if(i - 4 >= 0) {
                long diff = (dp[i - 4] * 2) % mod;
                dp4[i] = (dp4[i - 3] + diff) % mod;
                dp[i] = (dp[i] + dp4[i]) % mod;
            }
            if(i - 5 >= 0) {
                long diff = (dp[i - 5] * 2) % mod;
                dp5[i] = (dp5[i - 3] + diff) % mod;
                dp[i] = (dp[i] + dp5[i]) % mod;
            }
            if(i - 6 >= 0) {
                long diff = (dp[i - 6] * 4) % mod;
                dp6[i] = (dp6[i - 3] + diff) % mod;
                dp[i] = (dp[i] + dp6[i]) % mod;
            }
        }
        return Math.toIntExact(dp[n]);
    }
}