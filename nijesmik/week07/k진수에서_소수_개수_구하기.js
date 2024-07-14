function solution(n, k) {
  return n.toString(k).split(/0+/).map(Number).filter(isPrime).length;
}

const isPrime = (num) => {
  if (num === 0 || num === 1) {
    return false;
  }

  for (let i = 2; i <= Math.sqrt(num); i++) {
    if (num % i === 0) {
      return false;
    }
  }
  return true;
};
