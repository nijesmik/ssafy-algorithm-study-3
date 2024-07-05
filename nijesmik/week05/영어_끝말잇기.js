function solution(n, words) {
  const set = new Set();

  for (let i = 1; i < words.length; i++) {
    set.add(words[i - 1]);

    if (
      words[i - 1].slice(-1) !== words[i][0] || // 앞사람이 말한 단어의 마지막 문자로 시작하지 않는 단어
      set.has(words[i]) || // 이전에 등장했던 단어
      words[i].length === 1 // 한 글자인 단어
    ) {
      return [(i % n) + 1, Math.floor(i / n) + 1];
    }
  }

  return [0, 0];
}
