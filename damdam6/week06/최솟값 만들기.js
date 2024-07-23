function solution(A,B){
    var answer = 0;
    
    A.sort((a,b) => {
        if(a>b)return 1;
        if(a<b)return -1;
        return 0;
    })
    
    B.sort((a,b) => {
        if(a>b)return -1;
        if(a<b)return 1;
        return 0;
    })

    // console.log(A)
    // console.log(B)
    
    answer = A.reduce((acc,value,idx) => {
       return acc + value*B[idx]
    },0)
    

    return answer;
}