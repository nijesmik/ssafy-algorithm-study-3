function solution(s) {
    var numbers = s.split(" ");
    var numType = [];
    
    numbers.forEach((n) => {
        numType.push(parseInt(n));
        //출력은 콘솔?
        console.log(n);
    })
    
    // sort는 문자열 기반 정렬 -> 숫자 비교 시 뒤에 함수 써줘야함.
    numType.sort((a, b) => a - b);
    
    var answer = numType[0]+' '+numType[numType.length - 1];
    return answer;
}