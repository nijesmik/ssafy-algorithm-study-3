import java.util.*;


class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for(int test=0; test < s.length; test++){

            // System.out.println("test"+test);

            String str = s[test];

            Stack<Character> stk = new Stack<>();


            int idx = 0;
            int countX = 0;
            while(idx >= 0 && idx < str.length()){
                stk.push(str.charAt(idx++));



                if(stk.size()>=3){
                    char a = stk.pop();
                    char b = stk.pop();
                    char c = stk.pop();

                    if(a=='0' && b=='1' && c == '1'){
                        countX++;
                        continue;
                    }

                    stk.push(c);
                    stk.push(b);
                    stk.push(a);

                };

            }
            // System.out.println(stk.toString());

            StringBuilder sb = new StringBuilder();

            int size = stk.size();
            for(int i=0; i< size;i++){
                char t = stk.pop();
                if(t == '0'){
                    stk.push(t);
                    break;
                }
                sb.insert(0,t);
            }

            // System.out.println("stk: "+stk.toString());
            // System.out.println("sb: "+sb.toString());
            for(int i=0; i<countX;i++){
                sb.insert(0,"110");
            }

            while(!stk.isEmpty()){
                sb.insert(0, stk.pop());
            }






            answer[test] = sb.toString();
        }


        return answer;
    }
}