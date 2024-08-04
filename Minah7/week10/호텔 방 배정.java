// 미완성

class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        boolean[] check = new boolean[room_number.length];
        for(long i = 0; i < room_number.length; i++) {
            if(!check[room_number[i]]) {
                check[room_number[i]] = true;
                answer[i] = room_number[i];
            } else {
                for(long start = room_number[i] + 1; start < room_number.length; start++) {
                    if(!check[start]) {
                        check[start] = true;
                        answer[i] = start;
                    }
                }
            }
        }
        return answer;
    }
}