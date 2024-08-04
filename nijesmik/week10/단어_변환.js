function solution(begin, target, words) {
  if (!words.includes(target)) {
    return 0;
  }

  let answer = 51;
  const visited = Array(words.length).fill(false);

  const dfs = (begin, depth) => {
    if (depth > answer) {
      return;
    }
    if (begin === target) {
      answer = depth;
    }
    words.forEach((word, i) => {
      if (!visited[i] && diff(begin, word) === 1) {
        visited[i] = true;
        dfs(word, depth + 1);
        visited[i] = false;
      }
    });
  };

  dfs(begin, 0);
  return answer;
}

const diff = (str1, str2) => {
  return [...str1].filter((char, i) => char != str2[i]).length;
};
