function solution(n) {
  let usage = 0;

  while (n > 1) {
    if (n % 2 == 1) {
      usage++;
      n--;
    }
    n /= 2;
  }

  return usage + 1;
}
