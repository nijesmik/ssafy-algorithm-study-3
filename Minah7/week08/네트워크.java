// 성공

import java.util.*;

class Solution {
    static int answer = 0;
    
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(computers, visited, i);
            }
        }
        
        return answer;
    }
    
    public void bfs(int[][] computers, boolean[] visited, int c) {
        Queue<Integer> que = new LinkedList<>();
        
        que.add(c);
        visited[c] = true;
        
        while(!que.isEmpty()) {
            int computer = que.poll();
            
            for(int i = 0; i < computers.length; i++) {
                if(computers[computer][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    que.add(i);
                }
            }
        }
        answer++;
    }
}