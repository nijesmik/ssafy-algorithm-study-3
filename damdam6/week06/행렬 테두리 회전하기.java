import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        
        int[][] bd = new int[rows][columns];
        
        // 배열에 값 할당
        int value = 1;
        for(int i=0;i<rows;i++){
            for(int j=0; j<columns; j++){
                bd[i][j] = value++;
            }
        }
        
        int[] answer = new int[queries.length];
        for(int k = 0; k < queries.length; k++){
            
            int minValue = Integer.MAX_VALUE;
           
            int[] info = queries[k];
             // 행렬 수치 동일 기준으로 변경
            for(int i=0;i<4;i++){
                info[i]--;
            }
            
            int x = info[2] - info[0];
            int y = info[3] - info[1];
            
            int nx = bd[info[0]][info[1]];
            // 가로로 한줄
            for(int i=info[1]; i<= info[3]; i++){
                
                // 최솟값 구하기
                minValue = Math.min(minValue, nx);
                int now=nx;
                if(i == info[3]){
                    nx = bd[info[0]+1][info[3]];
                    bd[info[0]+1][info[3]] = now;
                }else{
                    nx = bd[info[0]][i+1];
                    bd[info[0]][i+1] = now;
                }  
            } 
            
            // // 출력확인
            // System.out.println("=======");
            // for(int i=0;i<rows;i++){
            //     System.out.println(Arrays.toString(bd[i]));
            // }
            
            
            // 세로로 한줄
            for(int j=info[0]+1; j <= info[2]; j++){
                
                // 최솟값 구하기
                minValue = Math.min(minValue, nx);
                int now=nx;
                if(j == info[2]){
                    nx = bd[j][info[3]-1];
                    bd[j][info[3]-1] = now;
                }else{
                    nx = bd[j+1][info[3]];
                    bd[j+1][info[3]] = now;
                }  
            } 
            
            // // 출력확인
            // System.out.println("=======");
            // for(int i=0;i<rows;i++){
            //     System.out.println(Arrays.toString(bd[i]));
            // }
            

            
            // 가로로 한줄
            for(int i=info[3]-1; i >= info[1]; i--){
                
                // 최솟값 구하기
                minValue = Math.min(minValue, nx);
                int now=nx;
                if(i == info[1]){
                    nx = bd[info[2]-1][i];
                    bd[info[2]-1][i] = now;
                }else{
                    nx = bd[info[2]][i-1];
                    bd[info[2]][i-1] = now;
                }  
            } 
            
            System.out.println(minValue);
            
         // // 출력확인
         //    System.out.println("=======");
         //    for(int i=0;i<rows;i++){
         //        System.out.println(Arrays.toString(bd[i]));
         //    }
            
            
            
            for(int j=info[2]-1; j >= info[0]; j--){
                
                // 최솟값 구하기
                minValue = Math.min(minValue, nx);
                int now=nx;
                if(j == info[0]){
                    // nx = bd[j][info[3]-1];
                    bd[j][info[1]+1] = now;
                }else{
                    nx = bd[j-1][info[1]];
                    bd[j-1][info[1]] = now;
                }  
            } 
            
            // // 출력확인
            // System.out.println("=======");
            // for(int i=0;i<rows;i++){
            //     System.out.println(Arrays.toString(bd[i]));
            // }
            
            answer[k] = minValue;
            
        }
        
        
        return answer;
    }
}