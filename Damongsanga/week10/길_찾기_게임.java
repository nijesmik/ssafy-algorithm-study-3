import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**길찾기 게임
 * 알고리즘 : DFS, 트리
 * 핵심 아이디어 : 모든 자식은 부모의 제한 범위를 따르게 됨으로, 탐색시 자식이 존재할 수 있는 범위를 지정하여 탐색하는 것이 포인트
 * 주의할 점 : 방문 배열을 사용하지 않으면 맨 왼쪽 자식만 탐색해버릴 수 있음
 * */
class 길_찾기_게임 {

    static class Node implements Comparable<Node>{
        int x; int y; int no;
        Node left; Node right;

        public Node(int x, int y, int no) {
            this.x = x;
            this.y = y;
            this.no = no;
        }

        @Override
        public int compareTo(Node o) {
            if (o.y == this.y) return this.x - o.x;
            return o.y - this.y;
        }
    }

    static List<Integer> preList = new ArrayList<>();
    static List<Integer> postList = new ArrayList<>();
    static boolean[] visited;

    public int[][] solution(int[][] nodeinfo) {
        int N = nodeinfo.length;
        Node[] nodes = new Node[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
        }
        Arrays.sort(nodes); // y 내림차순, x 오름차순 정렬

        // 트리 생성
        linkChild(0, -1, nodes[0].x, nodes);
        linkChild(0, nodes[0].x, 100001, nodes);

        // 탐색
        pre(nodes[0]);
        post(nodes[0]);
        int[][] answer = new int[2][N];
        answer[0] = preList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    public void linkChild(int idx, int lBound, int rBound, Node[] nodes){
        Node now = nodes[idx];
        visited[idx] = true; // 방문 배열 필요 (방문 배열 확인 안하면 제일 왼쪽 자식 노드만 검증하게 됨)

        // 자식 노드 탐색
        int nextIndex = idx+1;
        while(nextIndex < nodes.length && (visited[nextIndex] || nodes[nextIndex].y == now.y)){
            nextIndex++;
        }
        if (nextIndex == nodes.length) return;

        // 자식 노드 범위 검증
        Node node = nodes[nextIndex];
        if (!(lBound < node.x && node.x < rBound)) return;

        // 자식 연결
        if (node.x < now.x) now.left = node;
        else now.right = node;

        // 추가 탐색
        linkChild(nextIndex, lBound, node.x, nodes);
        linkChild(nextIndex, node.x, rBound, nodes);
    }

    public void pre(Node now){
        preList.add(now.no);
        if (now.left != null) pre(now.left);
        if (now.right != null) pre(now.right);
    }

    public void post(Node now){
        if (now.left != null) post(now.left);
        if (now.right != null) post(now.right);
        postList.add(now.no);
    }

}