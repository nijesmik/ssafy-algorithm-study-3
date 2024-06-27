function solution(n) {
  const mod = 1_000_000_007;
  const dp = [];

  dp[0] = 1;
  dp[1] = 1;
  dp[2] = dp[1] + dp[0] * 2;
  dp[3] = dp[2] + dp[1] * 2 + dp[0] * 5;
  dp[4] = dp[3] + dp[2] * 2 + dp[1] * 5 + dp[0] * 2;
  dp[5] = dp[4] + dp[3] * 2 + dp[2] * 5 + dp[1] * 2 + dp[0] * 2;
  for (let i = 6; i <= n; i++) {
    dp[i] = (dp[i - 1] + dp[i - 2] * 2 + dp[i - 3] * 6 + dp[i - 4] - dp[i - 6] + mod) % mod;
  }

  return dp[n];
}
