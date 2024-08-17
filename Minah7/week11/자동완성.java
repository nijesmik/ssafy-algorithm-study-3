// 실패
// https://school.programmers.co.kr/learn/courses/30/lessons/17685

import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        ArrayList<Integer>[] educated = new ArrayList[26];
        HashMap<String, Integer> prefix = new HashMap<>(); 
        for(int w = 0; w < words.length; w++) {
            int first = words[w].charAt(0) - 'a';
            if(educated[first] == null) {
                answer += words[w].length();
                educated[first] = new ArrayList<>();
                educated[first].add(w);
                prefix.put(words[w], 1);
            } else if(prefix.containsKey(words[w])) {
                answer += prefix.get(words[w]);
            } else {
                for(int i = 2; i <= words[w].length(); i++) {
                    boolean found = false;
                    String s = words[w].substring(0, i);
                    if(prefix.containsKey(s)) {
                        continue;
                    }
                    for(int j = 0; j < educated[first].size(); j++) {
                        if(words[educated[first].get(j)].length() < s.length()) {
                            continue;
                        }
                        if(words[educated[first].get(j)].substring(0, i).equals(found)) {
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        educated[first].add(w);
                        prefix.put(words[w], i);
                        answer += i;
                    }
                }
            }
        }
        return answer;
    }
}