function solution(m, n, board) {
    var answer = 0;
    
      board = board.map(r => r.split(''));
    
    // 위치 보여주는 class
    class Pos{
        
        x;
        y;
        
        constructor(x,y){
            this.x = x;
            this.y = y;
        }
    }
    
    let dx = [0,0,-1,1,1,-1,1,-1,0];
    let dy = [-1,1,0,0,1,1,-1,-1,0];
    
    // 체크 해야하는 squ 배열
    const squ = new Array(4).fill(null);  
    squ[0] = [1,2,5];
    squ[1] = [1,3,4];
    squ[2] = [0,3,6];
    squ[3] = [0,2,7];

    class PosAndSet{
        set;
        p;
        
        constructor(set, p){
            this.set = set;
            this.p = p;
        }
    }

    
    // 삭제 해야하는 dxy 인덱스를 set으로 반환함
    function getDelSet(p,c){
        let delSet = new Set();
A:        for(let a=0; a<4; a++){
            for(let b=0; b<3; b++){
                let nx = p.x + dx[squ[a][b]];
                let ny = p.y + dy[squ[a][b]];
                if(nx < 0 || ny < 0 || nx >= m || ny >=n )continue A;
                if(board[nx][ny] != c)continue A;
            }
            squ[a].forEach(value => delSet.add(value));         
        }
        
        if(delSet.size !=0){
            // 본인 위치 더하기
            delSet.add(8);
        }
        
        return new PosAndSet(delSet, p);
    }
    
    
    // 특정 Set+pos를 넣었을때 해당 위치에서 사각형들을 찾아 삭제하고 삭제한 값의 갯수 반환
    function del(delSet){
        let cnt = 0;
        delSet.forEach((v) => {
            p = v.p;

             for (let k of v.set) {
            if (board[p.x + dx[k]][p.y + dy[k]] == 'X') continue;
            board[p.x + dx[k]][p.y + dy[k]] = 'X';
            cnt++;
             }
            
        })
        return cnt;

    }
    
    // 이번 턴에서 삭제된 블럭의 갯수를 반환하는 함수
    function popSqu(){
        let popCnt = 0;
        // Set을 우선적으로 모으는 것으로 변경
        let totalSet = [];
        for(let i=0; i<m; i++){
            for(let j=0; j<n; j++){
                if(board[i][j] === 'X')continue;
                totalSet.push( 
                                    getDelSet(new  Pos(i,j),
                                             board[i][j]));
            }
        }
        popCnt += del(totalSet);
        
        return popCnt;
    }
    
    
    // board에 'X'위로 블럭이 떨어지도록 재구성

    function movSqu(){
      let  newBoard = new Array(m).fill(null).map(() => new Array(n).fill('X'));
        
        for(let j = 0; j<n; j++){
            let newi = m-1;
            for(let i = m-1; i >= 0; i--){
                if(board[i][j] == 'X')continue;
                newBoard[newi--][j] = board[i][j];
            }
        }
        
        board = newBoard;
    }
    
    
    // 블럭 삭제 후 블럭 떨어지는 과정을 확인하고 answer에 누적시키는 함수
    
    function play(){
        
        while(true){
            let cnt = popSqu();
        // console.log(board);
            if(cnt === 0)return;
            answer += cnt;
            movSqu();
        
        // console.log(board);
            
        }
        
    }
    
    // 실제 실행
    play();
    
    
    
    return answer;
}