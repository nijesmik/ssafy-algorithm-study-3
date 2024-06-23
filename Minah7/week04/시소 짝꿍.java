// 실패
// 테케 5개가 자꾸 시간 초과 남.

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        for(int i = 0; i < weights.length - 1; i++) {
            for(int j = i + 1; j < weights.length; j++) {
                if(weights[i] == weights[j]) {
                    answer++;
                    continue;
                }
                
                int big = Math.max(weights[i], weights[j]);
                int small = Math.min(weights[i], weights[j]);
                
                if(big * 2 == small * 3 || big * 2 == small * 4 
                   || big * 3 == small * 4) {
                    answer++;
                }
            }
        }
        return answer;
    }
}