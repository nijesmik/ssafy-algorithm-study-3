function solution(n) {
    let bina = n.toString(2);
    let cntOne = bina.split("1").length;
    
    let cnt = -1;
    let num = n;
    while(cnt !== cntOne ){
        cnt = (++num).toString(2).split("1").length;
    }
    
    var answer = num;
    return answer;
}