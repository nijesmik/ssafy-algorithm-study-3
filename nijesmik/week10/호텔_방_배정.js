function solution(k, room_number) {
  const taken = {};
  return room_number.map((num, idx) => get(taken, num));
}

const get = (taken, num) => {
  if (!taken[num]) {
    taken[num] = num + 1;
    return num;
  }
  taken[num] = get(taken, taken[num]);
  return taken[num];
};
