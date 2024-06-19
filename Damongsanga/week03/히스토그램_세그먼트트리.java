import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 히스토그램
 *  시간복잡도 : O(NlogN) -> 최악의 경우 넓이를 구하는 과정에서 O(N)이 될 수 있다는데..
 *  사용 알고리즘 : 세그먼트 트리
 *  중요 아이디어 :
 *  1. 세그먼트 트리로 범위 내 제일 낮은 높이를 가진 "idx" 값을 저장함
 *  2. query로 범위 내 가장 낮은 높이를 가지는 idx 값을 반환
 *  2.1 여기서 default 값으로는 아예 나올 수 없는 값을 주고, 그 값을 반환하면 무시하도록 구현
 *  3. 전체를 범위로 잡고 최대 넓이를 가지는 함수 구현
 *  3.1 최소 높이을 가지는 idx를 기준으로 왼쪽, 오른쪽으로 그 다음 최소 높이를 가지는 idx를 찾는 방법으로 재귀적으로 구현
 *  주의할 점
 *  1. 값이 0이 들어올 수 있다
 *  2. 문제에서 20억은 넘지 않는다 했음으로 long은 고려할 필요가 없다.
 * */
public class 히스토그램_세그먼트트리 {

    static int[] tree;
    static int[] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // tree에는 idx가 저장됨
        tree = new int[4*N];
        initRec(1, N, 1);

        System.out.println(queryMaxSize(1, N));

    }

    public static int initRec(int srt, int end, int node){
        if (srt == end) return tree[node] = srt;
        int mid = srt + (end - srt) / 2;
        int left = initRec(srt, mid, node * 2);
        int right = initRec(mid+1, end, node * 2 + 1);
        return tree[node] = (arr[left] > arr[right]) ? right : left;
    }

    // 범위 내 가장 작은 높이를 가지는 idx를 구하는!
    // left, right : 원래 구하려는 범위, srt, end : 지금 rec에서 구하는 범위
    public static int queryMinHeightIdx(int srt, int end, int node, int left, int right){
        if (end < left || right < srt) return -1; // 0이 아닌 나올 수 없는 값으로 하는게 맞음
        if (left <= srt && end <= right) return tree[node];

        int mid = srt + (end - srt) / 2;
        int idxLeft = queryMinHeightIdx(srt, mid, node * 2, left, right);
        int idxRight = queryMinHeightIdx(mid+1, end, node * 2 + 1, left, right);
        if (idxLeft == -1) return idxRight;
        if (idxRight == -1) return idxLeft;
        return arr[idxLeft] > arr[idxRight] ? idxRight : idxLeft;

    }

    public static int queryMaxSize(int srt, int end){
        int mid = queryMinHeightIdx(1, N, 1, srt, end);
        int area = arr[mid] * (end - srt + 1);

        if (srt <= mid-1)
            area = Math.max(area, queryMaxSize(srt, mid-1));
        if (mid + 1 <= end)
            area = Math.max(area, queryMaxSize(mid+1, end));

        return area;
    }

}