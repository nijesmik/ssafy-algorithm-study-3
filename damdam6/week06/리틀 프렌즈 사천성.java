import java.util.*;

class Solution {
    public String solution(int m, int n, String[] board) {
        String answer = "";

        List<Tile> list = new ArrayList<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i].charAt(j) != '.'
                        && board[i].charAt(j) != '*'){
                    list.add(new Tile(i, j, board[i].charAt(j)));
                }
            }
        }

        char[][] cBoard = new char[m][n];
        for(int i=0; i < board.length; i++){
            cBoard[i] = board[i].toCharArray();
        }

        Collections.sort(list);
        // System.out.println(list.toString());

        StringBuilder sb = new StringBuilder();

        boolean del = true;
        boolean[] chk = new boolean[list.size() / 2];

        c: while(del){
            // System.out.println("sb: " + sb.toString());

            for(int i=0; i < list.size() / 2; i++){
                if(chk[i]) continue;

                Tile t1 = list.get(i * 2);
                Tile t2 = list.get(i * 2 + 1);



                if(canDel(t1, t2, cBoard, m, n)){
                    cBoard[t1.x][t1.y] = '.';
                    cBoard[t2.x][t2.y] = '.';
                    chk[i] = true;
                    sb.append(t1.c);
                    continue c;
                }
            }
            del = false;
        }

        if(sb.length() < list.size()/2){
            answer = "IMPOSSIBLE";
        }else{
            answer = sb.toString();
        }

        return answer;
    }



    boolean canDel(Tile t1, Tile t2, char[][] cBoard, int m, int n){
        int[][] vt = new int[m][n];
        if(chkOverlap(t1, t2, cBoard, vt, 1, -1, m, n)){
            return true;
        }
        if(chkOverlap(t2, t1, cBoard, vt, 2, 1, m, n)){
            return true;
        }
        return false;
    }

    boolean chkOverlap(Tile t1, Tile t2, char[][] cBoard, int[][] vt, int markN, int targetN, int m, int n){

        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};

        if(vt[t1.x][t1.y] == targetN){
            return true;
        } else {
            vt[t1.x][t1.y] = markN;
        }

        char c = t1.c;
        int nx, ny;

        for(int i=0; i<4; i++){
            nx = t1.x;
            ny = t1.y;

            while(true){
                nx += dx[i];
                ny += dy[i];

                if(nx < 0 || ny < 0 || nx >= m || ny >= n) break;

                if(vt[nx][ny] == targetN){
                    return true;
                }

                if(cBoard[nx][ny] == '*') break;

                if(cBoard[nx][ny] == '.'){
                    vt[nx][ny] = markN;
                    continue;
                }

                if(cBoard[nx][ny] == c){
                    vt[nx][ny] = markN;
                    return true;
                }

                break;
            }
        }

        return false;
    }

    class Tile implements Comparable<Tile>{
        int x;
        int y;
        char c;

        Tile(int x, int y, char c){
            this.x = x;
            this.y = y;
            this.c = c;
        }

        public int compareTo(Tile o){
            return Character.compare(this.c, o.c);
        }

        public String toString(){
            return "x: " + x + " y: " + y + " c: " + c;
        }
    }
}

