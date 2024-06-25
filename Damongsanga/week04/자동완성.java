import java.util.Arrays;

/** 자동완성
 * 알고리즘 : 정렬 & 투포인터
 * 핵심 아이디어 :
 *  1. 자동완성은 앞에서부터 탐색함으로 정렬 필요, 단어 수도 10만개임으로 가능
 *  2. 단어 길이 합이 100만 이하임을 주목. 만약 투포인터로 직전 단어와 한 알파벳씩 비교해도 200만 이하일 것임
 *  구현 방법 :
 *  1. 직전 단어와 투포인터로 맨 앞 index에서부터 알파벳이 같은지 비교
 *  2. 다른 순간까지 now 증가 (현재 단어를 구별하기 위해 입력해야 하는 단어 수)
 *  3. prev index는 현재 단어에 의해 갱신될 수 있음. 만약 prev가 현재 now 보다 더 작다면, 이는 갱신이 필요하다는 뜻
 *  4. 여기서 갱신을 잘 해주어야 하는데 글자수가 부족해서 now 만큼 입력을 못할 수 있음. 따라서 prev = Math.min(now, prev 단어 길이)
 *  5. 이제 prev 단어는 더이상 갱신될 일이 없음으로 답에 sum
 *  6. 마지막까지 for문을 돌고 마지막 글자의 now를 답에 sum해주면 끝
 * */
class 자동완성 {
    public int solution(String[] words) {
        Arrays.sort(words);
        int answer = 0;
        int prev = 1;
        int now = 0;

        for (int i = 1; i < words.length; i++) {
            int idxPrev = 0;
            int idxNow = 0;
            now = 1;
            while (idxPrev < words[i-1].length() && idxNow < words[i].length()){
                if (words[i-1].charAt(idxPrev++) != words[i].charAt(idxNow++)) break;
                now++;
            }
            if (prev < now) prev = Math.min(now, words[i-1].length());
            answer += prev;
            prev = now;
        }

        answer += now;

        return answer;
    }
}