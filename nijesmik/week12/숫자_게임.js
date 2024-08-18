function solution(A, B) {
  A.sort(comparator);
  B.sort(comparator);

  let index = 0;
  A.forEach((a) => {
    if (a < B[index]) {
      index++;
    }
  });

  return index;
}

const comparator = (a, b) => b - a;
