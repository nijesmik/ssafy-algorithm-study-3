function solution(people, limit) {
  const sorted = people.sort((a, b) => a - b);
  let left = 0;
  let right = sorted.length - 1;
  let answer = 0;
  while (left <= right) {
    if (sorted[left] + sorted[right] <= limit) {
      left++;
    }
    right--;
    answer++;
  }
  return answer;
}
