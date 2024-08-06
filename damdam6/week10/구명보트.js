function solution(people, limit) {
    
    let sortArr = people;
    
    sortArr.sort((a,b) => a-b);
    //console.log(sortArr);
    
    
    let arrSize = sortArr.length;
    let right = arrSize -1;
    let left = 0;
    let boat = 0;
    
    while(left <= right){

        if(sortArr[left] + sortArr[right] <= limit){
            left++;
            right--;
        }else{
            right--;
        }
        boat++;
        
    }
    
    var answer = boat;
    return answer;
}