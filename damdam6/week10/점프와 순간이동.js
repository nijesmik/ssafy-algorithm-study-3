function solution(n)
{
    let tmp = n;
        var ans = 0;
    while(tmp > 0){
        if(tmp % 2 == 0){
            tmp = tmp /2;
        }else{
            tmp = (tmp-1);
            ans += 1;
        }
    }
    


    return ans;
}