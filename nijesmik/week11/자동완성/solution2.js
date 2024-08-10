function solution(words) {
  words.sort();
  return words.reduce((answer, word, index) => {
    const left = words[index - 1];
    const right = words[index + 1];

    for (let i = 0; i < word.length; i++) {
      if (!isEqual(left, word, i) && !isEqual(right, word, i)) {
        return answer + i + 1;
      }
    }
    return answer + word.length;
  }, 0);
}

const isEqual = (word1, word2, index) => {
  if (index === -1) {
    return true;
  }
  if (!word1 || !word2 || word1[index] !== word2[index]) {
    return false;
  }
  return isEqual(word1, word2, index - 1);
};
