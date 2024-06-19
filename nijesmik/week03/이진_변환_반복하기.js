function solution(s) {
  let count = 0;
  let zero = 0;

  while (s !== '1') {
    const length = s.length;
    s = s.replaceAll('0', '');
    const one = s.length;
    zero += length - one;
    s = one.toString(2);
    count++;
  }

  return [count, zero];
}
