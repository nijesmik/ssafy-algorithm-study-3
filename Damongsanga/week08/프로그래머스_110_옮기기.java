import java.util.ArrayDeque;
import java.util.Deque;


/**110 옮기기
 * 알고리즘 : 덱
 * 질문하기에서 스택 이라는 키워드를 보고 덱으로 풀어보기로 했다
 * 핵심 아이디어 :
 * 1. 숫자를 덱에 1개씩 넣으나 110을 발견하면 빼서 보관한다 (여기서 실제 110을 저장할 필요는 없고 덱에 넣지 않고 카운트만 해주면 된다)
 * 2. 다시 덱을 StringBuilder에 넣으면서 적절한 첫 번째 위치에 센 110을 모두 넣어주면 된다. 어자피 넣는 숫자가 110으로 동일함으로 110 사이에 다른 숫자가 들어갈 이유가 없다
 * 3. 적절한 위치는 현재가 1이고 다음 숫자가 존재하지 않거나 0이 아니면 된다. 그러면 결국 11X가 될 것임으로 11로 끝나던, 110이 되던, 111이 되던 110들을 삽입하기 적절한 위치가 된다.
 * 4. 만약 적절한 위치를 찾지 못했다면, 맨 마지막에 110 들을 넣어주면 된다.
 * */
class 프로그래머스_110_옮기기 {
    public String[] solution(String[] s) {
        int N = s.length;

        for (int i = 0; i < N; i++) {
            char[] now = s[i].toCharArray();
            Deque<Character> deque = new ArrayDeque<>();
            int count = 0;

            for (int j = 0; j < now.length; j++) {
                char target = now[j];
                if (deque.size() >= 2 && target == '0'){
                    if (deque.peekLast() == '1'){
                        char polled = deque.pollLast();
                        if (deque.peekLast() == '1'){
                            deque.pollLast();
                            count++;
                            continue;
                        } else {
                            deque.addLast(polled);
                        }
                    }
                }
                deque.addLast(now[j]);
            }

            StringBuilder sb = new StringBuilder();
            while (!deque.isEmpty()){
                char target = deque.pollFirst();
                if (count > 0 && target == '1' && !(!deque.isEmpty() && deque.peekFirst() == '0')){
                    for (int j = 0; j < count; j++) {
                        sb.append("110");
                    }
                    count = 0;
                }
                sb.append(target);
            }
            if (count>0){
                for (int j = 0; j < count; j++) {
                    sb.append("110");
                }
            }
            s[i] = sb.toString();
        }

        return s;
    }


}
