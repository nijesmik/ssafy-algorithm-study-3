import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/** 최솟값 찾기
 * 승준이 풀이 문제로 호영이형 추천 문제
 * 알고리즘 : 슬라이딩 윈도우 + 덱
 * 시간 복잡도 : O(N)
 * 핵심 :
 * 1. 슬라이딩 윈도우 만으로는 문제를 풀 수 없다 (범위 내에 불필요한 값을 삭제한 값들을 저장해 두어야 할 자료구조가 필요하다)
 * 2. 덱에 값을 넣는 것이 아닌 index를 넣는다! -> 한 개씩 덱애서 빼야하기 때문
 * 문제 풀이
 * 1. 덱의 왼쪽은 index 범위를 확인하여 넘어가면 빼준다.
 * 2.1 덱의 오른쪽을 보면서 현재 추가할 값보다 큰 값들은 빼준다! -> 핵심!
 * 2.2 덱의 오른쪽에 값 추가
 * 3. 덱에서 가장 왼쪽 값이 최솟값이다
 * 헤멘 부분
 * 1. 우선순위 큐 2개로 풀어보려고 했는데 왠지 모르겠는데 자꾸 틀렸다..
 * 2. 시간 복잡도도 O(NlogN)으로 덱 사용 할 때보다 더 별로다.
 * */

public class 최솟값_찾기 {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //
        StringBuilder answer = new StringBuilder();
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - L + 1) deque.pollFirst();
            while (!deque.isEmpty() && arr[deque.peekLast()] >= arr[i]) deque.pollLast();
            deque.offerLast(i);
            answer.append(arr[deque.peekFirst()]).append(" ");
        }

        System.out.println(answer.toString().trim());

    }

}