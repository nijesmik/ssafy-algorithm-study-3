/** n^2 배열 자르기
 * 스터디 지정 문제. 예전에 파이썬으로 풀어본 쉬운 문제임.
 * 아이디어 : 직접 2차원 배열을 만들어서 1차원 배열로 옮기면 메모리 바로 터질 범위임.
 * 1. index를 n에 대한 나머지와 몫을 각각 구한다.
 * 2. 이 중 큰 값을 고르고 그 값에 1을 더한 값이 해당 index의 값이다.
 * 3. 예시로 index가 5이고 n이 3이라면 몫은 1, 나머지는 2이다. 따라서 실제 값은 3이다.
 * 1 2 3
 * 2 2 [3]
 * 3 3 3
 * 4. 다른 예시로 index가 4이고 n이 3이라면 몫은 1, 나머지는 1이다. 따라서 실제 값은 2이다.
 *  * 1 2 3
 *  * 2 [2] 3
 *  * 3 3 3
 */
class n제곱_배열_자르기 {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];
        int idx = 0;
        for (long i = left; i <= right; i++){
            answer[idx++] = (int) Math.max(i/n, i%n) + 1;
        }
        return answer;
    }
}