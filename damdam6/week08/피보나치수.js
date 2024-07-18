function solution(n) {
    
    const M = 1234567;

    
    let fiboMap = new Map();
    fiboMap.set(0,0);
    fiboMap.set(1,1);
    
    for(let i=2;i<=n;i++){
        fiboMap.set(i, (fiboMap.get(i-1)+fiboMap.get(i-2))%M);
    }
    
//     function fibo(t){
    
//         if(fiboMap.has(t))return fiboMap.get(t);
    
//         let v = (fibo(t-1)+fibo(t-2))%M;
//         fiboMap.set(t, v);
    
//         return v;
//     }
    
    // var answer = fibo(n);
    var answer = fiboMap.get(n);
    return answer;
}
