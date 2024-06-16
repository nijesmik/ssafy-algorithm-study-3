class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        int l = 10;
        int r = 12;
        
        for(int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            if(n == 1 || n == 4 || n == 7) {
                answer += "L";
                l = n;
            } else if(n == 3 || n == 6 || n == 9) {
                answer += "R";
                r = n;
            } else {
                if(n == 0) {
                    n = 11;
                }
                
                int disL = (Math.abs(n - l) / 3) + (Math.abs(n - l) % 3);
                int disR = (Math.abs(n - r) / 3) + (Math.abs(n - r) % 3);
                
                if(disL == disR) {
                    if(hand.equals("left")) {
                        answer += "L";
                        l = n;
                    } else {
                        answer += "R";
                        r = n;
                    }
                    continue;
                }
                
                int min = Math.min(disL, disR);
                if(min == disL) {
                    answer += "L";
                    l = n;
                } else {
                    answer += "R";
                    r = n;
                }
            }
        }
        return answer;
    }
}