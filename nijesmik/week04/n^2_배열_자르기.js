function solution(n, left, right) {
  const ans = [];

  for (let i = left; i <= right; i++) {
    const col = i % n;
    const row = (i - col) / n;
    ans.push(Math.max(row, col) + 1);
  }

  return ans;
}
