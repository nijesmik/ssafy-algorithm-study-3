/** 수레 움직이기
 *  난이도 자체는 어렵지 않으나 빡구현 문제로 실수할 부분이 많음.
 *  디버깅 포함해서 50분 정도 걸려서 품
 *  알고리즘 : DFS (맵 사이즈도 작고, 방문 배열 원복이 편하다고 생각되어 DFS로 선정)
 *  핵심 아이디어:
 *  분기를 잘해야 한다. 도착한 수레를 어떻게 안움직이게 반복문 내에서 구현할지가 핵심
 *  주의할 점 :
 *  1. 한 색깔의 수레가 이미 도착하였다면 더이상 이동하면 안됨. 이를 구현하기 위해 만약 도착했다면 for문의 i 값을 아예 늘려버려서 반복문 안돌도록 함
 *  2. 방문 배열을 int로 하여 red 인경우 1을 추가, blue인 경우 2를 추가함. 그 결과 방문시 1이나 2를 더하면 되고, 방문 판단도 쉽게 알 수 있음
 *  3. 주의할 점은 수레가 도착하여 더 움직이 않았는데 방문배열에 "다시 방문"했다고 판단할 수 있음. 이를 위해 움직였는지 여부를 체크해주어야함 (isFinished)
 *  4. 서로 같은 곳으로 이동하는 경우도 제외해주어야 하지만 서로가 서로를 거쳐가는 경우도 제외해주어야 함 (입출력 예시 4번)
* */
class 수레_움직이기 {
    static class Node{
        int r; int c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
        public boolean equals(Node node){
            return this.r == node.r && this.c == node.c;
        }
    }

    static int N;
    static int M;
    static Node bEnd;
    static Node rEnd;
    static int[] rr = {-1,0,1,0};
    static int[] rc = {0,1,0,-1};
    static int answer = 987654321;
    static int[][] visited;

    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        visited = new int[N][M];

        // 입력 받기
        Node bNow = null;
        Node rNow = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == 1) {
                    rNow = new Node(i, j);
                    visited[i][j] = 1;
                } else if (maze[i][j] == 2) {
                    bNow = new Node(i, j);
                    visited[i][j] = 2;
                } else if (maze[i][j] == 3){
                    rEnd = new Node(i,j);
                } else if (maze[i][j] == 4){
                    bEnd = new Node(i,j);
                }
            }
        }


        dfs(bNow, rNow, 0, maze);

        return answer == 987654321? 0 : answer;
    }


    private void dfs(Node bNow, Node rNow, int count, int[][] maze){

        if (bNow.equals(bEnd) && rNow.equals(rEnd)) {
            answer = count;
            return;
        }

        if (count >= answer) return;

        for (int i = 0; i < 4; i++) {
            int bnr = bNow.r;
            int bnc = bNow.c;
            boolean bFinished = false;

            // 이동 여부 판단 및 이동
            if (bNow.equals(bEnd)) {
                bFinished = true;
                i+=4;
            } else {
                bnr += rr[i];
                bnc += rc[i];
            }

            // 이동할 수 없는 곳인 경우
            if (isNotMovable(bnr, bnc, maze)) continue;
            // 이동하였는데 그 곳이 방문한 곳인 경우
            if (!bFinished && (visited[bnr][bnc] == 2 || visited[bnr][bnc] == 3)) continue;


            for (int j = 0; j < 4; j++) {
                int rnr = rNow.r;
                int rnc = rNow.c;
                boolean rFinished = false;

                // 이동 여부 판단 및 이동
                if (rNow.equals(rEnd)){
                    rFinished = true;
                    j+=4;
                } else {
                    rnr += rr[j];
                    rnc += rc[j];
                }

                // 이동 할 수 없는 공간인 경우
                if (isNotMovable(rnr, rnc, maze)) continue;
                // 이동하였는데 그 곳이 방문한 곳인 경우
                if (!rFinished && (visited[rnr][rnc] == 1 || visited[rnr][rnc] == 3)) continue;

                // 서로 같은 곳으로 가는 경우
                if (bnr == rnr && bnc == rnc) continue;
                // 서로가 서로를 가로질러가는경우
                if (bnr == rNow.r && bnc == rNow.c && rnr == bNow.r && rnc == bNow.c) continue;

                // DFS
                visited[bnr][bnc]+=2;
                visited[rnr][rnc]+=1;
                dfs(new Node(bnr, bnc), new Node(rnr, rnc), count+1, maze);
                visited[bnr][bnc]-=2;
                visited[rnr][rnc]-=1;
            }
        }
    }

    private boolean isNotMovable(int nr, int nc, int[][] maze){
        return nr < 0 || nc < 0 || nr >= N || nc >= M || maze[nr][nc] == 5;
    }


}