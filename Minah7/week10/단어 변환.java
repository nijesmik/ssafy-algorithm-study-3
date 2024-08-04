// 미완성

class Solution {
    static int slen;
    static int wlen;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        slen = begin.length();
        int diff = 0;
        boolean[] d = new int[slen];
        for(int i = 0; i < wlen; i++) {
            if(begin.charAt(i) != target.charAt(i)) {
                diff++;
                d[i] = true;
}
}
        wlen = words.length;
        int cnt = 0;
        ArrayList<Integer>[] arr = new ArrayList[wlen];
        for(int i ) {
}
        dfs(0);
        return answer;
    }
    
    public static void dfs(int start) {
        for(int i = start; i < wlen; i++) {
            
}
}
}