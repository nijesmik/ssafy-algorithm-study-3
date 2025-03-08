// https://school.programmers.co.kr/learn/courses/30/lessons/131704

import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int oLen = order.length;
        Stack<Integer> conveyorBelt = new Stack<>();
        int box = 1;
        for(int i = 0; i < oLen; i++) {
            if(box < order[i]) {
                while(box < order[i]) {
                    conveyorBelt.push(box++);
                }
                if(box == order[i]) {
                	answer++;
                	box++;
                }
            } else if(box == order[i]) {
            	answer++;
            	box++;
            } else {
            	if(conveyorBelt.peek() == order[i]) {
            		conveyorBelt.pop();
            		answer++;
            	} else {
            		break;
            	}
            }
        }
        return answer;
    }
}