function solution(arr) {
    


    
    
    // 유클리드 호제법
    function common(a, b){
        if(a%b == 0)return b;
        return common(b, a%b);
    }
    
    function g(a,b){
        return a*b/common(a,b);
    }
    

    var answer = arr[0];
    for(let i=1; i<arr.length; i++){
       answer = g(answer, arr[i]);
    }
    

    return answer;
}