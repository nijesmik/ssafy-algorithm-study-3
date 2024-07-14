import java.util.Arrays;

/** 리틀 프렌즈 사천성
 * 알고리즘 : 구현
 * 핵심 아이디어 :
 *  1. DFS로 계속 탐색하면 안된다. (딱봐도 시간초과임)
 *  2. 문제에서 한번만 꺾을 수 있다고 했음으로 두 알파벳의 위치를 대각선 꼭지점으로 가지는 직사각형에서 시계방향, 반시계방향 경로 총 2개만 생각해야 한다.
 *  구현 방법 :
 *  1. 알파벳의 범위는 26으로 정해져있음으로 모든 알파벳의 위치를 기록해둘 charPositions 배열을 만든다
 *  2. 출력을 알파벳 순서대로 하라고 했음으로 A 부터 지울 수 있는지 탐색하여 지웠다면 다시 A부터 처음부터 탐색해야 한다.
 *  3. 탐색 과정은 쌍 증에서 첫 번째 알파벳을 a1, 두 번째 알파벳을 a2라고 하면 a1 r에서 a2 r까지 row 이동, a1 c에서 a2 c까지 col 이동하는 경로 1개랑
 *     a1 c에서 a2 c까지 col 이동하고, a1 r에서 a2 r까지 row 이동하는 경로, 총 2개만 보면 된다.
 *     탐색하는 방향은 중요하지 않으니 그냥 모두 증가하는 방향으로 구현했다.
 * */
class 리틀_프렌즈_사천성 {

    static char[][] gameBoard; // 게임판
    static int[][] charPositions; // 각 알파벳의 위치 (r1, c1, r2, c2)

    public String solution(int m, int n, String[] board) {

        final int[] EMPTY_POSITION = {-1,-1,-1,-1};
        StringBuilder sb = new StringBuilder();

        // input 받기
        charPositions = new int[26][4];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(charPositions[i], -1);
        }
        gameBoard = new char[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                gameBoard[i][j] = board[i].charAt(j);
                if ('A' <= gameBoard[i][j] && gameBoard[i][j] <= 'Z'){
                    int idx = gameBoard[i][j]-'A';
                    if (charPositions[idx][0] != -1){
                        count++;
                        charPositions[idx][2] = i;
                        charPositions[idx][3] = j;
                    } else {
                        charPositions[idx][0] = i;
                        charPositions[idx][1] = j;
                    }
                }
            }
        }

        // 쌍 갯수만큼 반복
        for (int tc = 0; tc < count; tc++) {
            for (int i = 0; i < 26; i++) {
                if (charPositions[i][0] == -1) continue; // 알파벳 탐색
                if(!isMovable(i)) continue;

                // 알파벳끼리 만날 수 있으면 게임판을 초기화하고 알파벳 위치도 모두 -1로 초기화 (첫번째 위치만 볼 것임으로 사실 charPositions[i][0] = -1로만 해줘도 된다)
                gameBoard[charPositions[i][0]][charPositions[i][1]] = '.';
                gameBoard[charPositions[i][2]][charPositions[i][3]] = '.';
                charPositions[i] = EMPTY_POSITION;
                sb.append((char) ('A'+i));
                break;
            }
        }
        // 만약 지워진 경우가 없다면
        if (sb.length() != count) return "IMPOSSIBLE";
        return sb.toString();
    }

    private boolean isMovable(int i){
        int rMin = Math.min(charPositions[i][0], charPositions[i][2]);
        int rMax = Math.max(charPositions[i][0], charPositions[i][2]);
        int cMin = Math.min(charPositions[i][1], charPositions[i][3]);
        int cMax = Math.max(charPositions[i][1], charPositions[i][3]);
        return (isMovableRow(charPositions[i][1], rMin, rMax, i) && isMovableCol(charPositions[i][2], cMin, cMax, i))
                || (isMovableRow(charPositions[i][3], rMin, rMax, i) &&  isMovableCol(charPositions[i][0], cMin, cMax, i));
    }

    private boolean isMovableRow(int c, int rMin, int rMax, int idx) {
        for (int i = rMin; i <= rMax; i++) {
            if (gameBoard[i][c] != '.' && gameBoard[i][c] != (char) ('A'+idx)) return false;
        }
        return true;
    }

    private boolean isMovableCol(int r, int cMin, int cMax, int idx) {
        for (int i = cMin; i <= cMax; i++) {
            if (gameBoard[r][i] != '.' && gameBoard[r][i] != (char) ('A'+idx)) return false;
        }
        return true;
    }
}