class Solution {
    public String solution(String s) {
        String answer = "";
        int cnt = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                cnt = 0;
                answer += " ";
                continue;
            }
            if(cnt % 2 == 0) {
                answer += s.substring(i, i+1).toUpperCase();
            } else if(cnt % 2 == 1) {
                answer += s.substring(i, i+1).toLowerCase();
            }
            cnt++;
        }
        return answer;
    }
}