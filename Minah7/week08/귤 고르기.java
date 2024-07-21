// 성공

import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int tanTypes = 0;
        int[] cnt = new int[10000001];
        for(int i = 0; i < tangerine.length; i++) {
            cnt[tangerine[i]]++;
        }
        Arrays.sort(cnt);
        int c = cnt.length -1;
        while(k > 0) {
            if(k - cnt[c] > 0) {
                tanTypes++;
                k -= cnt[c];
                c--;
            } else if(k - cnt[c] == 0) {
                tanTypes++;
                k -= cnt[c];
                c--;
            } else {
                tanTypes++;
                k -= cnt[c];
            }
        }
        for(int i = cnt.length -1; i >= 0; i--) {
            if(k > cnt[i]) {
                tanTypes++;
                k-=cnt[i];
            }
        }
        return tanTypes;
    }
}