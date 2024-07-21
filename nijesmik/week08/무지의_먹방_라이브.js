function solution(food_times, k) {
  const total = food_times.reduce((total, time) => total + time, 0);
  if (total <= k) {
    return -1;
  }

  const food = food_times.map((time, i) => ({ time, index: i + 1 }));
  food.push({ time: 0, index: 0 });
  food.sort((a, b) => a.time - b.time);

  let eaten = 1;
  while (eaten < food.length) {
    const time = food[eaten].time - food[eaten - 1].time;
    const toEat = food.length - eaten;
    const totalTime = time * toEat;
    if (totalTime > k) {
      break;
    }
    eaten++;
    k -= totalTime;
  }
  const remain = food.slice(eaten).sort((a, b) => a.index - b.index);
  return remain[k % remain.length].index;
}
