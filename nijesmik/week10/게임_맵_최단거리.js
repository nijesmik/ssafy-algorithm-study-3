function solution(maps) {
  const N = maps.length;
  const M = maps[0].length;
  const delta = [
    [0, 1],
    [0, -1],
    [1, 0],
    [-1, 0],
  ];
  const q = [];

  q.push([0, 0, 1]);
  maps[0][0] = 0;

  while (q.length > 0) {
    const [r, c, count] = q.shift();
    if (r === N - 1 && c === M - 1) {
      return count;
    }
    delta.forEach(([dr, dc]) => {
      const [nr, nc] = [r + dr, c + dc];
      if (valid(nr, N) && valid(nc, M) && maps[nr][nc]) {
        q.push([nr, nc, count + 1]);
        maps[nr][nc] = 0;
      }
    });
  }
  return -1;
}

const valid = (idx, len) => {
  if (idx < 0 || idx >= len) {
    return false;
  }
  return true;
};
