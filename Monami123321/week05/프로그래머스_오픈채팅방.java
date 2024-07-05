package programmers;

import java.util.HashMap;

public class 프로그래머스_오픈채팅방 {
    public String[] solution(String[] record) {
        String[][] arr = new String[record.length][];
        HashMap<String, String> hm = new HashMap<>();
        int size = arr.length;
        for (int i = 0; i < record.length; ++i) {
            arr[i] = record[i].split(" ");
            if (arr[i][0].equals("Enter")) {
                hm.put(arr[i][1], arr[i][2]);
            } else if (arr[i][0].equals("Change")) {
                hm.put(arr[i][1], arr[i][2]);
                --size;
            }
        }
        String[] ans = new String[size];
        int index = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i][0].equals("Enter")) {
                ans[index++] = hm.get(arr[i][1]) + "님이 " + "들어왔습니다.";
            } else if (arr[i][0].equals("Leave")) {
                ans[index++] = hm.get(arr[i][1]) + "님이 " + "나갔습니다.";
            }
        }
        return ans;
    }
}
