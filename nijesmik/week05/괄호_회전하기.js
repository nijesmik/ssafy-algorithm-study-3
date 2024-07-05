function solution(s) {
  let answer = 0;

  for (let i = 0; i < s.length; i++) {
    const stack = [];

    for (let j = 0; j < s.length; j++) {
      const top = stack.at(-1);

      if (s[j] === ']' && top === '[') {
        stack.pop();
      } else if (s[j] === ')' && top === '(') {
        stack.pop();
      } else if (s[j] === '}' && top === '{') {
        stack.pop();
      } else {
        stack.push(s[j]);
      }
    }

    if (stack.length === 0) {
      answer++;
    }

    s = s.slice(1) + s[0];
  }

  return answer;
}
