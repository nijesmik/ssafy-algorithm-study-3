import java.util.Arrays;

/**무지의 먹방 라이브
 * 알고리즘 : 정렬
 * 핵심 아이디어 :
 * 1. 인풋 값 고려하면 for문을 돌면 딱봐도 안됨. food_times 길이가 20만 이하임으로 정렬 가능
 * 2. index를 가지는 Node 클래스 만들어 값 기준 오름차순으로 정렬
 * 3. 음식 먹는데 필요한 시간이 짧은 음식 순으로 1개씩 먹었을 때의 시간 계산
 * 4. 남은 시간, 남은 음식에 따라서 마지막 지점 계산
 * 헤멘 부분 :
 * 1. 더이상 먹을 음식이 없으면 -1 반환해야하는 것 확인 안함
 * 2. foodCount를 index 개념으로 접근, 갯수, index 사이 1 차이 조심해야
 * */
class 무지의_먹방_라이브 {

    static class Node implements Comparable<Node>{
        int food; int idx;

        public Node(int food, int idx) {
            this.food = food;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            if (this.food == o.food) return this.idx - o.idx;
            return this.food - o.food;
        }
    }

    public int solution(int[] food_times, long k) {
        int N = food_times.length;

        Node[] nodes = new Node[N];
        for (int idx = 0; idx < N; idx++) {
            nodes[idx] = new Node(food_times[idx], idx);
        }

        Arrays.sort(nodes);

        int foodCount = 0; // 다 먹은 음식 갯수 (idx 아님)
        while(foodCount < N){
            long next = (long) (N - foodCount) * (nodes[foodCount].food - (foodCount == 0? 0 : nodes[foodCount-1].food));
            if (k < next) break; // 남은 음식의 최소값만큼 모두 먹지 못하면, break;
            k -= next;
            foodCount++;
        }

        // 모두 먹었으면 -1 반환
        if (foodCount == N) return -1;

        // 남아있는 음식 최소를 1로 맞춤 (for문 반복 횟수 줄이기 위함)
        k %= (N-foodCount);
        // 먹은 음식이 없다면 먹었는지 for 문을 돌면서 음식 먹었는지 여부를 확인할 필요 없이 index 기반으로 반환
        if (foodCount == 0){
            return (int) k + 1;
        }
        // 위 조건이 모두 맞지 않는다면 for 문을 돌면서 남은 음식 기반으로 멈추는 지점 확인
        for (int i = 0; i < N; i++) {
            if (food_times[i] <= nodes[foodCount-1].food) continue; // 현재 다 먹은 음식의 갯수보다 많은 경우는 제외
            if (k-- == 0) {
                return i+1;
            }
        }

        return -1;
    }
}