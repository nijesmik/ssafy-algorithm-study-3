import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/** 히스토그램
 *  원래 세그먼트 트리 사용해보려다 스택으로 풀어봄.
 *  시간복잡도 : O(N)
 *  사용 알고리즘 : 스택
 *  중요 아이디어 :
 *  1. 스택으로 값이 점점 커지는 경우만 값을 넣는다. 여기에는 idx와 val을 모두 넣는다.
 *  1.1 여기서 스택에 초기값으로 idx가 -1, val이 0인 값을 넣어주자. (stack empty 방지 및 아래 로직을 위해 필요하다)
 *  2. 만약 더 작은 val 의 값이 들어온다면, 작거나 같을 때까지 stack에서 뽑는다.
 *  3. 여기서 중요한데, stack에서 뽑고나서, peek을 해서 보이는 idx (즉, stack 뽑기 전 맨 위에서 2번째 idx) 값으로 직사각형을 계산해야 한다.
 *  3.1 이 이유는, 직사각형이 현재 stack에서 보고 있는 idx 기준으로 양방향으로 직사각형이 생길 수 있기 때문임
 *  3.2 이러한 예시를 보면 알 수 있다. ..2434 -> 여기서 맨 앞에 4는 스택에서 없고, 3은 스택에 있는데 새로운 4가 들어오면 3 만으로는 뒤에 4가 있는지 없는지 알 수 없다. (잘 생각해보자.. 말로 설명을 잘 못하겠음)
 *  주의할 점
 *  1. 값이 0이 들어올 수 있다
 *  2. 문제에서 20억은 넘지 않는다 했음으로 long은 고려할 필요가 없다.
 * */
public class 히스토그램 {

    static class Node{
        int idx; int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(-1,0));
        int answer = 0;
        for (int idx = 0; idx < N; idx++) {
            int now = arr[idx];
            while (!stack.isEmpty() && now < stack.peek().val){
                Node node = stack.pop();
                answer = Math.max((idx - stack.peek().idx - 1) * node.val, answer);
            }
            stack.push(new Node(idx, now));
        }

        while(stack.peek().val != 0){
            Node node = stack.pop();
            answer = Math.max((N - stack.peek().idx - 1)* node.val, answer);
        }

        System.out.println(answer);
    }

}