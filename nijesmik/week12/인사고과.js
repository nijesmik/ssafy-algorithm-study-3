function solution(scores) {
  const wanho = scores[0];

  scores.sort((a, b) => {
    if (a[0] === b[0]) {
      return a[1] - b[1];
    }
    return b[0] - a[0];
  });

  let answer = 0;
  let max = 0;
  for (let i = 0; i < scores.length; i++) {
    if (isLess(wanho, scores[i])) {
      return -1;
    }
    const [a, b] = scores[i];
    if (sumDiff(wanho, scores[i]) > 0 && b >= max) {
      answer++;
      max = b;
    }
  }
  return answer + 1;
}

const isLess = (a, b) => {
  return a[0] < b[0] && a[1] < b[1];
};

const sumDiff = (a, b) => {
  return b[0] + b[1] - (a[0] + a[1]);
};
