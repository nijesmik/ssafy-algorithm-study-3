function solution(s) {
  const sorted = s
    .split(' ')
    .map(Number)
    .sort((a, b) => a - b);
  return `${sorted[0]} ${sorted[sorted.length - 1]}`;
}

/*

숫자 배열을 정렬할 때 비교 함수 없이 사용하면 숫자가 아닌 문자열로 취급되어 다음과 같이 정렬될 수 있다.
(기본적으로 sort() 메서드는 문자열로 변환된 값에 대해 정렬을 수행한다!)

const numbers = [10, 5, 1, 20];
numbers.sort();
console.log(numbers); // [1, 10, 20, 5]

따라서 숫자 배열을 숫자 값 자체를 기준으로 정렬하려면 반드시 비교 함수(comparator)를 제공해야 한다.

*/
