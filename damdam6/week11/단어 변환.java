import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        
        wordsLength = words.length;
        
        // 타겟이 있나요?
        int targetIdx = -1;
        for(int i=0; i< words.length; i++){
            String str = words[i];
            if(str.equals(target)){
                targetIdx = i;
                break;
            }
        }
        if(targetIdx == -1){
            return 0;
        }
        
        // 이동할 수 있는 조합 확인
        int[][] canGo = canChangeBoard(words);
        
        int answer = bfs(begin, targetIdx, words, canGo);
        
        
        return answer;
    }

    int bfs(String begin, int targetIdx, String[] words, int[][] canGo){
        
        int[] canMatchWithBegin = new int[wordsLength];
        for(int i=0; i< wordsLength; i++){
            canMatchWithBegin[i] = canChange(begin, words[i]);
        }
        
        ArrayDeque<Words> qu = new ArrayDeque<>();
        qu.add(new Words(1, targetIdx, words[targetIdx]));
        
        while(!qu.isEmpty()){
            
            Words w = qu.poll();
            
            if(canMatchWithBegin[w.idx] == 1){
                return w.cnt;
            }
            
            for(int i=0; i< wordsLength; i++){
                if(w.vt[i] == 1)continue;
                if(canGo[w.idx][i] == 0)continue;
                w.vt[i] = 1;
                qu.add(new Words(w.cnt+1, i, words[i], w.vt));
                w.vt[i] = 0;
                
            }
            
        }
        
        return 0;
    }
    
    int canChange(String a, String b){
        boolean alreadyDif = false;
        for(int i=0; i<a.length();i++){
            if(a.charAt(i) != b.charAt(i)){
                if(alreadyDif){
                    return 0;
                }
                alreadyDif = true;
            }
        }
        return 1;
    }
    
    
    static int wordsLength;
    class Words{
        
        int cnt;
        int idx;
        String nowWords;
        int[] vt;
        
        Words(int cnt, int idx, String nowWords){
            this.cnt = cnt;
            this.idx = idx;
            this.nowWords = nowWords;
            this.vt = new int[wordsLength];
            this.vt[idx] = 1;
        }
        
        Words(int cnt, int idx, String nowWords, int[] vt){
            this.cnt = cnt;
            this.idx = idx;
            this.nowWords = nowWords;
            this.vt = Arrays.copyOf(vt, vt.length);
        }
        
        
    }
    
    // 변환 가능한 조합 반환
    int[][] canChangeBoard(String[] words){
        int[][] result = new int[words.length][words.length];
        for(int i=0; i < words.length; i++){
            for(int j=i+1; j< words.length; j++){
                int r = canChange(words[i], words[j]);
                result[i][j] = r;
                result[j][i] = r;
            }
        }
        
        return result;
    }
    
    

    

}