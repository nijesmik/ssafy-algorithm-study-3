// 성공

import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        // 보드 생성 + 값 넣기
        int[][] board = new int[rows+1][columns+1];
        int num = 1;
        for(int r = 1; r <= rows; r++) {
            for(int c = 1; c <= columns; c++) {
                board[r][c] = num++;
            }
        }
        // queries 개수만큼 회전 실행
        for(int q = 0; q < queries.length; q++) {
            int x1 = queries[q][0];
            int y1 = queries[q][1];
            int x2 = queries[q][2];
            int y2 = queries[q][3];
            List<Integer> arrNum = new ArrayList<>();
            for(int n = y1; n <= y2; n++) {
                arrNum.add(board[x1][n]);
            } // 위
            for(int e = x1+1; e < x2; e++) {
                arrNum.add(board[e][y2]);
            } // 오른쪽
            for(int s = y2; s >= y1; s--) {
                arrNum.add(board[x2][s]);
            } // 아래
            for(int w = x2-1; w > x1; w--) {
                arrNum.add(board[w][y1]);
            } // 왼쪽
            answer[q] = Collections.min(arrNum);
            // 지금부터 회전된 값 넣어주기
            int idx = 0;
            for(int n = y1+1; n <= y2; n++) {
                board[x1][n] = arrNum.get(idx++);
            } // 위
            for(int e = x1+1; e < x2; e++) {
                board[e][y2] = arrNum.get(idx++);
            } // 오른쪽
            for(int s = y2; s >= y1; s--) {
                board[x2][s] = arrNum.get(idx++);
            } // 아래
            for(int w = x2-1; w >= x1; w--) {
                board[w][y1] = arrNum.get(idx++);
            } // 왼쪽
        }
        return answer;
    }
}