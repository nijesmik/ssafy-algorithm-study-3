function solution(k, room_number) {
  const taken = new Map();
  return room_number.map((num) => get(taken, num));
}

const get = (taken, num) => {
  if (!taken.has(num)) {
    taken.set(num, num + 1);
    return num;
  }
  const val = get(taken, taken.get(num));
  taken.set(num, val);
  return val;
};
