/** 자물쇠와 열쇠
 * 핵심 아이디어
 * 단순 구현 문제로, key가 lock 의 바깥범위로 나갈 수 있음으로, key를 확장해서 풀이하면 쉽게 풀 수 있다.
 * 회전은 key가 아닌 lock을 하는 것이 더 효율적이다.
 * */

class 자물쇠와_열쇠 {
    static int N;
    static int M;
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;

        int[][] extendedKey = new int[M + N * 2 - 2][M + N * 2 - 2];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                extendedKey[i + (N-1)][j + (N-1)] = key[i][j];
            }
        }

        if (isFilledLock(lock)) return true;

        for (int i = 0; i < 4; i++) {
            for (int startR = 0; startR < N+M-2; startR++) {
                for (int startC = 0; startC < N+M-2; startC++) {
                    if (isUnlocked(startR, startC, extendedKey, lock)) return true;
                }
            }
            if (i != 3) lock = rotate(lock);
        }
        return false;
    }

    private boolean isFilledLock(int[][] lock){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) return false;
            }
        }
        return true;
    }

    private boolean isUnlocked(int startR, int startC, int[][] extendedKey, int[][] lock) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (lock[r][c] == extendedKey[startR + r][startC + c]) return false;
            }
        }
        return true;
    }

    // 시계 반대방향으로 90도 회전
    static int[][] rotate(int[][] lock){
        int[][] newLock = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newLock[i][j] = lock[N-j-1][i];
            }
        }
        return newLock;
    }
}

