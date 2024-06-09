import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String, Integer> item = new HashMap<>();
        for(int i = 0; i < want.length; i++) {
            item.put(want[i], number[i]);
        }
        
        for(int i = 0; i < discount.length-10+1; i++) {
            Map<String, Integer> check = new HashMap<>();
            for(int j = 0; j < 10; j++) {
                check.put(discount[i+j], check.getOrDefault(discount[i+j], 0) + 1);
            }
            
            boolean isSame = true;
            for(String key : item.keySet()) {
                if(item.get(key) != check.get(key)) {
                    isSame = false;
                    break;
                }
            }
            
            if(isSame) {
                answer++;
            }
        }
        return answer;
    }
}