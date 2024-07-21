import java.util.*;

/** 모두 0으로 만들기
 * 핵심 아이디어
 * 1. 위상정렬 : 인접하는 노드가 1개인 노드가 리프노드. 리프노드가 a 값을 가지고 있으면 무조건 절대값(a)만큼 주어진 행동을 해야 한다.
 * 2. 따라서 리프노드부터 차례대로 부모노드(인접노드)에게 자신의 값을 넘겨주면 된다.
 * 헤멘 부분
 * 1. 인접행렬을 1 감소하는 부분을 queue에서 뽑아올 때 해야한다.
 * 2. 주변 노드들을 탐색하면서 인접행렬이 "1"일때 가져온다.
 * 3. int 범위를 넘어갈 수 있다... 답이랑 배열 모두 long으로 해야함
 *
 * */

class 모두_0으로_만들기 {
    static int N;
    public long solution(int[] a, int[][] edges) {
        if (!isPossible(a)) return -1;

        N = a.length;

        long[] arr = Arrays.stream(a).asLongStream().toArray();

        int[] inV = new int[N];
        List<Integer>[] edgeLists = new List[N];

        for (int i = 0; i < N; i++) {
            edgeLists[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            inV[edge[0]]++;
            inV[edge[1]]++;
            edgeLists[edge[0]].add(edge[1]);
            edgeLists[edge[1]].add(edge[0]);
        }

        return topologicalSort(arr, edgeLists, inV);
    }

    private static boolean isPossible(int[] a){
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        return sum == 0;
    }

    private static long topologicalSort(long[] arr, List<Integer>[] edgeLists, int[] inV){
        long res = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (inV[i] == 1) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int now = queue.poll();
            inV[now]--;

            for (Integer next : edgeLists[now]) {
                if (inV[next] == 0) continue;

                res += Math.abs(arr[now]);
                arr[next] += arr[now];
                arr[now] = 0;


                inV[next]--;
                if (inV[next] == 1) {
                    queue.add(next);
                }
            }
        }

        return res;
    }
}