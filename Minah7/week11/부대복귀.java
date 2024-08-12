// 실패. null pointer exception
// https://school.programmers.co.kr/learn/courses/30/lessons/132266

import java.util.*;

class Solution {
    static int[] answer;
    static ArrayList<Integer>[] road;
    static int d;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int s = sources.length;
        d = destination;
        answer = new int[s];
        road = new ArrayList[n + 1];
        for(int i = 0; i < roads.length; i++) {
            if(road[roads[i][0]] == null) {
                road[roads[i][0]] = new ArrayList<Integer>();
            }
            road[roads[i][0]].add(roads[i][1]);
            road[roads[i][1]].add(roads[i][0]);
        }
        
        findMinDistance(s, sources);
        
        return answer;
    }
    public void findMinDistance(int slen, int[] sources) {
        for(int i = 0; i < slen; i++) {
            int min = 0;
            
            Queue<Integer> que = new ArrayDeque<>();
            que.add(sources[i]);
            boolean found = false;
            int len = que.size();
            
            while(!que.isEmpty()) {
                int start = que.poll();
                if(start == d) {
                    found = true;
                    break;
                }
                len--;
                
                if(road[start] != null) {
                    for(int r = 0; r < road[start].size(); r++) {
                        if(road[start].get(r) != start) {
                            que.add(road[start].get(r));
                        }
                    }
                    if(que.contains(d)) {
                        min++;
                        found = true;
                        break;
                    }
                }
                
                if(len == 0) {
                    min++;
                    len = que.size();
                }
                
            }
            
            if(!found) {
                min = -1;
            }
            
            answer[i] = min;
        }
    }
}