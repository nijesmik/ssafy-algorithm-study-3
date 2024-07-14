function solution(n, t, m, p) {
  let answer = '';
  let num = 0;
  let str = '';
  while (answer.length < t) {
    str += num.toString(n);
    if (str.length >= m) {
      answer += str[p - 1].toUpperCase();
      str = str.slice(m);
    }
    num++;
  }
  return answer;
}
