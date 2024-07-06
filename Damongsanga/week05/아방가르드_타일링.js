/** 아방가르드 타일링
 * 핵심 아이디어 : DP
 * 문제
 * index :  1   2   3   4   5   6   7   8   9
 * pure  :  1   2   5   2   2   4   2   2   4
 * dp    :  1   3   10  21  58  156 ... ... ...
 * cache :  0   0   0   0   2   8   30  ... ...
 *
 * 여기서 index가 3의 배수일때 pure가 4, 나머지는 2
 * dp[i] = pure[i] + (dp[i-1] * pure[1] + dp[i-2] * pure[2] + dp[i-3] * pure[3]) + dp[i-4] * pure[4] + dp[i-5] * pure[5] + dp[i-6] * pure[6] + ...
 *       = pure[i] + (dp[i-1] + 2 * dp[i-2] + 5 * dp[i-3]) + (3의배수마다 반복 => cache)
 * 따라서 3배수마다 반복되는 값을 cache로 저장하면 된다.
 * cache를 길이가 3인 배열로 선언하여 현재 index를 3으로 나눈 나머지 번쨰의 값을 dp를 구할 때 더해준다.
 * 어자피 pure는 2, 2, 4가 계속 반복됨으로 다음에 다시 사용될 때를 위해 값을 추가해준다.
 * 말로 설명하기는 어렵고.. 위에 공식대로 직접 전부다 써보면 반복되는 부분이 보인다.
 *
 * 아래는 dp 배열 메모리를 최대한 줄이기 위해 변수 3개만 사용함 (prev3, prev2, prev1)
 */

function solution(n) {
  const MOD = 1_000_000_007;
  let init = [1, 3, 10];
  if (n <= 3) return init[n - 1];

  let pure = [1, 2, 5];
  let pureRepeat = [4, 2, 2]; // dp6부터 3의배수마다 4, 나머지는 2
  let cache = [8, 0, 2]; // dp4에서 0, dp5에서 2, dp6에서 8임

  for (let i = 4; i <= n; i++) {
    let r = i % 3;
    let [prev3, prev2, prev1] = init;
    let now =
      ((prev3 * pure[2]) % MOD) +
      ((prev2 * pure[1]) % MOD) +
      ((prev1 * pure[0]) % MOD) +
      pureRepeat[r];

    now += cache[r];
    cache[r] +=
      prev3 * pureRepeat[0] + prev2 * pureRepeat[1] + prev1 * pureRepeat[2];
    cache[r] %= MOD;
    init = [prev2, prev1, now % MOD];
  }

  return init[2];
}
