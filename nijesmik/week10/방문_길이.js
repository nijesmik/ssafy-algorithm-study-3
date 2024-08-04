function solution(dirs) {
  const cur = { r: 0, c: 0 };
  let answer = 0;
  const map = new Map();
  for (let i = 0; i < dirs.length; i++) {
    const [r, c] = getDir(dirs[i]);
    if (!validate(cur.r + r) || !validate(cur.c + c)) {
      continue;
    }
    cur.r += r;
    cur.c += c;
    const key = getKey(cur, dirs[i]);
    if (!map.has(key)) {
      answer++;
      map.set(key, true);
    }
  }
  return answer;
}

const getKey = ({ r, c }, dir) => {
  if (dir === 'D' || dir === 'L') {
    dir = dir === 'L' ? 'R' : 'U';
    const [dr, dc] = getDir(dir);
    r += dr;
    c += dc;
  }
  return `${r}${c}${dir}`;
};

const getDir = (dir) => {
  switch (dir) {
    case 'U':
      return [0, 1];
    case 'D':
      return [0, -1];
    case 'R':
      return [1, 0];
    case 'L':
      return [-1, 0];
  }
};

const validate = (k) => {
  return -5 <= k && k <= 5;
};
