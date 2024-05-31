import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 수열과_쿼리_5 {


    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sqrt = (int) Math.sqrt(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[][] queries = new int[M][3];
        int[] result = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken())-1;
            queries[i][1] = Integer.parseInt(st.nextToken())-1;
            queries[i][2] = i;
        }

        // srt를 sqrt 구간 기준으로 정렬. end 값에 대한 고려도 같이 해주면 더 좋은 성능을 낼 수 있다.
        Arrays.sort(queries, (o1, o2) -> {
            if (o1[0]/sqrt == o2[0]/sqrt) return o1[1] - o2[1];
            return o1[0]/sqrt - o2[0]/sqrt;
        });

        // init (첫 값은 직접 구한다)
        Map<Integer, Integer> countMap = new HashMap<>();
        int[] query = queries[0];
        for (int i = query[0]; i <= query[1]; i++) {
            addToMap(arr, countMap, i);
        }

        result[query[2]] = countMap.keySet().size();

        // 나머지
        for (int i = 1; i < M; i++) {
            query = queries[i];
            int l = queries[i-1][0];
            int r = queries[i-1][1];

            // 만약 겹치는 구간이 아예 없으면 기존 map을 초기화하고 새로 구한다. 이 경우에도 시간 복잡도는 구간의 크기인 sqrt를 넘을 수 없음
            if (r < query[0]){
                countMap.clear();
                for (int j = query[0]; j <= query[1]; j++) {
                    addToMap(arr, countMap, j);
                }
            }

            // 겹치는 구간이 존재한다면 전에 구했던 값을 기반으로 map을 업데이트 한다.
            else {
                while(l > query[0]) {
                    l--;
                    addToMap(arr, countMap, l);
                }
                while(l < query[0]) {
                    removeFromMap(arr, countMap, l);
                    l++;
                }
                while(r > query[1]) {
                    removeFromMap(arr, countMap, r);
                    r--;
                }
                while(r < query[1]) {
                    r++;
                    addToMap(arr, countMap, r);
                }
            }

            result[query[2]] = countMap.keySet().size();
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int res : result) {
            sb.append(res).append("\n");
        }
        System.out.println(sb);


    }

    private static void removeFromMap(int[] arr, Map<Integer, Integer> countMap, int idx) {
        if (!countMap.containsKey(arr[idx])) return;
        if (countMap.get(arr[idx]) == 1) countMap.remove(arr[idx]);
        else countMap.put(arr[idx], countMap.get(arr[idx])-1);
    }

    private static void addToMap(int[] arr, Map<Integer, Integer> countMap, int idx) {
        if (countMap.containsKey(arr[idx])){
            countMap.put(arr[idx], countMap.get(arr[idx])+1);
        } else {
            countMap.put(arr[idx], 1);
        }
    }

}