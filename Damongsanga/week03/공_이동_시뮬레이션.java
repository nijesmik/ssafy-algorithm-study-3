/** 공 이동 시뮬레이션
 * 알고리즘 : 구현
 * 핵심 아이디어 :
 * 1. 쿼리를 역순으로 반대로 탐색해가야 한다.
 * 2. 역순으로 탐색해보면 벽에 부딛히면서 가능한 경우가 직사각형 영역으로 커진다. 따라서 행 (rSrt, rEnd), 열(cSrt, cEnd) 범위별로 보면 된다.
 *  2.1 srt는 이동하는 방향에 반대방향으로 dx만큼 이동하면 쿼리 수행 직전의 범위가 된다. 여기서 0보다 작은 경우는 벽에 부딛히는 경우임으로 0으로 설정한다.
 *  2.2 end도 마찬가지로 이동하는 방향에 반대방향으로 dx만큼 이동하면 쿼리 수행 직전의 범위가 된다. 여기서 n-1이나 m-1보다 큰 경우는 벽에 부딛히는 경우임으로 n-1 or m-1로 설정한다.
 * 3. 엣지케이스가 존재한다. 예시로 m, n이 3,3이고 시작점이 1, 1인데 어느 방향으로든 dx를 2만큼 가는 쿼리가 주어지면 갈 수 있는 경우 자체가 없다.
 *  3.1 이 경우에는 0을 리턴해야 하는데, 이를 판단하는 법은 아래 로직대로 체크를 하되 srt가 end보다 커져버리는 경우이다.
 * */

class 공_이동_시뮬레이션 {
    static long[] rr = {0,0,-1,1};
    static long[] rc = {-1,1,0,0};

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long rSrt = x;
        long rEnd = x;
        long cSrt = y;
        long cEnd = y;

        for(int i = queries.length - 1; i >= 0 ; i--){
            int direction = queries[i][0];
            int dx = queries[i][1];
            if (rSrt != 0)
                rSrt = rSrt - rr[direction] * dx < 0 ? 0 : rSrt - rr[direction] * dx;
            if (rEnd != n-1)
                rEnd = rEnd - rr[direction] * dx >= n ? n-1 : rEnd - rr[direction] * dx;
            if (cSrt != 0)
                cSrt = cSrt - rc[direction] * dx < 0 ? 0 : cSrt - rc[direction] * dx;
            if (cEnd != m-1)
                cEnd = cEnd - rc[direction] * dx >= m ? m-1 : cEnd - rc[direction] * dx;

            if (rSrt > rEnd || cSrt > cEnd) return 0;

        }

        return (rEnd - rSrt + 1) * (cEnd - cSrt + 1);
    }


}