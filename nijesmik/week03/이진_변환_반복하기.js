function solution(s) {
  let count = 0;
  let zero = 0;

  while (s !== '1') {
    let one = 0;
    for (let i = 0; i < s.length; i++) {
      if (s[i] === '1') {
        one++;
      } else {
        zero++;
      }
    }
    s = one.toString(2);
    count++;
  }

  return [count, zero];
}
