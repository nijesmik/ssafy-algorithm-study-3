// 미완성
// https://school.programmers.co.kr/learn/courses/30/lessons/17685

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        boolean[] educated = new boolean[26];
        HashMap<String, String> prefix = new HashMap<>(); 
        for(int w = 0; w < words.length; w++) {
            int first = words[w].charAt(0) - 'a';
            if(!educated[first]) {
                answer += words[w].length();
                educated[first] = true;
                prefix.put(words[w], words.substring(0, 1);
            } else if(prefix.containsKey(words[w]) {
                answer += prefix.get(words[w]).length();
            } else {
                for(int i = 1; i <= words[w].length; i++) {
                    if(!prefix.containsKey(words[w].substring(0, i)) )
                }
            }
        }
        return answer;
    }
}