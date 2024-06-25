class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for(int i = yellow; i > 0; i--) {
            if(yellow % i == 0) {
                int width = i * 2;
                int height = (yellow / i) * 2;
                if(brown == width + height + 4) {
                    answer[0] = height / 2 + 2;
                    answer[1] = width / 2 + 2;
                }
            }
        }
        return answer;
    }
}