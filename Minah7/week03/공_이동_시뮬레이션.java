// 처음엔 문제 이해가 안돼서 블로그 참고.
// 다시 풀어보기

import java.util.*;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;
        
        long row1 = x, row2 = x;
        long col1 = y, col2 = y;
        
        for(int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int cnt = queries[i][1];
            
            if(dir == 0) {
                if(col1 != 0) {
                    col1 += cnt;
                }
                col2 = Math.min(col2 + cnt, m - 1);
                if(col1 > m - 1) {
                    return 0;
                }
            }
            else if(dir == 1) {
                if(col2 != m - 1) {
                    col2 -= cnt;
                }
                col1 = Math.max(col1 - cnt, 0);
                if(col2 < 0) {
                    return 0;
                }
            }
            else if(dir == 2) {
                if(row1 != 0) {
                    row1 += cnt;
                }
                row2 = Math.min(row2 + cnt, n - 1);
                if(row1 > n - 1) {
                    return 0;
                }
            }
            else {
                if(row2 != n - 1) {
                    row2 -= cnt;
                }
                row1 = Math.max(row1 - cnt, 0);
                if(row2 < 0) {
                    return 0;
                }
            }
        }
        
        answer = (row2 - row1 + 1) * (col2 - col1 + 1);
        
        return answer;
    }
}