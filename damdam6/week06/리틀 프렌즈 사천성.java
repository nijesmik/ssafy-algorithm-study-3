import java.util.*;

class Solution {
    public String solution(int m, int n, String[] board) {
        
        TreeMap<Character, charPair> pairMap = new TreeMap<>();
        
        ArrayList<charPair> pairList = new ArrayList<>();
        
        char[][] bd = new char[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                char c = board[i].charAt(j);
                // 비어 있다면
                if(!pairMap.containsKey(c)){
                    pairMap.put(c, new charPair(c, new pos(i,j)));
                }else{
                    charPair cp = pairMap.get(c);
                    cp.p2 = new pos(i,j);
                    pairList.add(cp);
                }
                
                bd[i][j] = c;
            }
        }
        
        Collections.sort(pairList);
        
        while(true){
            break;
            
        }
        
        String answer = "";
        return answer;
    }
    
    class pos{
        int x;
        int y;
        
        pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
    }
    
    class charPair implements Comparable<charPair>{
        boolean done;
        char c;
        pos p1;
        pos p2;
        
        charPair(char c, pos p1){
            this.done = false;
            this.c = c;
            this.p1 = p1;
            this.p2 = null;
        }
        
        charPair(char c, pos p1, pos p2){
            this.done = false;
            this.c = c;
            this.p1 = p1;
            this.p2 = p2;
        }
        
        @Override
        public int compareTo(charPair o){
            if(this.done != o.done){
                if(this.done)return 1;
                if(o.done)return -1;
            }
            
            return Character.compare(this.c, o.c);
        }
    }
}