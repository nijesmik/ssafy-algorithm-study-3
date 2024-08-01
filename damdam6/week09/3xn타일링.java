import java.util.*;

class Solution {
    public int solution(int n) {
        if(n%2 != 0)return 0;
        long MOD = 1_000_000_007;

        long[] dp = new  long[n+1];
        long[] even = new long[n+1];
        
        dp[0] = 1;
        even[2] = 2;
        
        for(int i=2; i <= n+1; i =i+2){

            
            // 2개 짜리는 3을 곱한다.
            dp[i] =dp[i] + dp[i-2];

            // 4개 이후부터는 2씩 뺀 값을 누적하여 합하고 *2한다
    
                even[i] = (even[i-2] + dp[i-2]*2)%MOD;
                dp[i] = (dp[i] + even[i])%MOD;
            
            
        }
        
        //System.out.println(Arrays.toString(even));
        //System.out.println(Arrays.toString(dp));
        
        return  Math.toIntExact(dp[n]%MOD);
    }
}