import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        // 끝나는 시간을 기준으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int end = targets[0][1];
        answer++;

        // 시점과 종점 비교해서 카운트
        for(int[] tar : targets) {
            if(tar[0] >= end) {
                end = tar[1];
                answer++;
            }
        }
        
        return answer;
    }
}