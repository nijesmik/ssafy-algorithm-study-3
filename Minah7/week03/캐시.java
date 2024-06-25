import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> cache = new LinkedList<>();
        for(int i = 0; i < cities.length; i++) {
            String c = cities[i].toLowerCase();
            if(!cache.contains(c)) {
                cache.addFirst(c);
                answer += 5;
            } else {
                cache.remove(c);
                cache.addFirst(c);
                answer ++;
            }
            if(cache.size() > cacheSize) {
                cache.pollLast();
            }
        }
        return answer;
    }
}