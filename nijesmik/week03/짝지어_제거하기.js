function solution(s) {
  const stack = [];

  for (let c of s) {
    if (stack[stack.length - 1] === c) {
      stack.pop();
    } else {
      stack.push(c);
    }
  }

  if (stack.length > 0) {
    return 0;
  }
  return 1;
}
