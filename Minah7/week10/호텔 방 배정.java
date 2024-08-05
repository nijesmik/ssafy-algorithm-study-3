// 정확성 성공, 효율성 실패.

import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Set<Long> set = new HashSet<>();
        for(int i = 0; i < room_number.length; i++) {
            long room = room_number[i];
            if(!set.contains(room)) {
                set.add(room);
                answer[i] = room;
            } else {
                for(long start = room + 1; start <= k; start++) {
                    if(!set.contains(start)) {
                        set.add(start);
                        answer[i] = start;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}