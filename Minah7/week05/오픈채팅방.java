// 성공

import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        Queue<Integer> messages = new LinkedList<>(); // 출력 순서를 담을 큐
        HashMap<String, String> id = new HashMap<>(); // 아이디와 닉네임 담을 용도
        for(int i = 0; i < record.length; i++) {
            if(record[i].contains("Enter") || record[i].contains("Change")) {
                String[] r = record[i].split(" ");
                id.put(r[1], r[2]);
                if(r[0].equals("Enter")) {
                    messages.add(i);
                }
            } else if(record[i].contains("Leave")) {
                messages.add(i);
            }
        }
        
        int ms = messages.size();
        String[] answer = new String[ms];
        for(int m = 0; m < ms; m++) {
            // String s = record[messages.poll()];
            String[] info = record[messages.poll()].split(" ");
            if(info[0].equals("Enter")) {
                answer[m] = id.get(info[1]) + "님이 들어왔습니다.";
            } else {
                answer[m] = id.get(info[1]) + "님이 나갔습니다.";
            }
        }       
        return answer;
    }
}