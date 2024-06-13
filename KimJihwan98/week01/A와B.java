import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0;i < T.length(); i++) {
            list.add(T.charAt(i));
        }
        int answer = -1;
        while(true) {
            int size = list.size();
            if(list.get(size-1)=='A') {
                list.remove(size - 1);
            } else {
                list.remove(size - 1);
                Collections.reverse(list);
            }
            if(list.size() == S.length()) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0;i < list.size(); i++) {
                    if(list.get(i)=='A') sb.append("A");
                    else sb.append("B");
                }
                T = sb.toString();
                if(S.equals(T)) answer = 1;
                else answer = 0;
                break;
            }
        }

        System.out.println(answer);
    }
}
