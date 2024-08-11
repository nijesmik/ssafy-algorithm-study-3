// 미완성
// https://school.programmers.co.kr/learn/courses/30/lessons/132266

class Solution {
    static int[] answer;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        answer = new int[sources.length];
        ArrayList<Integer>[] road = new ArrayList[n + 1];
        for(int i = 0; i < roads.length; i++) {
            if(road[roads[i][0]] == null) {
                road[roads[i][0]] = new ArrayList<Integer>();
            }
            road[roads[i][0]].add(roads[i][1]);
        }
        
        findMinDistance();
        
        return answer;
    }
    public void findMinDistance() {
        for(int i = 0; i < sources.length; i++) {
            int min = roads.length;
            Queue<Integer> que = new LinkedList<>();
            que.add(sources[i]);
            while(!que.isEmpty()) {
                int start = que.poll();
            }
        }
    }
}