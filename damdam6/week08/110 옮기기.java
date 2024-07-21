import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        int length = s.length;
        String[] answer = new String[length];

        int countX;
        for(int t = 0; t<length; t++){
            countX = 0;
        // 뽑을 때는? -> 다 뽑기
            char[] cArr = s[t].toCharArray();
            
            int i = 0;
            int record = 0;
             while(i < cArr.length - 2){
                record = i;
                int a = i;
                 
                if(a >= cArr.length)break;
                while(cArr[a] == '.'){
                    
                    a++;
                };
                 
                 // 시작점 바꾸기
                 if(cArr[a] == '0'){
                     // 0이므로 어차피 시작 안됨
                     i = a+1;
                     continue;
                 }
                int b = a+1;
                 if(b >= cArr.length)break;
                while(cArr[b] == '.'){
                    b++;
                }
                 if(cArr[b] == '0'){
                     i = b+1;
                     continue;
                 }
                int c = b+1;
                 if( c >= cArr.length)break;
                 while(cArr[c] == '.'){
                     c++;
                 }
                 if(cArr[c] == '1'){
                     i = b;
                     continue;
                 }
                 
                 countX ++;
                 cArr[a] = '.';
                 cArr[b] = '.';
                cArr[c] = '.';
                 i = ;
            }
            
            System.out.println(Arrays.toString(cArr));
            
            // for(int i=0; i<cArr.length-2;i++){
            //     if(cArr[i] == '1' 
            //       && cArr[i+1] == '1' 
            //       && cArr[i+2] == '0' ){
            //         countX++;
            //         cArr[i] = '.';
            //         cArr[i+1] = '.';
            //         cArr[i+2] = '.';
            //     }
            // }
            
        // 0을 앞 쪽으로 밀어낼 수 있도록 뒤에 달기
          
            int idx = cArr.length-1;
            String newS = "";
            while(idx >= 0 ){
                if(cArr[idx] == '.'){
                    idx--;
                }else if(cArr[idx] == '0'){
                    idx--;
                    // 더할 110이 없으면
                    if(countX <= 0){ 
                        newS = "0"+newS;
                        continue;
                    }
                    // 있을 경우
                    newS = "0110"+newS;
                    countX--;
                }else{
                   newS = "1"+newS;
                    idx--;
                }
                
                if(t==2){
                    System.out.println(newS);
                };
            }
            
            while(countX > 0){
                newS = "110"+newS;
                countX--;
            }
            
            
            answer[t] = newS;
        
            
        }
        
        
        return answer;
    }
}