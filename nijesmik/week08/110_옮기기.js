function solution(s) {
  return s.map(convert);
}

const convert = (str) => {
  const match = str.match(/110/);
  if (!match) {
    return str;
  }

  const { index } = match;
  const deleted = str.slice(0, index) + str.slice(index + 3);

  let answer = str;
  for (let i = 0; i < deleted.length; i++) {
    const prev = deleted.slice(0, i) + '110';
    const next = convert(deleted.slice(i));
    const inserted = prev + next;
    if (inserted < answer) {
      answer = inserted;
    }
  }
  return answer;
};
