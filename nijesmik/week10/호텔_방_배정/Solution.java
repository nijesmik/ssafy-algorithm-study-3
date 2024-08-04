import java.util.*;

class Solution {
    Map<Long, Long> map = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        int N = room_number.length;
        long[] answer = new long[N];
        for (int i = 0; i < N; i++) {
            answer[i] = get(room_number[i]);
        }
        return answer;
    }

    long get(long num) {
        if (!map.containsKey(num)) {
            map.put(num, num + 1);
            return num;
        }
        long val = get(map.get(num));
        map.put(num, val);
        return val;
    }
}
