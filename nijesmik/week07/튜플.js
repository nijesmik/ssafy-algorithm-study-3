function solution(s) {
  const subsets = s.substring(2, s.length - 2).split('},{');
  const answer = [];
  subsets.sort(compare).forEach((subset) => {
    const set = new Set(subset.split(','));
    answer.forEach((e) => set.delete(e));
    answer.push(...set);
  });
  return answer.map(Number);
}

const compare = (a, b) => {
  if (a.length < b.length) {
    return -1;
  }
  if (a.length > b.length) {
    return 1;
  }
  return 0;
};
