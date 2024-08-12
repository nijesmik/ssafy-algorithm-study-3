function solution(str1, str2) {
  const set1 = {};
  const set2 = {};
  addAll(set1, str1.toLowerCase());
  addAll(set2, str2.toLowerCase());

  const union =
    Object.keys(set1).reduce((union, key) => {
      const num = set2[key] ? set2[key] : 0;
      return union + Math.max(set1[key], num);
    }, 0) +
    Object.keys(set2).reduce((union, key) => {
      if (set1[key]) {
        return union;
      }
      return union + set2[key];
    }, 0);

  const intersect = Object.keys(set1).reduce((intersect, key) => {
    const num = set2[key] ? set2[key] : 0;
    return intersect + Math.min(set1[key], num);
  }, 0);

  const answer = intersect / union;
  if (Number.isNaN(answer)) {
    return 65536;
  }
  return Math.floor(answer * 65536);
}

const addAll = (set, str) => {
  for (let i = 1; i < str.length; i++) {
    add(set, str, i);
  }
};

const add = (set, str, i) => {
  if (!isAlpha(str[i]) || !isAlpha(str[i - 1])) {
    return;
  }
  const word = str[i - 1] + str[i];
  if (!set[word]) {
    set[word] = 0;
  }
  set[word]++;
};

const isAlpha = (str) => {
  return /[A-Za-z]+/.test(str);
};
