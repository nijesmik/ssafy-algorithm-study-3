import java.util.PriorityQueue;

public class 요격_시스템 {
  class Solution {
    public int solution(int[][] targets) {
      int answer = 0;
      PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
      for(int[] target : targets) {
        pq.add(target);
      }

      int end = pq.peek()[1];
      while(!pq.isEmpty()) {
        int[] now = pq.poll();
        if(now[0] >= end) {
          answer++;
          end = now[1];
        }
      }

      return answer+1;
    }
  }
}
