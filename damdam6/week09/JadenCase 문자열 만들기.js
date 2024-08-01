function solution(s) {
    var answer = '';
    
    let arr = s.split(' ');
    let jan = arr.map((v)=>{
       return v.split('').map((c, idx) =>{
            
            if(idx === 0)return c.toUpperCase();
            return c.toLowerCase()
            
        }).join('');
       
    });
    
    
    //console.log(arr)
    answer = jan.join(' ');
    return answer;
}