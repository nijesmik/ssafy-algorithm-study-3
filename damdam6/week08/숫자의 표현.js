function solution(n) {
    var answer = 1;
    
    for(let i=1;i<n/2;i++){
        let result = 0;
        let up = i;
        while(result < n){
            result += up;
            up++;           
            
        }
        
        if(n==result){
            // console.log(i);
            answer++;
        }
    }
    
    
    return answer;
}