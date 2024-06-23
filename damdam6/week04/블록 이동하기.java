class Solution {
    public int solution(int[][] board) {
        int answer = 0;
// vt 배열을 어떻게 만들어야 하는지 모르겠음...

// dx dy 배열을 움직임 + 이동 개념으로 만들예정

        return answer;
    }
    
    class robot{
        int time;
        pos[] posArr;
        boolean horizon;
        
        robot(pos p1, pos p2, int time){

            this.posArr = new pos[]{p1,p2};
            this.time = time;
        }
    }
    
    class pos{
        int x;
        int y;
        
        pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}