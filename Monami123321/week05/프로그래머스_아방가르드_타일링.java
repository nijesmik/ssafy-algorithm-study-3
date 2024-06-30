package programmers;

public class 프로그래머스_아방가르드_타일링 {
    static final int MOD = 1_000_000_007;
    public int solution(int n) {
        long[] arr = new long[n + 6];
        long[] sum = new long[n + 6];
        sum[0] = sum[1] = arr[0] = arr[1] = 1;
        sum[2] = arr[2] = 3;
        sum[3] = arr[3] = 10;
        sum[3]++;
        sum[4] = arr[4] = 23;
        sum[4]++;
        arr[5] = 62;
        sum[5] = 65;
        arr[6] = 170;
        sum[6] = 181;
        for(int i = 7; i <= n; ++i) {
            arr[i] = arr[i - 1] % MOD;
            arr[i] += (arr[i - 2] << 1) % MOD;
            arr[i] += (arr[i - 3] * 5) % MOD;
            arr[i] += (sum[i - 4] << 1) % MOD;
            arr[i] += (sum[i - 5] << 1) % MOD;
            arr[i] += (sum[i - 6] << 2) % MOD;
            arr[i] %= MOD;

            sum[i] = (arr[i] + sum[i - 3]) % MOD;
        }
        return (int) arr[n];
    }
}
