function solution(n, a, b) {
  let answer = 0;
  while (a != b) {
    a = ++a >> 1;
    b = ++b >> 1;
    answer++;
  }
  return answer;
}
