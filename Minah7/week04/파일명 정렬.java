// 실패
// 테케만 맞고 계속 런타임 에러 뜸..

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        int f = files.length;
        String[] answer = new String[f];
        
        String[][] div = new String[f][3];
        for(int i = 0; i < f; i++) {
            int s = 0;
            int e = 0;
            for(int c = 0; c < files[i].length(); c++) {
                if((Character.isDigit(files[i].charAt(c)) && s == 0)) {
                    s = c;
                }
                if(s != 0 && !Character.isDigit(files[i].charAt(c))) {
                    e = c;
                    break;
                }
            }
            div[i][0] = files[i].substring(0, s);
            div[i][1] = files[i].substring(s, e);
            div[i][2] = files[i].substring(e);
        }
        
        Arrays.sort(div, (o1, o2) -> {
			if(o1[0].toLowerCase().compareTo(o2[0].toLowerCase(Locale.ROOT)) > 0) {
                return 1;
            } else if(o1[0].toLowerCase().compareTo(o2[0].toLowerCase(Locale.ROOT)) < 0) {
                return -1;
            } else {
                if(Integer.parseInt(o1[1]) > Integer.parseInt(o2[1])) {
                    return 1;
                } else if(Integer.parseInt(o1[1]) < Integer.parseInt(o2[1])) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        
        for(int i = 0; i < f; i++) {
            answer[i] = div[i][0] + div[i][1] + div[i][2];
        }
        
        return answer;
    }
}