import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        
        HashMap<Long, Long> map = new HashMap<>();
        long[] answer = new long[room_number.length];
        for(int i=0; i<room_number.length;i++){
            answer[i] = findEmptyRoomN(room_number[i], map);
        }
        return answer;
    }
    
    long findEmptyRoomN(long reqN, HashMap<Long,Long> map){
        if(!map.containsKey(reqN)){
            map.put(reqN, getNextRoomN(reqN, map));
            return reqN;
        }
        
        long roomN = getNextRoomN(reqN, map);
        long roomNextN = getNextRoomN(roomN, map);
        map.put(reqN, roomNextN);
        map.put(roomN, roomNextN);
        return roomN;
    }
    
    long getNextRoomN(long roomN, HashMap<Long, Long> map){
        List<Long> list = new ArrayList<>();
        long nxtRoom = roomN+1;
        if(map.containsKey(roomN)){
            nxtRoom = map.get(roomN);
        }
        
        
        while(map.containsKey(nxtRoom)){
            list.add(nxtRoom);
            nxtRoom = map.get(nxtRoom);
        }
        
        for(long num: list){
            map.put(num, nxtRoom);
        }
        
        return nxtRoom;
        
    }
}