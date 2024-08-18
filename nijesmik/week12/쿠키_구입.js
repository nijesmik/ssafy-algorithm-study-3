const sum = [0];

function solution(cookie) {
  cookie.forEach((c, i) => {
    sum[i + 1] = sum[i] + c;
  });
  let answer = 0;
  for (let b = sum.length - 2; b > 0; b--) {
    let a = 0;
    let c = sum.length - 1;
    while (compare(a, b, c) !== 0 && len(a, b) > answer && len(b, c) > answer) {
      if (compare(a, b, c) > 0) {
        c--;
      } else {
        a++;
      }
    }
    if (compare(a, b, c) === 0) {
      answer = Math.max(len(a, b), answer);
    }
  }

  return answer;
}

const compare = (a, b, c) => {
  return len(b, c) - len(a, b);
};

const len = (i, j) => {
  return sum[j] - sum[i];
};
