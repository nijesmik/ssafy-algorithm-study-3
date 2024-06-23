function solution(n, left, right) {
  const startC = left % n;
  const startR = (left - startC) / n;

  const endC = right % n;
  const endR = (right - endC) / n;

  const ans = [];

  if (startR == endR) {
    for (let j = startC; j <= endC; j++) {
      ans.push(Math.max(startR, j) + 1);
    }
    return ans;
  }

  for (let j = startC; j < n; j++) {
    ans.push(Math.max(startR, j) + 1);
  }

  for (let i = startR + 1; i < endR; i++) {
    for (let j = 0; j < n; j++) {
      ans.push(Math.max(i, j) + 1);
    }
  }

  for (let j = 0; j <= endC; j++) {
    ans.push(Math.max(endR, j) + 1);
  }

  return ans;
}
