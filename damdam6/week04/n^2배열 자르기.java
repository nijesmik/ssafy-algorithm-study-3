class Solution {
    public int[] solution(int n, long left, long right) {
        
        int length = (int)(right - left + 1);
        int[] answer = new int[length];
  
        

        for(long i = left; i <=right;i++ ){
            int x = (int) (i/n);
            int y = (int) (i%n);
            answer[(int)(i-left)] = Math.max(x, y)+1;
        }
        
        
        return answer;
    }
}