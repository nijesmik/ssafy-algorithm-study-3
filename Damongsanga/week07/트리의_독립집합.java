import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**트리의 독립집합
 *
 * 알고리즘 : DP (자신을 선택했는지 / 안했는지 기준)
 * 핵심 아이디어
 * 1. dfs로 탐색. 후위 순회를 위해 먼저 진입을 하고, 자식의 memo값을 확인
 * 2. 부모 O : 초기값은 자신의 가중치. 이후 자식이 자신을 선택하지 않은 memo 값을 더함
 * 3. 부모 X : 초기값은 0. 이후 자식이 자신을 선택한 memo값과 자신을 선택하지 않은 memo 값 중 더 큰 값을 더해줌
 *
 * 4. 최대 독립 집합 값을 구했다면 이후 역탐색하여 루트 찾기
 * 5. 루트에서 다시 시작. 여기서 4번에서 고른 memo 값부터 탐색해야함. (자신을 선택 했는지, 안했는지)
 * 6. 현재 memo가 자신을 고른 값이라면, 자식은 자신을 고르지 않은 경우만 탐색
 * 7. 현재 memo가 자신을 고르지 않은 값이라면, 자식은 자신을 고른 경우, 아닌 경우를 비교하여 큰 쪽으로 탐색
 *
 * 헤멘 부분
 * 1. DP를 사용하긴 했는데 그냥 현재 위치에서 최선값만 저장함. 그러다보니 역 탐색이 불가능했음
 * 2. 선택 했느냐, 안했느냐로 구분되는 DP 설계에 더 익숙해져야겠음 (사실상 벽뿌수고 가기 문제랑 같음)
 *
 * */
public class 트리의_독립집합 {
    static int[] nodes;
    static int[][] memo;
    static List<Integer>[] edgeList;
    static boolean[] visited;
    static List<Integer> res;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        nodes = new int[N+1];
        memo = new int[N+1][2];
        visited = new boolean[N+1];
        res = new ArrayList<>();
        String[] tmp2 = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            nodes[i] = Integer.parseInt(tmp2[i-1]);
        }

        edgeList = new List[N+1];
        for (int i = 1; i < N+1; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            String[] tmp = br.readLine().split(" ");
            int left = Integer.parseInt(tmp[0]);
            int right = Integer.parseInt(tmp[1]);
            edgeList[left].add(right);
            edgeList[right].add(left);
        }

        dfs(1);

        if (memo[1][0] > memo[1][1]){
            System.out.println(memo[1][0]);
            trace(1,0);
        } else {
            System.out.println(memo[1][1]);
            trace(1,1);
        }

        Collections.sort(res);
        StringBuilder sb = new StringBuilder();
        for(int i : res){
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
    private static void dfs(int now){
        memo[now][0] = 0;
        memo[now][1] = nodes[now];
        visited[now] = true;

        for (int child : edgeList[now]) {
            if (visited[child]) continue;
            dfs(child);
            if (memo[child][0] > memo[child][1]){
                memo[now][0] += memo[child][0]; // 부모 X, 자식 X
            } else {
                memo[now][0] += memo[child][1]; // 부모 X, 자식 O
            }
            memo[now][1] += memo[child][0]; // 부모 O, 자식 X
        }
    }

    private static void trace(int now, int memoIdx){
        visited[now] = false;
        if (memoIdx == 1){
            res.add(now);
            for (int next : edgeList[now]){
                if (!visited[next]) continue;
                trace(next, 0);
            }
        } else {
            for (int next : edgeList[now]){
                if (!visited[next]) continue;
                if (memo[next][0] > memo[next][1]){
                    trace(next, 0);
                } else {
                    trace(next, 1);
                }
            }
        }
    }
}