const parking = new Map();
const totalTime = new Map();

const addTime = (num, outTime) => {
  const inTime = parking.get(num);
  let time = outTime - inTime;
  const prev = totalTime.has(num) ? totalTime.get(num) : 0;
  totalTime.set(num, prev + time);
};

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
      if (status === 'IN') {
        parking.set(num, parsedTime);
      } else {
        addTime(num, parsedTime);
        parking.delete(num);
      }
    });
  Array.from(parking.keys()).forEach((num) => addTime(num, 23 * 60 + 59));

  return Array.from(totalTime.entries())
    .sort((a, b) => a[0] - b[0])
    .map(([num, time]) => getFee(fees, time));
}
