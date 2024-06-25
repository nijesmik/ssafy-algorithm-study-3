// 성공


class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];

        int index = 0;

        for(int i = 0; i < answer.length; i++) {
            answer[i] = (int)Math.max((i + left) / n, (i + left) % n) + 1;
        }
        
        return answer;
    }
}