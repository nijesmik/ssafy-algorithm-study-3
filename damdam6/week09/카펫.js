function solution(brown, yellow) {
    var answer = [];
    
    let bx = 0;
    let by = 0;
    let yx = 0;
    let yy = 0;
    let modb = -1;
    let mody = -1;
    for(let i=3; i<=brown/2; i++){
        bx = i;
        by = brown/2+2 -i;
        if(by == 0)continue;       
        // console.log("bx "+bx+"by "+by);
        yx = i-2;
        mody = yellow % yx;
        if(mody != 0)continue;
        yy = yellow / yx;
        if(yy != by -2)continue;
        bx = i;
       break;  
    }
    
    return [by,bx];
}