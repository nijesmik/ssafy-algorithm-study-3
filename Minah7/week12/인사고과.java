// https://school.programmers.co.kr/learn/courses/30/lessons/152995
// 실패. 에러

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        map.put(idx, scores[idx][0] + scores[idx][1]);
        
        boolean exist = true;
        for(int i = 1; i < scores.length; i++) {
            if(scores[i][0] >= scores[idx][0] && scores[i][1] >= scores[idx][1]) {
                if(scores[i][0] > scores[idx][0] && scores[i][1] > scores[idx][1]) {
                    exist = false;
                    break;
                } else {
                    map.put(i, scores[i][0] + scores[i][1]);
                }
                idx = i;
            }
        }
        if(exist) {
            Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
            map.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer> comparingByValue(Comparator.reverseOrder())
                          .thenComparing(Map.Entry.comparingByKey()))
                .forEachOrdered(x -> map.put(x.getKey(), x.getValue()));
            int wanhoScore = sortedMap.get(0);
            for(Integer i : sortedMap.values()) {
                if(i != wanhoScore) {
                    answer++;
                } else {
                    answer++;
                    break;
                }
            }
        } else {
            answer = -1;
        }
        
        return answer;
    }
}