// https://school.programmers.co.kr/learn/courses/30/lessons/87946
// 성공

class Solution {
    static int answer = 0;
    public int solution(int k, int[][] dungeons) {
        int n = dungeons.length;
        boolean[] visited = new boolean[n];
        dfs(dungeons, visited, k, k, 0);
        return answer;
    }
    static void dfs(int[][] dungeons, boolean[] visited, int k, int rest, int complete) {
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && rest >= dungeons[i][0]) {
                visited[i] = true;
                dfs(dungeons, visited, k, rest - dungeons[i][1], complete + 1);
                visited[i] = false;
            } else {
                answer = Math.max(answer, complete);
            }
        }
    }
}