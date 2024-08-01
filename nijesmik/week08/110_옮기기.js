function solution(s) {
  return s.map(convert);
}

const convert = (str) => {
  let stack = [];
  let count = 0;
  for (let i = 0; i < str.length; i++) {
    stack.push(str[i]);
    if (stack.at(-1) === '0' && stack.at(-2) === '1' && stack.at(-3) === '1') {
      count++;
      stack.pop();
      stack.pop();
      stack.pop();
    }
  }
  let index = stack.length - 1;
  while (index >= 0 && stack[index] === '1') {
    index--;
  }
  return stack.slice(0, index + 1).join('') + '110'.repeat(count) + stack.slice(index + 1).join('');
};
