// https://school.programmers.co.kr/learn/courses/30/lessons/42898

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] arr = new int[n][m];
        for(int i = 0; i < puddles.length; i++) {
            arr[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
        }
        
        arr[0][0] = 1;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(arr[r][c] == -1) {
                    arr[r][c] = 0;
                    continue;
                }
                if(r != 0) {
                    arr[r][c] += arr[r-1][c] % 1000000007;
                }
                if(c != 0) {
                    arr[r][c] += arr[r][c-1] % 1000000007;
                }
            }
        }
        return arr[n - 1][m - 1] % 1000000007;
    }
}