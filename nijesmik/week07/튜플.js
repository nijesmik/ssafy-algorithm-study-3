function solution(s) {
  return s
    .slice(2, -2)
    .split('},{')
    .sort(compare)
    .reduce((answer, subset) => {
      const set = new Set(subset.split(','));
      answer.forEach((e) => set.delete(e));
      return [...answer, ...set];
    }, [])
    .map(Number);
}

const compare = (a, b) => a.length - b.length;
