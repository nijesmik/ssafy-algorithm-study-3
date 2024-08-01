package test;

import java.util.*;

/**가사 검색
 * 알고리즘 : 이분 탐색, 맵
 * 핵심 아이디어 :
 * 0. 단어와 쿼리 갯수는 10만, 단어의 길이 자체가 1만 이하로 / 해당 단어가 쿼리에 적절한지 비교하는 경우는 O(N)으로 들어가도, 단어 탐색은 log(N)으로 해결해야 한다 -> 이분 탐색
 * 0.1 단어 갯수가 10만으로 Nlog(N)만에 정렬이 가능하긴 하다. 그리고 단어 길이별로 나눌 것임으로 정렬하는데 시간이 덜들 것이다.
 *
 * 1. 쿼리는 접두사나 접미사 1가지로만 들어옴으로, 비교로직은 앞에서부터 (접두사를) 검사하는 방식으로 하되, 접미사로 쿼리가 들어오면 쿼리를 뒤집는다.
 * 2. 이에 맞추어 words도 단어 길이에 따라 일반 단어와 뒤집힌 단어, 2가지를 저장해둔다 (frontMap, backMap) 따라서 key가 단어의 길이가 된다.
 * 3. 쿼리가 정방향이면 frontMap에서, 쿼리가 접미사이면 뒤집어서 backMap에서 탐색한다.
 * 4. map에서 단어 길이에 해당하는 key에서 단어 리스트를 받아와 쿼리에 적절한 단어가 있는지 이분탐색으로 찾는다. 없으면 바로 0을 반환한다.
 * 5. 있다면 그 단어 idx를 기준으로 왼쪽 boundady, 오른쪽 boundary를 각각 이분탐색으로 찾는다. (단어당 최악의 경우 총 3회 탐색)
 *
 * 기타 :
 * 1. 단어는 중복이 없으나 쿼리는 중복이 있음으로 캐시를 고려할 수 있으나, 테스트 결과 읽기 요청이 그렇게 많지 않아서 효과는 미비했다. 오히려 캐시를 사용한 경우가 더 오래걸린 경우도 존재
 * */
class 가사_검색 {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        // 인풋
        Map<Integer, List<String>> frontMap = new HashMap<>();
        Map<Integer, List<String>> backMap = new HashMap<>();
        for (String word : words){
            int len = word.length();
            frontMap.putIfAbsent(len, new ArrayList<>());
            frontMap.get(len).add(word);
            backMap.putIfAbsent(len, new ArrayList<>());
            backMap.get(len).add(new StringBuilder(word).reverse().toString());
        }

        // 정렬
        for (List<String> list :frontMap.values()){
            Collections.sort(list);
        }
        for (List<String> list :backMap.values()){
            Collections.sort(list);
        }

        // 캐시
//        Map<String, Integer> cache = new HashMap<>();
        // 쿼리
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
//            if (cache.containsKey(query)) {
//                answer[i] = cache.get(query);
//                continue;
//            }

            if (query.charAt(0) != '?') answer[i] = find(query, frontMap);
            else answer[i] = find(new StringBuilder(query).reverse().toString(), backMap);

//            cache.put(query, answer[i]);

        }

        return answer;
    }

    private int find(String query, Map<Integer, List<String>> frontMap) {
        List<String> list = frontMap.get(query.length());
        if (list == null) return 0;
        int found = binarySearch(query, list);
        if (found == -1) return 0;

        return binarySearchRight(query, list, found) - binarySearchLeft(query, list, found) + 1;

    }

    private int binarySearchRight(String query, List<String> list, int l) {
        int r = list.size()-1;
        int rightIdxBoundary = l;
        while(l <= r){
            int mid = l + (r-l) / 2;
            int res = compare(list.get(mid), query);
            if (res == 0) {
                rightIdxBoundary = mid;
                l = mid+1;
            } else if (res == 1) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return rightIdxBoundary;
    }

    private int binarySearchLeft(String query, List<String> list, int r) {
        int l = 0;
        int leftIdxBoundary = r;

        while(l <= r){
            int mid = l + (r-l) / 2;
            int res = compare(list.get(mid), query);
            if (res == 0) {
                leftIdxBoundary = mid;
                r = mid-1;
            } else if (res == 1) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return leftIdxBoundary;
    }

    private int binarySearch(String query, List<String> list) {
        int l = 0;
        int r = list.size()-1;
        int foundIdx = -1;

        while(l <= r){
            int mid = l + (r-l) / 2;
            int res = compare(list.get(mid), query);
            if (res == 0) {
                foundIdx = mid;
                break;
            } else if (res == 1) {
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return foundIdx;
    }


    private int compare(String word, String query){
        for (int i = 0; i < word.length(); i++) {
            if (query.charAt(i) == '?') return 0; // 쿼리에 해당하는 단어면 0 반환
            if (word.charAt(i) > query.charAt(i)) return 1; // 쿼리보다 뒷단어면 1
            else if (word.charAt(i) < query.charAt(i)) return -1; // 쿼리보다 앞단어면 -1
        }
        return 0;
    }

}