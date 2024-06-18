// 성공

class Solution {
    public int solution (int n) {
        int answer = 0;
        int start = 1;
        while (start <= n) {
            int sum = start;
            for(int i = start + 1; i <= n; i++) {
                if(sum == n) {
                    System.out.println(start);
                    answer++;
                    break;
                } 
                sum += i;
                if(sum > n) {
                    break;
                }
            }
            start++;
        }
        answer++;
        return answer;
    }
}