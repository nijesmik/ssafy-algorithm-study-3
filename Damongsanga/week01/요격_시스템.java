import java.util.Arrays;

/** 요격 시스템
 * n이 50만 이하임으로 정렬 가능
 * 미사일의 왼쪽이 작은 순으로 정렬하여 현재 요격시스템으로 커버하는 범위의 오른쪽이 미사일의 왼쪽과 겹치면 추가의 요격시스템을 사용할 필요가 없고
 * 미사일의 왼쪽이 범위를 벗어나면 answer를 1 상승 시킨다
 * 문제의 포인트는 정렬을 미사일 왼쪽 기준으로 하되 비교할 때는 오른쪽만 고려하면 된다는 것
* */
class 요격_시스템 {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[0] - b[0]);
        int r = targets[0][1];
        int answer = 1;

        for (int i = 1; i < targets.length; i++) {
            int[] target = targets[i];
            if (target[0] >= r){
                answer++;
                r = target[1];
            } else {
                r = Math.min(r, target[1]);
            }
        }

        return answer;

    }
}