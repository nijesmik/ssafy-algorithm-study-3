import java.util.ArrayList;
import java.util.List;

/** 등대
 * 알고리즘 : 트리 탐색
 * 핵심 아이디어 :
 *  1. 노드가 N개, 연결 관계가 N-1개임으로 트리임을 알 수 있다.
 *  2. 어떤 경로에서도 연속으로 등대가 없을 수 없다 => 트리의 자식 중 하나라도 등대가 안켜져있다면 자신이 켜져있어야 한다.
 *  3. 리프노드는 무조건 꺼져있어야 한다.
 *  헤멘 부분 :
 *  1. 처음에 단순 DFS로 접근. 지금 내 기준에서 켜져 있으면 다음엔 끄거나 키거나, 내가 꺼져있으면 다음은 무조건 켜는 방식으로 -> 딱봐도 시간 초과날 것 같았음
 *  2. 다음엔 최악의 경우를 생각해서, 가장 긴 경로를 기준으로 /2 한 값을 기준으로 생각하려고 했으나 중간에 다른 경로로 뻗어나가는 것을 고려하는 것이 복잡했음
 *  3. 트리임을 나중에 눈치채고 리프노드가 꺼져있음을 기준으로 트리 탐색을 생각 (후위 순회 개념으로 접근)
 *  4. 첫 제출이 틀렸는데, 리프노드 판별법이 단순하게 접근 경로가 1인 것으로 판단하여 루트노드도 리프노드로 인식될 수 있었음. 이 부분 개선하여 통과
 * */
class 등대 {

    static int answer = 0;
    static int N;
    static List<Integer>[] adjacencyList;
    static boolean[] visited;

    public int solution(int n, int[][] lighthouse) {
        N = n;
        adjacencyList = new List[N+1];
        for (int i = 1; i < N+1; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n-1; i++) {
            adjacencyList[lighthouse[i][0]].add(lighthouse[i][1]);
            adjacencyList[lighthouse[i][1]].add(lighthouse[i][0]);
        }

        visited = new boolean[N+1];
        isLightedDfs(1, 1);

        return answer;
    }

    private boolean isLightedDfs(int now, int root){
        // 제일 먼저 방문처리
        visited[now] = true;

        // 리프노드라면 등대 false
        if (now != root && adjacencyList[now].size() == 1) return false;

        // 리프 노트가 아니라면
        boolean isChildAllLighted = true;
        for (int next : adjacencyList[now]) {
            if (visited[next]) continue;
            isChildAllLighted &= isLightedDfs(next, root);
        }

        if (!isChildAllLighted){
            answer++;
            return true;
        } else {
            return false;
        }
    }
}