import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 표 병합
 * 알고리즘 : 유니온 파인드
 * 핵심 아이디어 :
 * 1. 내용은 그렇게 어렵지 않다. r,c 기준으로 id를 만들어 부모 id에 해당하는 값을 기준으로 업데이트하면 된다.
 * 헤멘 부분 :
 * 1. [update value1 value2]를 맨 처음에 hashmap을 사용해서 최대한 효율적으로 바꿔보려고 했는데
 *    시간 복잡도 계산해보니 모든 command를 N^2 로 for문 돌아도 250만으로 충분히 시간 안에 들어올 수 있어 그냥 이중 for문으로 구현했다.
 * 2. 입력이 1~50으로 주어지는데 이를 그냥 메소드 매개변수로 넘겨줄 때부터 0 ~ 49로 받게 하는게 편하다.
 * */
class 표_병합 {
    static int N = 50;
    static int[] parents = new int[N*N+1];
    String[][] arr = new String[N][N];

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        // 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = "EMPTY";
                parents[getId(i,j)] = getId(i,j);
            }
        }

        for (String command : commands) {
            String[] tmp = command.split(" ");
            switch (tmp[0]) {
                case "UPDATE" -> {
                    if (tmp.length == 4) update(Integer.parseInt(tmp[1])-1, Integer.parseInt(tmp[2])-1, tmp[3]);
                    else updateValue(tmp[1], tmp[2]);
                }
                case "MERGE" -> merge(Integer.parseInt(tmp[1])-1, Integer.parseInt(tmp[2])-1, Integer.parseInt(tmp[3]) - 1, Integer.parseInt(tmp[4]) - 1);
                case "UNMERGE" -> unmerge(Integer.parseInt(tmp[1]) - 1, Integer.parseInt(tmp[2]) - 1);
                case "PRINT" -> print(Integer.parseInt(tmp[1]) - 1, Integer.parseInt(tmp[2]) - 1, answer);
            }
        }

        return answer.toArray(new String[0]);
    }

    private void update(int r, int c, String newValue){
        int parentId = find(getId(r,c));
        arr[parentId/N][parentId%N] = newValue;
    }

    private void updateValue(String value, String newValue){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j].equals(value)) arr[i][j] = newValue;
            }
        }
    }

    private void merge(int r1, int c1, int r2, int c2){
        if (r1 == r2 && c1 == c2) return;
        if (!getValueByRC(r1, c1).equals("EMPTY")){
            union(getId(r1,c1), getId(r2,c2));
        } else {
            union(getId(r2,c2),getId(r1,c1));
        }
    }


    private void unmerge(int r, int c){
        int parentId = find(getId(r,c));
        String value = arr[parentId/N][parentId%N];

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N*N; i++) {
            if (find(i) == parentId) set.add(i);
        }

        for (Integer id : set) {
            parents[id] = id; // 그룹 초기화
            arr[id/N][id%N] = "EMPTY";
        }

        arr[r][c] = value;

    }

    private void print(int r, int c, List<String> answer){
        answer.add(getValueByRC(r,c));
    }


    // a가 부모가 됨
    private boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB){
            return false;
        }
        parents[parentB] = parentA;
        return true;
    }

    private int find(int a){
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private int getId(int r, int c){
        return r * N + c;
    }

    private String getValueById(int id){
        int parentId = find(id);
        return arr[parentId/N][parentId%N];
    }

    private String getValueByRC(int r, int c){
        int parentId = find(getId(r,c));
        return getValueById(parentId);
    }

}