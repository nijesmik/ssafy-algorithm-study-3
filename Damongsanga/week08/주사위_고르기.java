import java.util.ArrayList;
import java.util.Arrays;

/**주사위 고르기
 * 알고리즘 : 백트랙킹
 * 주요 아이디어 :
 * 1. 중복 탐색하지 않도록 백트랙킹으로 절반(N)만 선택
 * 2. 승패 계산시 이기는 경우와 지는 경우를 모두 계산해야
 * 3. 해당 주사위 조합으로 나올 수 있는 합의 count를 계산 (예시로 합이 6인 경우가 6, 합이 6 이하인 경우가 5이라면, 합 6으로 이길 수 있는 경우는 5 * 6 = 30)
 * 3.1 이 경우는 (6^N) + (N * 100) 횟수만 탐색하면 된다.
 * 3.1 만약 직접 모든 경우를 하나하나 구해서 대조하면 (6^N) + (6^N)^2 횟수 탐색해야 한다..
 * 헤멘 부분 :
 * 1. 예전에 못푼 문제였는데 확인해보니, 리턴 값을 이기는 횟수나 지는 횟수만 반환하는게 아니라 (이긴 횟수 - 진 횟수) 으로 반환했었다.
 * 2. 이 경우, 전체 합이 일정하더라도 무승부라는 추가 변수가 있음으로 (이긴 횟수 - 진 횟수)가 높다고 많이 이겼다고 할 수 없다.
 * 3. 따라서 그저 이긴 횟수 (혹은 진 횟수) 가 많은 값을 답으로 선택해야 한다.
 * */
class 주사위_고르기 {

    static int weight = 0;
    static boolean[] answerList;
    static int n;

    public int[] solution(int[][] dice) {
        n = dice.length/2; // n은 1명이 가져가는 주사위 수
        boolean[] visited = new boolean[n*2];

        // 절반만 탐색하도록 맨 첫번째 주사위는 선택하여 backtracking
        visited[0] = true;
        backtracking(1, 0, visited, dice);

        int[] answer = new int[n];
        int idx = 0;
        for (int i = 0; i < n*2; i++) {
            if (answerList[i]) answer[idx++] = i+1;
        }

        return answer;
    }

    static void backtracking(int count, int last, boolean[] visited, int[][] dice){
        if (count == n){
            int win = getWin(visited, dice);
            if (weight < Math.abs(win)){ // 내가 고른 주사위의 반대 경우도 포함하도록 절대값
                weight = Math.abs(win);
                answerList = Arrays.copyOf(visited, n*2);
                if (win < 0) { // 진 경우를 반환했다면 반대 경우 선택
                    for (int i = 0; i < n * 2; i++) {
                        answerList[i] = !answerList[i];
                    }
                }
            }
            return;
        }

        for (int i = last+1; i < n*2; i++) {
            visited[i] = true;
            backtracking(count+1, i, visited, dice);
            visited[i] = false;
        }
    }


    static int getWin(boolean[] visited, int[][] dice){
        ArrayList<int[]> chosenDiceData = new ArrayList<>(); // 고른 주사위 6면들
        ArrayList<int[]> notChosenDiceData = new ArrayList<>(); // 안고른 주사위 6면들

        for (int i = 0; i < n*2; i++) {
            if (visited[i]) chosenDiceData.add(dice[i]);
            else notChosenDiceData.add(dice[i]);
        }

        int[] countSumOfChosenDices = new int[n*100+1]; // 주사위 한 명 최대값이 100임
        getDiceSumDFS(0, 0, countSumOfChosenDices, chosenDiceData);
        int[] countSumOfNotChosenDices = new int[n*100+1];
        getDiceSumDFS(0, 0, countSumOfNotChosenDices, notChosenDiceData);

        int winTotal = 0; // 승리 누적
        int loseTotal = 0; // 패배 누적
        int winTmp = 0;
        int loseTmp = 0;
        for (int i = n; i <= n * 100; i++) {
            winTmp += countSumOfChosenDices[i-1];
            loseTmp += countSumOfNotChosenDices[i-1];
            winTotal += countSumOfChosenDices[i] * (loseTmp);
            loseTotal += countSumOfNotChosenDices[i] * (winTmp);
        }

        // 진 값으로 반환하는 경우 음수로 반환
        return winTotal > loseTotal? winTotal : loseTotal * -1;
    }

    // 실제 카운트 값 계산
    static void getDiceSumDFS(int depth, int sum, int[] countSumOfDices, ArrayList<int[]> chosenDiceData){
        if (depth == n){
            countSumOfDices[sum]++;
            return;
        }

        for (int i = 0; i < 6; i++) {
            sum += chosenDiceData.get(depth)[i];
            getDiceSumDFS(depth+1, sum, countSumOfDices, chosenDiceData);
            sum -= chosenDiceData.get(depth)[i];
        }
    }

}