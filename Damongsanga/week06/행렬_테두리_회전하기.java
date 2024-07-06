import java.util.*;

class 행렬_테두리_회전하기 {
    static int tmp1;
    static int tmp2;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows][columns];
        int tmp = 1;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                arr[i][j] = tmp++;
            }
        }
        for (int i = 0; i < queries.length; i++){
            answer[i] = rotate(queries[i], arr);
        }
        return answer;
    }
    private int rotate(int[] query, int[][] arr){
        int r1 = query[0]-1;
        int c1 = query[1]-1;
        int r2 = query[2]-1;
        int c2 = query[3]-1;
        tmp1 = arr[r1][c1];
        tmp2 = arr[r1][c1];
        int min = arr[r1][c1];

        for (int i = c1+1; i <= c2; i++){
            swap(arr[r1][i]);
            min = Math.min(arr[r1][i], min);
        }

        for (int i = r1+1; i <= r2; i++){
            swap(arr[i][c2]);
            min = Math.min(arr[i][c2], min);
        }

        for (int i = c2-1; i >= c1; i--){
            swap(arr[r2][i]);
            min = Math.min(arr[r2][i], min);
        }

        for (int i = r2-1; i >= r1; i--){
            swap(arr[i][c1]);
            min = Math.min(arr[i][c1], min);
        }


        return min;
    }

    private static void swap (int val){
        tmp2 = val;
        val = tmp1;
        tmp1 = tmp2;
    }
}