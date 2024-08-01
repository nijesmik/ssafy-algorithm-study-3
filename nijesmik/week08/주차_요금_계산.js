const totalTime = {};

const getFee = (fees, time) => {
  const [baseTime, baseRate, unitTime, unitRate] = fees;
  if (time <= baseTime) {
    return baseRate;
  }

  time -= baseTime;
  return Math.ceil(time / unitTime) * unitRate + baseRate;
};

function solution(fees, records) {
  records
    .map((r) => r.split(' '))
    .forEach(([time, num, status]) => {
      const [hour, min] = time.split(':');
      const parsedTime = Number(hour) * 60 + Number(min);
      if (!totalTime[num]) {
        totalTime[num] = 0;
      }
      if (status === 'IN') {
        totalTime[num] += 23 * 60 + 59 - parsedTime;
      } else {
        totalTime[num] -= 23 * 60 + 59 - parsedTime;
      }
    });

  return Object.entries(totalTime)
    .sort((a, b) => a[0] - b[0])
    .map(([num, time]) => getFee(fees, time));
}
