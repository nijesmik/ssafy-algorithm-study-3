// 성공. 블로그 참고함.
// 다시 풀어보기.

class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int n = cookie.length;
        int[] forward = new int[n];
        int[] backward = new int[n];
        
        if(n == 1) {
            return answer;
        }
        
        forward[0] = cookie[0];
        backward[n - 1] = cookie[n - 1];
        for(int i = 1; i < n; i++) {
            forward[i] = forward[i - 1] + cookie[i];
            backward[n - 1 - i] = backward[n - 1 - i + 1] + cookie[n - 1 - i];
        }
        
        int toLeft, toRight, leftBias, rightBias;
        for(int i = 0; i < n - 1; i++) {
            toLeft = i;
            toRight = i + 1;
            leftBias = backward[i + 1];
            rightBias = forward[i];
            while(toRight < n && forward[toRight] - rightBias < answer) {
                toRight++;
            }
            while(toLeft > -1 && backward[toLeft] - leftBias < answer) {
                toLeft--;
            }
            while(toLeft > -1 && toRight < n) {
                int leftValue = backward[toLeft] - leftBias;
                int rightValue = forward[toRight] - rightBias;
                if(leftValue == rightValue) {
                    answer = leftValue;
                    toRight++;
                    toLeft--;
                } else if(leftValue < rightValue) {
                    toLeft--;
                } else {
                    toRight++;
                }
            }
        }
        return answer;
    }
}